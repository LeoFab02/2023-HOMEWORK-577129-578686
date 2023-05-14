package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLabirintoBuilder {
	
	private LabirintoBuilder lb;

	@BeforeEach
	void setUp(){
	
		lb = new LabirintoBuilder();
		
	}

	@Test
	void testAddStanzaIniziale() {
		
		lb.addStanzaIniziale("Atrio");
		
		assertEquals("Atrio", lb.getLabirinto().getStanzaIniziale().getNome());
		
	}
	
	@Test
	void testAddStanzaVincente() {
		
		lb.addStanzaVincente("Biblioteca");
		
		assertEquals("Biblioteca", lb.getLabirinto().getStanzaVincente().getNome());
		
	}
	
	@Test
	void testAddStanzaBuia() {
		
		lb.addStanzaBuia("Bagno", "Candela");
		
		assertEquals("Bagno", lb.getUltimaStanza().getNome());
		
	}
	
	@Test
	void testAddStanzaBloccata() {
		
		lb.addStanzaBloccata("Cantina", "est", "Chiave");
		
		assertEquals("Cantina", lb.getUltimaStanza().getNome());
		
	}
	
	@Test
	void testAddStanza() {
		
		lb.addStanza("N10");
		
		assertEquals("N10", lb.getUltimaStanza().getNome());
		
	}
	
	@Test
	void testAddStanzaMagica() {
		
		lb.addStanzaMagica("N11");
		
		assertEquals("N11", lb.getUltimaStanza().getNome());
		
	}
	
	@Test
	void testAddAttrezzo() {
		
		lb.addStanza("N6");
		lb.addAttrezzo("Martello",2);
		
		assertEquals("Martello", lb.getUltimaStanza().getAttrezzo("Martello").getNome());
		
	}
	
	@Test
	void testAddAdiacenza() {
		
		lb.addStanza("N11");
		lb.addStanza("N12");
		
		lb.addAdiacenza("N12", "N11", "nord");
		
		assertEquals("N11", lb.getUltimaStanza().getStanzaAdiacente("nord").getNome());
		
	}

}
