package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoFine extends AbstractComando {
	
	private IO console;
	
	@Override
	public void esegui(Partita partita) {
		
		console.mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita(); // si desidera smettere
		
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
	
	public static String messaggioFine() {
		return "Grazie di aver giocato!";
	}
	
	@Override
	public void setConsole(IO console) {
		this.console = console;
	}

}
