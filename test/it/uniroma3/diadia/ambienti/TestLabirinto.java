package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLabirinto {
	
	private Labirinto labirinto;

	@BeforeEach
	void setUp(){
		
		
		labirinto = new Labirinto();
		Stanza s = new Stanza("Atrio");
		Stanza s2 = new Stanza("Biblioteca");
		
		labirinto.setStanzaIniziale(s);
		labirinto.setStanzaVincente(s2);
		
	}

	@Test
	void testGetStanzaVincente() {
		
		
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
		
	}
	
	@Test
	void testGetStanzaIniziale() {
		
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
		
	}

}