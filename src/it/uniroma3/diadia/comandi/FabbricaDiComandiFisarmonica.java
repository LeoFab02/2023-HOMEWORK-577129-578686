package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	
	private IO console;
	
	public FabbricaDiComandiFisarmonica(IO console) {
		
		this.console = console;
		
	}

	@Override
	public Comando costruisciComando(String istruzione) {
		
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale param.
		
		if (nomeComando == null)
			comando = new ComandoNonValido(console);
			else if (nomeComando.equals("vai"))
			comando = new ComandoVai(console);
			else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi(console);
			else if (nomeComando.equals("posa"))
			comando = new ComandoPosa(console);
			else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto(console);
			else if (nomeComando.equals("fine"))
			comando = new ComandoFine(console);
			else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda(console);
			else comando = new ComandoNonValido(console);
			comando.setParametro(parametro);

			return comando;
			
	}

}
