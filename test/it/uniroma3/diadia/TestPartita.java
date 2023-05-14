package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class TestPartita {

	private Partita partita;
	private LabirintoBuilder lb;

	@BeforeEach
	void setUp() {

		lb = new LabirintoBuilder();
		
		Labirinto l = lb
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		
		partita = new Partita(l);

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
