package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestComandoVai {
	
	private ComandoVai vai;
	private IO console;
	private Partita partita;

	@BeforeEach
	void setUp(){
		
		partita = new Partita();
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

}
