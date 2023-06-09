package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class TestPartita {

	Labirinto labirinto;
	Partita p;
	Stanza s;

	@BeforeEach
	public void setUp() throws FormatoFileNonValidoException, IOException {
		 labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
//				.addStanzaIniziale("Atrio")
//				.addAttrezzo("martello", 3)
//				.addStanzaVincente("Biblioteca")
//				.addAdiacenza("Atrio", "Biblioteca", "nord")
//				.getLabirinto();
		 p = new Partita(labirinto);
		 s = new Stanza("Stanza");
	}

	@Test
	void testVintaStanzaCorrenteUgualeAStanzaVincente() {

		p.setStanzaCorrente(p.getLabirinto().getStanzaVincente());
		assertTrue(p.vinta());

	}

	@Test
	void testVintaStanzaCorrenteNonUgualeAStanzaVincente() {

		assertFalse(p.vinta());

	}

	@Test
	void testIsFinitaTuttiFalse() {

		assertFalse(p.isFinita());

	}

	@Test
	void testIsFinitaTrue() {

		p.setFinita();
		assertTrue(p.isFinita());

	}

	@Test
	void testIsFinitaZeroCfu() {

		p.getGiocatore().setCfu(0);
		assertTrue(p.isFinita());

	}

}
