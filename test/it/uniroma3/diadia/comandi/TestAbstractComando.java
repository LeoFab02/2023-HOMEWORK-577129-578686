package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class TestAbstractComando {
	
	private AbstractComando comando;

	@BeforeEach
	void setUp() throws Exception {
		
		comando = new ComandoFine();
		comando.setParametro("prova");
		
	}

	@Test
	void testSetParametro() {
		
		comando.setParametro(null);
		assertNull(comando.getParametro());
		
	}
	
	@Test
	void testGetParametro() {
		
		assertEquals(null,comando.getParametro());
		
	}

}
