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

		s.addAttrezzo(new Attrezzo("A",2));
		s.addAttrezzo(new Attrezzo("B",2));
		s.addAttrezzo(new Attrezzo("C",2));
		s.addAttrezzo(new Attrezzo("D",2));
		s.addAttrezzo(new Attrezzo("E",2));
		s.addAttrezzo(new Attrezzo("F",2));
		s.addAttrezzo(new Attrezzo("G",2));
		s.addAttrezzo(new Attrezzo("H",2));
		s.addAttrezzo(new Attrezzo("I",2));
		s.addAttrezzo(new Attrezzo("J",2));

		assertFalse(s.addAttrezzo(new Attrezzo("Spada",3)));

	}
	
	@Test
	void testImpostaStanzaAdiacenteGiusto() {

		s.impostaStanzaAdiacente( Direzione.nord , new Stanza("prova2") );
		assertEquals( "prova2" , s.getStanzaAdiacente(Direzione.nord).getNome() );

	}

	@Test
	void testImpostaStanzaAdiacenteSbagliato() {


		s.impostaStanzaAdiacente( Direzione.nord , new Stanza("prova2") );
		assertFalse(s.getStanzaAdiacente(Direzione.nord).getNome().equals("prova1") );

	}

	@Test
	void testImpostaStanzaAdiacenteNessunaStanzaInserita() {

		s.impostaStanzaAdiacente( Direzione.nord , null );
		assertNull(s.getStanzaAdiacente(Direzione.nord));

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
