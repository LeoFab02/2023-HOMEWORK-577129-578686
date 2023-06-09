package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.ambienti.*;

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

		stanza.impostaStanzaAdiacente( Direzione.nord , new Stanza("prova2") );
		assertEquals( "prova2" , stanza.getStanzaAdiacente(Direzione.nord).getNome() );

	}

	@Test
	void testImpostaStanzaAdiacenteSbagliato() {


		stanza.impostaStanzaAdiacente( Direzione.nord , new Stanza("prova2") );
		assertFalse(stanza.getStanzaAdiacente(Direzione.nord).getNome().equals("prova1") );

	}

	@Test
	void testImpostaStanzaAdiacenteNessunaStanzaInserita() {

		stanza.impostaStanzaAdiacente( Direzione.nord , null );
		assertNull(stanza.getStanzaAdiacente(Direzione.nord));

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

		stanza.addAttrezzo(new Attrezzo("A",2));
		stanza.addAttrezzo(new Attrezzo("B",2));
		stanza.addAttrezzo(new Attrezzo("C",2));
		stanza.addAttrezzo(new Attrezzo("D",2));
		stanza.addAttrezzo(new Attrezzo("E",2));
		stanza.addAttrezzo(new Attrezzo("F",2));
		stanza.addAttrezzo(new Attrezzo("G",2));
		stanza.addAttrezzo(new Attrezzo("H",2));
		stanza.addAttrezzo(new Attrezzo("I",2));
		stanza.addAttrezzo(new Attrezzo("J",2));

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