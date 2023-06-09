package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import java.util.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuarda extends AbstractComando{

	private IO console;
	
	@Override
	public void esegui(Partita partita) {
		
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		
	
		console.mostraMessaggio("Contenuto Borsa: " + partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso().toString());
		
		
		console.mostraMessaggio("CFU: " + partita.getGiocatore().getCfu());
		
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public void setConsole(IO console) {
		this.console = console;
	}

}
