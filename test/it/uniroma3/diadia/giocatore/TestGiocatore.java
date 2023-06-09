package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGiocatore {
	
	private Giocatore giocatore;

	@BeforeEach
	void setUp() throws IOException{
		
		giocatore = new Giocatore();
		
	}

	@Test
	void testGetCFU() {
		
		assertEquals(20, giocatore.getCfu());
		
	}
	
	@Test
	void testSetCFU() {
		
		giocatore.setCfu(19);
		assertEquals(19,giocatore.getCfu());
		
	}

}
