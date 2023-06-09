package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.fixture.Fixture;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestComandoFine {

	List<String> righeDaLeggere;

	@BeforeEach
	public void setUp() throws Exception {
		righeDaLeggere = new ArrayList<>();
	}

	@Test
	public void testPartitaConComandoFine() throws Exception {
		righeDaLeggere.add("fine");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaEasy(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.messaggioFine(), io.nextMessaggio());

	}

}
