package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestComandoPrendi {
	
	private Partita partita;
	private IO console;
	private ComandoPrendi prendi;

	@BeforeEach
	void setUp(){
		
		partita = new Partita();
		console = new IOConsole();
		prendi = new ComandoPrendi(console);
		
	}

	@Test
	void testAttrezzoPreso() {
		
		prendi.setParametro("osso");
		prendi.esegui(partita);
		
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		
	}
	
	@Test
	void testPrendiBorsaPiena() {
		
		for(int i=0;i<10;i++) {
			partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("lanterna",1));
		}
		
		prendi.setParametro("osso");
		prendi.esegui(partita);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		
	}

}
