package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaBuia {
	
	private Attrezzo lanterna;
	private StanzaBuia atrio;

	@BeforeEach
	void setUp(){
		
		lanterna = new Attrezzo("lanterna",3);
		atrio = new StanzaBuia("Atrio","lanterna");
		
	}
	
	@Test
	void testDescrizioneOggettoPresente() {
		
		atrio.addAttrezzo(lanterna);
		assertEquals(atrio.toString(), atrio.getDescrizione());
		
	}

	@Test
	void testDescrizioneOggettoNonPresente() {

		assertEquals("c'e' un buio pesto qui\n", atrio.getDescrizione());
		
	}

}
