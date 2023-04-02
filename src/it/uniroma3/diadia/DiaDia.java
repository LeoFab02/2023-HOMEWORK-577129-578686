package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole console;

	public DiaDia(IOConsole console) {
		this.partita = new Partita();
		this.console = console;
	}

	public void gioca() {
		
		String istruzione; 

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
			
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
		
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		Comando comandoDaEseguire = new Comando(istruzione);
		String nome = comandoDaEseguire.getNome();
		
		if ("fine".equals(nome)) {
			
			this.fine(); 
			return true;
			
		} else if ("vai".equals(nome)) {
			
			this.vai(comandoDaEseguire.getParametro());
			
			
		}else if ("prendi".equals(nome)) {
			
			this.prendi(comandoDaEseguire.getParametro());
			
		}else if("posa".equals(nome)) {
			
			this.posa(comandoDaEseguire.getParametro());
			
		}else if ("aiuto".equals(nome)) {
			
			this.aiuto();
			
		}else {
			
			console.mostraMessaggio("Comando sconosciuto");
			
		}
		
		if (this.partita.vinta()) {
			
			console.mostraMessaggio("Sei riuscito ad arrivare alla biblioteca! Complimenti, Hai vinto!");
			this.fine();
			return true;
			
		} else {
			
			return false;
			
		}
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private boolean vai(String direzione) {
		
		if(direzione==null) {
			
			console.mostraMessaggio("Dove vuoi andare?");
			
			direzione = console.leggiRiga();
			
		}
			
		Stanza prossimaStanza = null;
		
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null) {
			
			console.mostraMessaggio("Direzione inesistente");
		
		}else {
			
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
			
			if(partita.getGiocatore().getCfu() == 0) {
				
				if(partita.isFinita()) {
					console.mostraMessaggio("I tuoi cfu si sono esauriti! Hai perso!");
					this.fine();
					return true;
				}
				
			}
			
		}
		
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		return false;
		
	}
	
	public void prendi(String attrezzo) {
		
		if(attrezzo == null) {
			
			console.mostraMessaggio("Che oggetto vuoi prendere?");
			attrezzo = console.leggiRiga();
			
		}
		
		//Controlli sulla stanza e sulla borsa
		
		//Stanza senza oggetti
		if(partita.getStanzaCorrente().getAttrezzi()[0] == null) {
			
			console.mostraMessaggio("Non ci sono attrezzi da prendere nella stanza!");
			
		}else{
			
			boolean inserito;
			
			if(partita.getStanzaCorrente().hasAttrezzo(attrezzo)) {

				inserito = partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(attrezzo));

				if(inserito) {
					partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(attrezzo));
					console.mostraMessaggio("Hai preso l'attrezzo " + attrezzo + " dalla stanza!");
					console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
				}else {
					console.mostraMessaggio("Non hai preso niente perche' la borsa e' piena o stai trasportando troppo peso!");
				}
				
			}else {
				
				console.mostraMessaggio("Non c'e' l'attrezzo "+attrezzo+"!");
				
			}
			
		}
		
		
		
	}
	
	public void posa(String attrezzo) {

		if(attrezzo == null) {

			console.mostraMessaggio("Che oggetto vuoi posare?");
			attrezzo = console.leggiRiga();
			
		}
		
		if(partita.getGiocatore().getBorsa().isEmpty()) {
			
			console.mostraMessaggio("Non puoi posare attrezzi perche' la borsa e' vuota!");
			
		}else{
			
			Attrezzo rimosso = partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
			boolean inserito;

			if(rimosso != null) {

				inserito = partita.getStanzaCorrente().addAttrezzo(rimosso);

				if(inserito) {
					
					console.mostraMessaggio("Hai posato l'attrezzo "+attrezzo+" nella stanza");
					console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
					
				}else {
					console.mostraMessaggio("Non hai posato l'attrezzo perche' la stanza ha raggiunto la sua capacita' massima");
				}

			}else {
				console.mostraMessaggio("Non hai l'attrezzo "+attrezzo+" che vuoi inserire");
			}
			
		}
		
	}
	

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}