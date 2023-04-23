package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaBloccata {
	
	private Attrezzo chiave;
	
	private Stanza stanzaAdiacente;
	private StanzaBloccata stanza;

	@BeforeEach
	void setUp(){
		
		chiave = new Attrezzo("chiave",1);
		
		stanza = new StanzaBloccata("stanza","est","chiave");
		stanzaAdiacente = new Stanza("stanzaAdiacente");
		
		stanza.impostaStanzaAdiacente("est",stanzaAdiacente);
		
		
	}

	@Test
	void testDirezioneNonBloccata() {
		
		stanza.addAttrezzo(chiave);
		assertEquals(stanzaAdiacente, stanza.getStanzaAdiacente("est"));
		
	}
	
	@Test
	void testDirezioneBloccata() {
		
		assertEquals(stanza, stanza.getStanzaAdiacente("est"));
		
	}

}
