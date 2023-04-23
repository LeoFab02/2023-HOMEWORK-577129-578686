package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoFine implements Comando {
	
	private IO console;
	private String parametro;
	
	public ComandoFine(IO console) {
		this.console = console;
	}
	
	@Override
	public void esegui(Partita partita) {
		
		console.mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita(); // si desidera smettere
		
	}
	
	@Override
	public void setParametro(String parametro) {
		
		this.parametro = parametro;
		
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}

}
