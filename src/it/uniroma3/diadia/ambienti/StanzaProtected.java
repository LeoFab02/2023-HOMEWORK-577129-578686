package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected{
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;
	
	protected Map<String,Attrezzo> attrezzi;
	protected Map<Direzione, StanzaProtected> stanzeAdiacenti;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<Direzione,StanzaProtected>(NUMERO_MASSIMO_DIREZIONI);
		this.attrezzi = new HashMap<String,Attrezzo>(NUMERO_MASSIMO_ATTREZZI);
		}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, StanzaProtected stanza) {
		
		stanzeAdiacenti.put(direzione, stanza);
		
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public StanzaProtected getStanzaAdiacente(Direzione direzione) {
		
		return stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Collection<Attrezzo> getAttrezzi() {
		return this.attrezzi.values();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi.put(attrezzo.getNome(),attrezzo);
			return true;
		}
		else {
			return false;
		}
		
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {

		StringBuilder risultato = new StringBuilder();

		risultato.append(this.nome);

		risultato.append("\nUscite: ");

		Direzione direzione;
		
		Set<Direzione> direzioni = stanzeAdiacenti.keySet();
		Iterator<Direzione> it1 = direzioni.iterator();

		for (int i=0; i<stanzeAdiacenti.size();i++) {

			direzione = it1.next();

			if (direzione!=null) {

				risultato.append(" " + direzione);

			}

		}

		risultato.append("\nAttrezzi nella stanza: ");

		Attrezzo attrezzo;
		
		Collection<Attrezzo> attrezzi = this.attrezzi.values();
		Iterator<Attrezzo> it2 = attrezzi.iterator();

		for (int i=0;i<attrezzi.size();i++) {

			attrezzo = it2.next();

			risultato.append(attrezzo.toString()+" ");

		}

		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		
		return attrezzi.containsKey(nomeAttrezzo);
		
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo nomeAttrezzo) {
		
		if(nomeAttrezzo == null) {
			return false;
		}
		
		if(attrezzi.remove(nomeAttrezzo.getNome())!=null) {
			return true;
		}

		return false;
	}


	public Set<Direzione> getDirezioni() {
		
		return stanzeAdiacenti.keySet();
		
	}

}
