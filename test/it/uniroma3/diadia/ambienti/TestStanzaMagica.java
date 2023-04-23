package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaMagica {
	
	private StanzaMagica s;

	@BeforeEach
	void setUp(){
		
		s = new StanzaMagica("stanzaMagica",1);
		
	}

	@Test
	void testAddAttrezzoNonInvertito() {
		
		s.addAttrezzo(new Attrezzo("osso",1));
		assertTrue(s.hasAttrezzo("osso"));
		
	}
	
	@Test
	void testAddAttrezzoInvertito() {
		
		s.addAttrezzo(new Attrezzo("osso",1));
		s.addAttrezzo(new Attrezzo("chiave",1));
		
		assertTrue(s.hasAttrezzo("evaihc"));
		
	}
	
	@Test
	void testAddAttrezzoStanzaPiena() {

		for(int i=0;i<s.getAttrezzi().length;i++) {
			s.addAttrezzo(new Attrezzo("Martello",2));
		}

		assertFalse(s.addAttrezzo(new Attrezzo("Spada",3)));

	}
	
	@Test
	void testImpostaStanzaAdiacenteGiusto() {

		s.impostaStanzaAdiacente( "nord" , new Stanza("prova2") );
		assertEquals( "prova2" , s.getStanzaAdiacente("nord").getNome() );

	}

	@Test
	void testImpostaStanzaAdiacenteSbagliato() {


		s.impostaStanzaAdiacente( "nord" , new Stanza("prova2") );
		assertFalse(s.getStanzaAdiacente("nord").getNome().equals("prova1") );

	}

	@Test
	void testImpostaStanzaAdiacenteNessunaStanzaInserita() {

		s.impostaStanzaAdiacente( "nord" , null );
		assertNull(s.getStanzaAdiacente("nord"));

	}
	
	@Test
	void testHasAttrezzoEsistente() {

		s.addAttrezzo(new Attrezzo("Martello",3));
		assertTrue(s.hasAttrezzo("Martello"));

	}

	@Test
	void testHasAttrezzoNonEsistente() {

		s.addAttrezzo(new Attrezzo("Martello",3));
		assertFalse(s.hasAttrezzo("Spada"));

	}

	@Test
	void testHasAttrezzoStanzaSenzaAttrezzi() {

		assertFalse(s.hasAttrezzo("Spada"));

	}
	
	@Test
	void testRemoveAttrezzoEsistenteNonInvertito() {

		s.addAttrezzo(new Attrezzo("Spada",2));
		assertTrue(s.removeAttrezzo(s.getAttrezzo("Spada")));

	}
	
	@Test
	void testRemoveAttrezzoEsistenteInvertito() {

		s.addAttrezzo(new Attrezzo("Spada",2));
		assertTrue(s.removeAttrezzo(s.getAttrezzo("Spada")));

	}

	@Test
	void testRemoveAttrezzoNonEsistente() {

		s.addAttrezzo(new Attrezzo("Spada",2));
		assertFalse(s.removeAttrezzo(s.getAttrezzo("Martello")));

	}

	@Test
	void testRemoveAttrezzoStanzaSenzaAttrezzi() {

		assertFalse(s.removeAttrezzo(s.getAttrezzo("Martello")));

	}

}
