package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestComandoPosa {
	
	private Partita partita;
	private ComandoPosa posa;
	private IO console;

	@BeforeEach
	void setUp(){
		
		partita = new Partita();
		console = new IOConsole();
		posa = new ComandoPosa(console);
		
	}

	@Test
	void testPosaAttrezzo() {
		
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("lanterna",1));
		posa.setParametro("lanterna");
		posa.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("lanterna"));
		
	}
	
	@Test
	void testNonPosaStanzaPiena() {
		
		for(int i=0;i<9;i++) {
			
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("lanterna",1));
			
		}
		
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("martello",1));
		posa.setParametro("martello");
		posa.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
		
	}
	
	@Test
	void testNonPosaBorsaVuota() {
		
		posa.setParametro("lanterna");
		posa.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("lanterna"));
		
	}

}
