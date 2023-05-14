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
	
	public Partita(Labirinto l) {
		
		labirinto = l;
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		giocatore = new Giocatore();
		this.finita = false;
		
	}
	
	public Partita(){
		
		labirinto = new Labirinto();
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		giocatore = new Giocatore();
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
	
	public boolean giocatoreisVivo() {
		
		if(this.giocatore.getCfu() != 0) {
			return true;
		}else {
			return false;
		}
		
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}	
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public void setLabirinto(Labirinto l) {
		this.labirinto = l;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
}
