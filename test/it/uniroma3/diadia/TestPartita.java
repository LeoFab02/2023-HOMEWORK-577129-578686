package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPartita {

	private Partita partita;

	@BeforeEach
	void setUp() {

		partita = new Partita();

	}

	@Test
	void testVintaStanzaCorrenteUgualeAStanzaVincente() {

		partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.vinta());

	}

	@Test
	void testVintaStanzaCorrenteNonUgualeAStanzaVincente() {

		assertFalse(partita.vinta());

	}

	@Test
	void testIsFinitaTuttiFalse() {

		assertFalse(partita.isFinita());

	}

	@Test
	void testIsFinitaTrue() {

		partita.setFinita();
		assertTrue(partita.isFinita());

	}

	@Test
	void testIsFinitaZeroCfu() {

		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());

	}

}
