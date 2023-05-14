package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestComandoPrendi {
	
	private LabirintoBuilder lb;
	
	private Partita partita;
	private IO console;
	private ComandoPrendi prendi;

	@BeforeEach
	void setUp(){
		
		lb = new LabirintoBuilder();
		Labirinto l = lb
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		
		partita = new Partita(l);
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
    public void TestNonPrendiAttrezzo() {
        prendi.setParametro("lanterna");
        prendi.esegui(partita);
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
    }
	
	@Test
	void testStanzaSenzaAttrezzi() {
		
		Attrezzo osso = new Attrezzo("osso",1);
		
		partita.getStanzaCorrente().removeAttrezzo(osso);
		
		prendi.setParametro("osso");
		prendi.esegui(partita);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
		
	}

}
