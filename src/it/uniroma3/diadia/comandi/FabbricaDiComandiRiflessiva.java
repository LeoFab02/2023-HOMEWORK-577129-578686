package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{
	
	private IO console;
	
	public FabbricaDiComandiRiflessiva(IO console) {
		
		this.console = console;
		
	}

	@Override
	public Comando costruisciComando(String istruzione) throws Exception{
		
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale param.
		
		/*if (nomeComando == null)
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
			comando.setParametro(parametro);*/
		try {
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		
		nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
		
		nomeClasse.append( nomeComando.substring(1) ) ;
	
		
		comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
		comando.setParametro(parametro);
		}catch(Exception e) {
			comando = new ComandoNonValido();
		}
		comando.setConsole(console);

		return comando;
			
	}

}
