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

class TestComandoPosa {
	
	private LabirintoBuilder lb;
	
	private Partita partita;
	private ComandoPosa posa;
	private IO console;

	@BeforeEach
	void setUp() throws FormatoFileNonValidoException, IOException{
		
		lb = new LabirintoBuilder();
		Labirinto l = lb
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				.getLabirinto();
		
		partita = new Partita(l);
		console = new IOConsole(new Scanner(System.in));
		posa = new ComandoPosa();
		posa.setConsole(console);
		
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
		
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("A",2));
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("B",2));
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("C",2));
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("D",2));
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("E",2));
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("F",2));
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("G",2));
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("H",2));
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("I",2));
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("J",2));
		
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
