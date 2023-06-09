package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.fixture.Fixture;


import org.junit.jupiter.api.BeforeEach;


class TestComandoAiuto {

	List<String> righeDaLeggere;

	@BeforeEach
	void setUp() throws Exception {
		righeDaLeggere = new ArrayList<String>();
	}

	@Test
	void testPartitaConComandoAiuto() throws Exception {
		righeDaLeggere.add("aiuto");
		righeDaLeggere.add("fine");
		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaEasy(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());
		for(int i=0; i < ComandoAiuto.getElencoComandi().length; i++) {
			assertTrue(io.hasNextMessaggio());
			assertEquals(ComandoAiuto.getElencoComandi()[i]+" ", io.nextMessaggio());
		}
		assertTrue(io.hasNextMessaggio());
		io.nextMessaggio();
		assertEquals(ComandoFine.messaggioFine(), io.nextMessaggio());
	}

}
