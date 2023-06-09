package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.*;

import java.io.IOException;

import it.uniroma3.diadia.ConfigurazioneProperties;

public class Giocatore{
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() throws IOException {
		
		borsa = new Borsa();
		this.cfu = ConfigurazioneProperties.getCFU();
		
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	

}