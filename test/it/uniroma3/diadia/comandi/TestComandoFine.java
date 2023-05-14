package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.fixture.Fixture;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TestComandoFine {

	List<String> righeDaLeggere;

	@Before
	public void setUp() throws Exception {
		/*righeDaLeggere = new ArrayList<>();
		righeDaLeggere.add("fine");*/
	}

	@Test
	public void testPartitaConComandoFine() {

		righeDaLeggere = new ArrayList<>();
		righeDaLeggere.add("fine");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaFacile(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.messaggioFine(), io.nextMessaggio());

	}

}
