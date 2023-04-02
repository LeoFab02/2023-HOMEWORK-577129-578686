package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.*;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Labirinto labirinto;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private boolean finita;
	
	/**
     * Costruttore della classe partita.
     * Ha il compito di inizializzare le variabili di instanza.
     */
	public Partita(){
		
		giocatore = new Giocatore();
		labirinto = new Labirinto();
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		this.finita = false;
		
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}	
	
	/**
	 * Restituisce il riferimento all'oggetto variabile di istanza labirinto.
	 * @return Il riferimento all'oggetto labirinto.
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	/**
	 * Restituisce il riferimento all'oggetto variabile di istanza giocatore.
	 * @return Il riferimento all'oggetto giocatore.
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	/**
	 * Restituisce il riferimento all'oggetto variabile di istanza stanza corrente.
	 * @return Il riferimento all'oggetto stanza corrente.
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Assegna alla variabile di istanza stanzaCorrente un riferimento di tipo stanza passato
	 * come parametro.
	 * @param Il riferimento alla nuova stanza corrente.
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
}
