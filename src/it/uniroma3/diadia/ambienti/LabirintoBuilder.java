package it.uniroma3.diadia.ambienti;

import java.util.Map;
import java.util.List;
import java.util.TreeMap;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.HashMap;

public class LabirintoBuilder{

	private Labirinto labirinto;
	private Map<String,Stanza> mappa;
	private Stanza ultimaStanza;

	public LabirintoBuilder() {

		labirinto = new Labirinto();
		mappa = new TreeMap<String,Stanza>();

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

	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String dirBloccata , String attSbloccante) {

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

	public LabirintoBuilder addAdiacenza(String stanza, String stanzaAdiacente, String direzione) {
		
		if(!direzione.equals("nord") && !direzione.equals("sud") && !direzione.equals("est") && !direzione.equals("ovest")) {
			return this;
		}
		
		Stanza ad = mappa.get(stanzaAdiacente);
		Stanza s = mappa.get(stanza);

		s.impostaStanzaAdiacente(direzione, ad);

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
