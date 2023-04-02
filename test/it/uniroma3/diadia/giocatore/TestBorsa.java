package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestBorsa {
	
	private Borsa borsa;

	@BeforeEach
	void setUp(){
		
		borsa = new Borsa();
		
	}

	@Test
	void testAddAttrezzo() {
		
		assertTrue(borsa.addAttrezzo(new Attrezzo("Spada",3)));
	
	}
	
	@Test
	void testRemoveAttrezzo() {
		
		Attrezzo a = new Attrezzo("Spada",3);
		
		borsa.addAttrezzo(a);
		assertSame(a,borsa.removeAttrezzo("Spada"));
	
	}
	
	@Test
	void testHasAttrezzo() {
		
		borsa.addAttrezzo(new Attrezzo("Spada",3));
		assertTrue(borsa.hasAttrezzo("Spada"));
	
	}

}
