package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto extends AbstractComando{
	
	private IO console;

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "saluta", "regala", "interagisci"};
	
	@Override
	public void esegui(Partita partita) {
		
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]);
		
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	public static String[] getElencoComandi() {
		return elencoComandi;
	}
	
	@Override
	public void setConsole(IO console) {
		this.console = console;
	}
	
}
