package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.io.IOException;
import java.util.Scanner;

class TestComandoPrendi {
	
	private LabirintoBuilder lb;
	
	private Partita partita;
	private IO console;
	private ComandoPrendi prendi;

	@BeforeEach
	void setUp() throws FormatoFileNonValidoException, IOException{
		
		lb = new LabirintoBuilder();
		Labirinto l = lb
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.getLabirinto();
		
		partita = new Partita(l);
		console = new IOConsole(new Scanner(System.in));
		prendi = new ComandoPrendi();
		prendi.setConsole(console);
		
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
