package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;
import java.util.Iterator;

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
	void testAddAttrezzoSpazioDisponibile() {
		
		assertTrue(borsa.addAttrezzo(new Attrezzo("Spada",3)));
	
	}
	
	@Test
	void testAddAttrezzoTroppoPeso() {
		
		borsa.addAttrezzo(new Attrezzo("Martello",7));
		assertFalse(borsa.addAttrezzo(new Attrezzo("Spada",4)));
		
	}
	
	@Test
	void testRemoveAttrezzoEsistente() {
		
		Attrezzo a = new Attrezzo("Spada",3);
		
		borsa.addAttrezzo(a);
		assertSame(a,borsa.removeAttrezzo("Spada"));
	
	}
	
	@Test
	void testRemoveAttrezzoNonEsistente() {
		
		Attrezzo a = new Attrezzo("Spada",3);
		
		borsa.addAttrezzo(a);
		assertNull(borsa.removeAttrezzo("Martello"));
		
	}
	
	@Test
	void testRemoveAttrezzoBorsaSenzaAttrezzi() {
		
		assertNull(borsa.removeAttrezzo("Martello"));
		
	}
	
	@Test
	void testHasAttrezzoEsistente() {
		
		borsa.addAttrezzo(new Attrezzo("Spada",3));
		assertTrue(borsa.hasAttrezzo("Spada"));
	
	}
	
	@Test
	void testHasAttrezzoNonEsistente() {
		
		borsa.addAttrezzo(new Attrezzo("Martello",3));
		assertFalse(borsa.hasAttrezzo("Spada"));
		
	}
	
	@Test
	void testHasAttrezzoBorsaSenzaAttrezzi() {
		
		assertFalse(borsa.hasAttrezzo("Spada"));
		
	}
	
	@Test
	void testIsEmptyBorsaVuota() {
		
		assertTrue(borsa.isEmpty());
		
	}
	
	@Test
	void testIsEmptyBorsaNonVuota() {
		
		borsa.addAttrezzo(new Attrezzo("Martello",2));
		assertFalse(borsa.isEmpty());
		
	}
	
	@Test
	void testGetSortedTestOrdinatoPerNome() {
		
		borsa.addAttrezzo(new Attrezzo("piuma",1));
		borsa.addAttrezzo(new Attrezzo("osso",1));
		
		SortedSet<Attrezzo> s = borsa.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = s.iterator();
				
		assertEquals("osso", it.next().getNome());
		assertEquals("piuma", it.next().getNome());
		
	}

}