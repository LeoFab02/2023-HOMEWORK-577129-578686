package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Map;
import java.util.TreeMap;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	public Labirinto() {
		
	}

	private Labirinto(String nomeFile, LabirintoBuilder lb) throws FileNotFoundException, FormatoFileNonValidoException {
		
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile,lb);
		
		c.carica();
		
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
		
	}
	
	private Labirinto(StringReader reader, LabirintoBuilder lb) throws FileNotFoundException, FormatoFileNonValidoException {
		
		CaricatoreLabirinto c = new CaricatoreLabirinto(reader,lb);
		
		c.carica();
		
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
		
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaIniziale(Stanza s) {
		this.stanzaIniziale = s;
	}

	public void setStanzaVincente(Stanza s) {
		this.stanzaVincente = s;
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}
	
	public static LabirintoBuilder newBuilder(StringReader labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}
	
	public static class LabirintoBuilder{

		private Labirinto labirinto;
		private Map<String,Stanza> mappa;
		private Stanza ultimaStanza;

		public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {

			mappa = new TreeMap<String,Stanza>();
			this.labirinto = new Labirinto(labirinto,this);

		}
		
		public LabirintoBuilder(StringReader labirinto) throws FileNotFoundException, FormatoFileNonValidoException {

			mappa = new TreeMap<String,Stanza>();
			this.labirinto = new Labirinto(labirinto,this);

		}
		
		public LabirintoBuilder() {
			
			mappa = new TreeMap<String,Stanza>();
			this.labirinto = new Labirinto();
			
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanza){

			Stanza s = new Stanza(nomeStanza);
			labirinto.setStanzaIniziale(s);

			mappa.put(nomeStanza, s);
			ultimaStanza = s;

			return this;

		}

		public LabirintoBuilder addStanzaVincente(String nomeStanza) {

			Stanza s = new Stanza(nomeStanza);
			labirinto.setStanzaVincente(s);

			mappa.put(nomeStanza, s);
			ultimaStanza = s;
			
			return this;
			
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso) {

			ultimaStanza.addAttrezzo(new Attrezzo(nome,peso));
			
			return this;

		}

		public LabirintoBuilder addStanza(String nomeStanza) {

			Stanza s = new Stanza(nomeStanza);
			mappa.put(nomeStanza, s);
			ultimaStanza = s;
			
			return this;

		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione dirBloccata , String attSbloccante) {

			StanzaBloccata s = new StanzaBloccata(nomeStanza, dirBloccata, attSbloccante);
			mappa.put(nomeStanza, s);
			ultimaStanza = s;
			
			return this;

		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanza) {

			StanzaMagica s = new StanzaMagica(nomeStanza);
			mappa.put(nomeStanza, s);
			ultimaStanza = s;

			return this;

		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanza, int soglia) {

			StanzaMagica s = new StanzaMagica(nomeStanza,soglia);
			mappa.put(nomeStanza, s);
			ultimaStanza = s;

			return this;

		}

		public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzo) {

			StanzaBuia s = new StanzaBuia(nomeStanza, attrezzo);
			mappa.put(nomeStanza, s);
			ultimaStanza = s;

			return this;

		}

		public LabirintoBuilder addAdiacenza(String stanza, String stanzaAdiacente, Direzione dir) {
			
			if(!dir.equals(Direzione.nord) && !dir.equals(Direzione.sud) && !dir.equals(Direzione.est) && !dir.equals(Direzione.ovest)) {
				return this;
			}
			
			Stanza ad = mappa.get(stanzaAdiacente);
			Stanza s = mappa.get(stanza);

			s.impostaStanzaAdiacente(dir, ad);

			return this;

		}

		public Labirinto getLabirinto() {

			return this.labirinto;

		}
		
		public Stanza getUltimaStanza() {
			
			return ultimaStanza;
			
		}
		
		public Map<String,Stanza> getListaStanze(){
			
			return mappa;
			
		}

	}

}