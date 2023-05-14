package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestComandoVai {

	private LabirintoBuilder lb;
	List<String> righeDaLeggere;

	private ComandoVai vai;
	private IO console;
	private Partita partita;

	@BeforeEach
	void setUp(){

		lb = new LabirintoBuilder();
		Labirinto l = lb
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N10")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();

		partita = new Partita(l);
		console = new IOConsole();
		vai = new ComandoVai(console);

	}

	@Test
	void testEseguiVerificaProssimaStanza() {

		vai.setParametro("sud");
		vai.esegui(partita);

		assertEquals("Aula N10",partita.getStanzaCorrente().getNome());

	}

	@Test
	void testEseguiNonCambiaStanza() {

		vai.esegui(partita);

		assertEquals("Atrio",partita.getStanzaCorrente().getNome());

	}

	@Test
	void testEseguiVerificaCFU() {

		vai.setParametro("sud");
		vai.esegui(partita);

		assertEquals(19,partita.getGiocatore().getCfu());

	}

	@Test
	void testEseguiVerificaNonCambiaCFU() {

		vai.esegui(partita);

		assertEquals(20,partita.getGiocatore().getCfu());

	}

	@Test
	void testPartitaConComandoVai() {

		righeDaLeggere = new ArrayList<String>();
		righeDaLeggere.add("vai nord");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaFacile(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Biblioteca\nUscite:  sud\nAttrezzi nella stanza: ", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa:", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa vuota", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Hai vinto!", io.nextMessaggio());

	}

	@Test
	void testPartitaConComandoVaiOvest() {

		righeDaLeggere = new ArrayList<String>();
		righeDaLeggere.add("vai ovest");
		righeDaLeggere.add("fine");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaDifficile(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio\nUscite:  est\nAttrezzi nella stanza: ", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa:", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa vuota", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.messaggioFine(), io.nextMessaggio());
	}

	@Test
	void testPartitaConComandoVaiOvestEst() {

		righeDaLeggere = new ArrayList<String>();
		righeDaLeggere.add("vai ovest");
		righeDaLeggere.add("vai est");
		righeDaLeggere.add("fine");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaDifficile(righeDaLeggere);

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio\nUscite:  est\nAttrezzi nella stanza: ", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa:", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa vuota", io.nextMessaggio());
		assertEquals("Atrio\nUscite:  nord ovest\nAttrezzi nella stanza: martello (3kg) ", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa:", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa vuota", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.messaggioFine(), io.nextMessaggio());

	}

}
