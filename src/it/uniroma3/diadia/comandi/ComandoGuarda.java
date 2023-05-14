package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import java.util.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda implements Comando{
	
	private String parametro;
	private IO console;
	
	public ComandoGuarda(IO console) {
		
		this.console = console;
		
	}
	
	@Override
	public void esegui(Partita partita) {
		
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		
	
		console.mostraMessaggio("Contenuto Borsa: " + partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso().toString());
		//console.mostraMessaggio(partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso().toString());

		console.mostraMessaggio("CFU: " + partita.getGiocatore().getCfu());
		
	}
	
	@Override
	public void setParametro(String parametro) {
		
		this.parametro = parametro;
		
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}

}
