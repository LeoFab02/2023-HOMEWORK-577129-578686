package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

class TestComandoVai {
	
	private LabirintoBuilder lb;
	List<String> righeDaLeggere;
	
	private ComandoVai vai;
	private IO console;
	private Partita partita;

	@BeforeEach
	void setUp() throws FormatoFileNonValidoException, IOException{
		
		lb = new LabirintoBuilder();
		Labirinto l = lb
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N10")
				.addAdiacenza("Atrio", "Aula N10", Direzione.sud)
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.getLabirinto();
		
		partita = new Partita(l);
		console = new IOConsole(new Scanner(System.in));
		vai = new ComandoVai();
		vai.setConsole(console);	
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
	void testPartitaConComandoVai() throws Exception {
		
		righeDaLeggere = new ArrayList<String>();
		righeDaLeggere.add("vai nord");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaEasy(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Biblioteca\nUscite:  sud\nAttrezzi nella stanza: ", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa:Borsa vuota", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Hai vinto!", io.nextMessaggio());
		
	}
	
	@Test
	void testPartitaConComandoVaiOvest() throws Exception {
		
		righeDaLeggere = new ArrayList<String>();
		righeDaLeggere.add("vai ovest");
		righeDaLeggere.add("fine");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaHard(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio\nUscite:  est\nAttrezzi nella stanza: ", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa:Borsa vuota", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.messaggioFine(), io.nextMessaggio());
	}
	
	@Test
	void testPartitaConComandoVaiOvestEst() throws Exception {
		
		righeDaLeggere = new ArrayList<String>();
		righeDaLeggere.add("vai ovest");
		righeDaLeggere.add("vai est");
		righeDaLeggere.add("fine");

		IOSimulator io = Fixture.creaSimulazionePartitaEGiocaHard(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.getMessaggioBenvenuto(), io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Studio\nUscite:  est\nAttrezzi nella stanza: ", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa:Borsa vuota", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Atrio\nUscite:  nord ovest\nAttrezzi nella stanza: martello (3kg) ", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Borsa:Borsa vuota", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.messaggioFine(), io.nextMessaggio());
	}

}
