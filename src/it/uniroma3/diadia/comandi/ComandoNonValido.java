package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{
	
	private IO console;

	@Override
	public void esegui(Partita partita) {
		
		console.mostraMessaggio("Comando non valido");
		
	}
	
	@Override
	public String getNome() {
		return "nonValido";
	}
	
	@Override
	public void setConsole(IO console) {
		this.console = console;
	}
	
	
}
