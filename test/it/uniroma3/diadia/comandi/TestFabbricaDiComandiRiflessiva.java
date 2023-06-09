package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import it.uniroma3.diadia.IOConsole;

class TestFabbricaDiComandiRiflessiva {
	
	private IOConsole console;
	private FabbricaDiComandiRiflessiva f;
	private Comando comando;

	@BeforeEach
	void setUp(){
		
		console = new IOConsole(new Scanner(System.in));
		f = new FabbricaDiComandiRiflessiva(console);
		
	}

	@Test
	void testVai() throws Exception {
	
		comando = f.costruisciComando("vai sud");
		assertTrue("vai".equals(comando.getNome()) && "sud".equals(comando.getParametro()));
		
	}
	
	@Test
	void testPrendi() throws Exception {
	
		comando = f.costruisciComando("prendi osso");
		assertTrue("prendi".equals(comando.getNome()) && "osso".equals(comando.getParametro()));
		
	}
	
	@Test
	void testPosa() throws Exception {
	
		comando = f.costruisciComando("posa osso");
		assertTrue("posa".equals(comando.getNome()) && "osso".equals(comando.getParametro()));
		
	}
	
	@Test
	void testNonValido() throws Exception {
	
		comando = f.costruisciComando("nonValido");
		assertTrue("nonValido".equals(comando.getNome()) && comando.getParametro() == null);
		
	}

	@Test
	void testFine() throws Exception {
	
		comando = f.costruisciComando("fine");
		assertTrue("fine".equals(comando.getNome()) && comando.getParametro() == null);
		
	}
	
	@Test
	void testAiuto() throws Exception {
	
		comando = f.costruisciComando("aiuto");
		assertTrue("aiuto".equals(comando.getNome()) && comando.getParametro() == null);
		
	}
	
	@Test
	void testGuarda() throws Exception {
	
		comando = f.costruisciComando("guarda");
		assertTrue("guarda".equals(comando.getNome()) && comando.getParametro() == null);
		
	}
	
}
