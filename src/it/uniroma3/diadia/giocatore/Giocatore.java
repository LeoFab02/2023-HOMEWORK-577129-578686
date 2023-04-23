package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.*;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		
		borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
		
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