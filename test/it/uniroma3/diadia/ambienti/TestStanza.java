package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanza {
	
	private Stanza stanza;
	
	@BeforeEach
	void setUp(){
		
		stanza = new Stanza("Prova");
		
	}

	@Test
	void testImpostaStanzaAdiacenteGiusto() {
		
		stanza.impostaStanzaAdiacente( "nord" , new Stanza("prova2") );
		assertEquals( "prova2" , stanza.getStanzaAdiacente("nord").getNome() );
		
	}
	
	@Test
	void testImpostaStanzaAdiacenteSbagliato() {
		
		
		stanza.impostaStanzaAdiacente( "nord" , new Stanza("prova2") );
		assertFalse(stanza.getStanzaAdiacente("nord").getNome().equals("prova1") );
		
	}
	
	@Test
	void testImpostaStanzaAdiacenteNessunaStanzaInserita() {
		
		stanza.impostaStanzaAdiacente( "nord" , null );
		assertNull(stanza.getStanzaAdiacente("nord"));
		
	}
	
	@Test
	void testHasAttrezzoEsistente() {
		
		stanza.addAttrezzo(new Attrezzo("Martello",3));
		assertTrue(stanza.hasAttrezzo("Martello"));
		
	}
	
	@Test
	void testHasAttrezzoNonEsistente() {
		
		stanza.addAttrezzo(new Attrezzo("Martello",3));
		assertFalse(stanza.hasAttrezzo("Spada"));
		
	}
	
	@Test
	void testHasAttrezzoStanzaSenzaAttrezzi() {
		
		assertFalse(stanza.hasAttrezzo("Spada"));
		
	}
	
	@Test
	void testAddAttrezzoStanzaVuota() {
		
		assertTrue(stanza.addAttrezzo(new Attrezzo("Martello",3)));
		
	}
	
	@Test
	void testAddAttrezzoStanzaPiena() {
		
		for(int i=0;i<stanza.getAttrezzi().length;i++) {
			stanza.addAttrezzo(new Attrezzo("Martello",2));
		}
		
		assertFalse(stanza.addAttrezzo(new Attrezzo("Spada",3)));
		
	}
	
	@Test
	void testRemoveAttrezzoEsistente() {
		
		stanza.addAttrezzo(new Attrezzo("Spada",2));
		assertTrue(stanza.removeAttrezzo(stanza.getAttrezzo("Spada")));
		
	}
	
	@Test
	void testRemoveAttrezzoNonEsistente() {
		
		stanza.addAttrezzo(new Attrezzo("Spada",2));
		assertFalse(stanza.removeAttrezzo(stanza.getAttrezzo("Martello")));
		
	}
	
	@Test
	void testRemoveAttrezzoStanzaSenzaAttrezzi() {
		
		assertFalse(stanza.removeAttrezzo(stanza.getAttrezzo("Martello")));
		
	}
	
	

}