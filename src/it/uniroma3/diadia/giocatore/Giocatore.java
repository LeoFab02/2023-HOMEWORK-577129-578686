package it.uniroma3.diadia.giocatore;

/**
 * Classe Giocatore - Ha la responsabilità di gestire i CFU del giocatore e
 * di memorizzare gli attrezzi in un oggetto istanza della classe Borsa
 * @version base
*/

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	
	/**
	 * Costruttore della classe giocatore.
	 * Inizializza l'oggetto variabile d'istanza borsa e
	 * la variabile cfu a quelli iniziali cioè 20.
	*/
	public Giocatore() {
		
		borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
		
	}
	
	/**
	 * Ritorna i cfu del giocatore.
	 * @return I cfu del giocatore
	*/
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * Assegna alla variabile d'istanza cfu un intero passato come parametro.
	 * @param intero che indica il numero di cfu.
	*/
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	/**
	 * Ritorna il riferimento all'oggetto variabile d'istanza borsa.
	 * @return Il riferimento all'oggetto variabile d'istanza borsa.
	*/
	public Borsa getBorsa() {
		
		return this.borsa;
		
	}
	

}
