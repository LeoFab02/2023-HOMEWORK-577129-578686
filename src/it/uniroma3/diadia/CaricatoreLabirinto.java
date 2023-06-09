package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente il nome stanza buia */
	private static final String STANZE_BUIE_MARKER = "Buia:";  

	/* prefisso della riga contenente il nome stanza bloccata */
	private static final String STANZE_BLOCCATE_MARKER = "Bloccata:";  

	/* prefisso della riga contenente il nome stanza bloccata */
	private static final String STANZE_MAGICHE_MARKER = "Magica:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeMago> <presentazione> <attrezzo> */
	private static final String PERSONAGGI_MARKER_MAGO = "Mago:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeStrega> <presentazione> */
	private static final String PERSONAGGI_MARKER_STREGA = "Strega:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeCane> <presentazione> */
	private static final String PERSONAGGI_MARKER_CANE = "Cane:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private BufferedReader reader;

	/*private Map<String, Stanza> nome2stanza;*/

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private LabirintoBuilder lb;


	public CaricatoreLabirinto(String nomeFile, LabirintoBuilder lb) throws FileNotFoundException, FormatoFileNonValidoException {
		this.lb = lb;
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader reader, LabirintoBuilder lb) throws FileNotFoundException, FormatoFileNonValidoException {
		this.lb = lb;
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccata();
			this.leggiECreaStanzeMagica();
			this.leggiInizialeEvincente();
			this.leggiEMettiMaghiNellaStanza();
			this.leggiEMettiStregheNellaStanza();
			this.leggiEMettiCaniNellaStanza();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.lb.addStanza(nomeStanza);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {

			try (Scanner scannerDiLinea = new Scanner(nomeStanza)) {			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Il nome di una stanza."));
					String nome = scannerDiLinea.next();

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Un oggetto"));
					String attrezzo = scannerDiLinea.next();

					this.lb.addStanzaBuia(nomeStanza, attrezzo);
				}
			} 
		}
	}

	private void leggiECreaStanzeBloccata() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {

			try (Scanner scannerDiLinea = new Scanner(nomeStanza)) {			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Il nome di una stanza."));
					String nome = scannerDiLinea.next();

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Una Direzione."));
					String dir = scannerDiLinea.next();
					Direzione direzione = null;

					if(dir.equals("nord")) {
						direzione = Direzione.nord;
					}else if(dir.equals("sud")) {
						direzione = Direzione.sud;
					}else if(dir.equals("est")) {
						direzione = Direzione.est;
					}else if(dir.equals("ovest")) {
						direzione = Direzione.ovest;
					}

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Un oggetto"));
					String attrezzo = scannerDiLinea.next();

					this.lb.addStanzaBloccata(nome, direzione, attrezzo);
				}
			} 
		}
	}

	private void leggiEMettiMaghiNellaStanza() throws FormatoFileNonValidoException{

		String nomiMaghi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_MAGO);
		for(String nomeMago : separaStringheAlleVirgole(nomiMaghi)) {

			try (Scanner scannerDiLinea = new Scanner(nomeMago)) {			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Il nome del mago."));
					String nome = scannerDiLinea.next();

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("La presentazione."));
					String pres = scannerDiLinea.next();

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Un oggetto"));
					String attrezzo = scannerDiLinea.next();

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Un oggetto"));
					String nomeStanza = scannerDiLinea.next();


					AbstractPersonaggio mago = new Mago(nome,pres,new Attrezzo(attrezzo,5));
					this.lb.getListaStanze().get(nomeStanza).setPersonaggio(mago);

				}
			} 
		}

	}

	private void leggiEMettiStregheNellaStanza() throws FormatoFileNonValidoException{

		String nomiStreghe = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_STREGA);
		for(String nomeStrega : separaStringheAlleVirgole(nomiStreghe)) {

			try (Scanner scannerDiLinea = new Scanner(nomeStrega)) {			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Il nome del mago."));
					String nome = scannerDiLinea.next();

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("La presentazione."));
					String pres = scannerDiLinea.next();

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Un oggetto"));
					String nomeStanza = scannerDiLinea.next();

					AbstractPersonaggio strega = new Strega(nome,pres);
					this.lb.getListaStanze().get(nomeStanza).setPersonaggio(strega);

				}
			} 
		}

	}

	private void leggiEMettiCaniNellaStanza() throws FormatoFileNonValidoException{

		String nomiCani = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_CANE);
		for(String nomeCane : separaStringheAlleVirgole(nomiCani)) {

			try (Scanner scannerDiLinea = new Scanner(nomeCane)) {			

				while (scannerDiLinea.hasNext()) {

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Il nome del mago."));
					String nome = scannerDiLinea.next();

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("La presentazione."));
					String pres = scannerDiLinea.next();

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("Un oggetto"));
					String nomeStanza = scannerDiLinea.next();

					AbstractPersonaggio cane = new Strega(nome,pres);
					this.lb.getListaStanze().get(nomeStanza).setPersonaggio(cane);

				}
			} 
		}

	}

	private void leggiECreaStanzeMagica() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {

			this.lb.addStanzaMagica(nomeStanza);

		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.lb.getListaStanze().get(nomeStanzaIniziale);
		this.stanzaVincente = this.lb.getListaStanze().get(nomeStanzaVincente);
		/*this.lb.addStanzaIniziale(nomeStanzaIniziale);
		this.lb.addStanzaVincente(nomeStanzaVincente);*/
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.lb.getListaStanze().get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.lb.getListaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specifiche : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specifiche)) {			

				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();

					Direzione direzione = null;

					if(dir.equals("nord")) {
						direzione = Direzione.nord;
					}else if(dir.equals("sud")) {
						direzione = Direzione.sud;
					}else if(dir.equals("est")) {
						direzione = Direzione.est;
					}else if(dir.equals("ovest")) {
						direzione = Direzione.ovest;
					}
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, direzione, stanzaDestinazione);
				}
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		this.lb.addAdiacenza(stanzaDa,nomeA,dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + ((LineNumberReader) this.reader).getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;//this.lb.getLabirinto().getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;//this.lb.getLabirinto().getStanzaVincente();
	}

}
