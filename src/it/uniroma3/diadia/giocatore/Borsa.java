package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.*;

/**
 * Classe Borsa - Una borsa del giocatore contentente gli attrezzi.
 * Gestisce tutti gli aspetti di inserimento e rimozione
 * di attrezzi controllandone la presenza all'interno della borsa
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	
	/**
     * Richiama il costruttore di borsa passando come parametro
     * il peso massimo cioè 10.
     */
	public Borsa() {
		
		this(DEFAULT_PESO_MAX_BORSA);
		
	}

	/**
     * Crea una borsa inizializzando il peso massimo all'intero
     * passato come parametro.
     * @param intero che indica il peso max della borsa.
     */
	public Borsa(int pesoMax) {
		
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
		
	}

	/**
     * Mette un attrezzo nella borsa.
     * @param attrezzo l'attrezzo da mettere nella borsa.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		
		if (this.numeroAttrezzi==10)
			return false;
		
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		
		this.numeroAttrezzi++;
		
		return true;
	}

	/**
     * Ritorna un intero che indica il peso massimo della borsa.
     * @return int intero che indica il peso massimo della borsa
     */
	public int getPesoMax() {
		
		return pesoMax;
		
	}
	
	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella borsa.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella borsa.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		
		Attrezzo a = null;
		
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}

	/**
     * Restituisce la somma del peso di tutti gli attrezzi nella borsa.
	 * @return la somma del peso di tutti gli attrezzi nella borsa.
	 */
	public int getPeso() {
		
		int peso = 0;
		
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
		
	}
	
	/**
     * Controlla che il numero degli attrezzi nella borsa sia 0.
	 * @return True se la borsa è vuota, False altrimenti.
	 */
	public boolean isEmpty() {
		
		return this.numeroAttrezzi == 0;
		
	}
	
	/**
	* Controlla se un attrezzo esiste nella borsa (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella borsa, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		
		return this.getAttrezzo(nomeAttrezzo)!=null;
		
	}
	
	/**
	 * Rimuove un attrezzo dalla borsa (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		
		Attrezzo a;
		
		for(int i=0;i<numeroAttrezzi;i++) {
			
			if(attrezzi[i].getNome().equals(nomeAttrezzo)) {
				
				a = attrezzi[i];
				attrezzi[i] = attrezzi[numeroAttrezzi-1];
				attrezzi[numeroAttrezzi-1] = null;
				
				numeroAttrezzi--;
				
				return a;
				
			}
			
		}
		
		return null;
		
	}
	
	/**
	* Restituisce una rappresentazione stringa della borsa,
	* stampadone la descrizione (peso e attrezzi contenuti)
	* @return la rappresentazione stringa
	*/
	public String toString() {
		
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		
		return s.toString();
		
	}


}
