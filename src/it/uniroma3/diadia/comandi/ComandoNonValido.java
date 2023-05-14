package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	
	private IO console;
	private String parametro;
	
	public ComandoNonValido(IO console) {
		
		this.console = console;
		
	}

	@Override
	public void esegui(Partita partita) {
		
		console.mostraMessaggio("Comando Non Valido");
		
	}
	
	public void setParametro(String parametro) {
		
		this.parametro = parametro;
		
	}
	
	@Override
	public String getNome() {
		return "nonValido";
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	
}
