package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.fixture.Fixture;

public class TestComandoAiuto {

	List<String> righeDaLeggere;

	@Before
	public void setUp() throws Exception {
		righeDaLeggere = new ArrayList<String>();
	}

	@Test
	public void testPartitaConComandoAiuto() {
		righeDaLeggere.add("aiuto");
		righeDaLeggere.add("fine");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaFacile(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());

		for(int i=0; i < ComandoAiuto.getElencoComandi().length; i++) {
			assertTrue(io.hasNextMessaggio());
			assertEquals(ComandoAiuto.getElencoComandi()[i], io.nextMessaggio());
		}

		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.messaggioFine(), io.nextMessaggio());

	}

}
