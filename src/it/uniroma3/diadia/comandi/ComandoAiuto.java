package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto implements Comando{
	
	private IO console;
	private String parametro;
	
	public ComandoAiuto(IO console) {
		this.console = console;
	}

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	
	@Override
	public void esegui(Partita partita) {
		
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]);
		
	}
	
	@Override
	public void setParametro(String parametro) {
		
		this.parametro = parametro;
		
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	public static String[] getElencoComandi() {
		return elencoComandi;
	}
	
}
