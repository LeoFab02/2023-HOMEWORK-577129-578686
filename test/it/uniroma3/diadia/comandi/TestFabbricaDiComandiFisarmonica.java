package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;

class TestFabbricaDiComandiFisarmonica {
	
	private IOConsole console;
	private FabbricaDiComandiFisarmonica f;
	private Comando comando;

	@BeforeEach
	void setUp(){
		
		console = new IOConsole();
		f = new FabbricaDiComandiFisarmonica(console);
		
	}

	@Test
	void testVai() {
	
		comando = f.costruisciComando("vai sud");
		assertTrue("vai".equals(comando.getNome()) && "sud".equals(comando.getParametro()));
		
	}
	
	@Test
	void testPrendi() {
	
		comando = f.costruisciComando("prendi osso");
		assertTrue("prendi".equals(comando.getNome()) && "osso".equals(comando.getParametro()));
		
	}
	
	@Test
	void testPosa() {
	
		comando = f.costruisciComando("posa osso");
		assertTrue("posa".equals(comando.getNome()) && "osso".equals(comando.getParametro()));
		
	}
	
	@Test
	void testNonValido() {
	
		comando = f.costruisciComando("vaii");
		assertTrue("nonValido".equals(comando.getNome()) && comando.getParametro() == null);
		
	}

	@Test
	void testFine() {
	
		comando = f.costruisciComando("fine");
		assertTrue("fine".equals(comando.getNome()) && comando.getParametro() == null);
		
	}
	
	@Test
	void testAiuto() {
	
		comando = f.costruisciComando("aiuto");
		assertTrue("aiuto".equals(comando.getNome()) && comando.getParametro() == null);
		
	}
	
	@Test
	void testGuarda() {
	
		comando = f.costruisciComando("guarda");
		assertTrue("guarda".equals(comando.getNome()) && comando.getParametro() == null);
		
	}
	
}
