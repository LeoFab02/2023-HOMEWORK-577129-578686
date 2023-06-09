package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Collection;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;

	private Map<String,Attrezzo> attrezzi;
	private Map<Direzione,Stanza> stanzeAdiacenti;

	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<Direzione,Stanza>(NUMERO_MASSIMO_DIREZIONI);
		this.attrezzi = new HashMap<String,Attrezzo>(NUMERO_MASSIMO_ATTREZZI);
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {

		boolean aggiornato = false;
		
		if (stanzeAdiacenti.containsKey(direzione)) {
			this.stanzeAdiacenti.put(direzione,stanza);
			aggiornato = true;
		}
		if (!aggiornato)
			if (this.stanzeAdiacenti.keySet().size() < NUMERO_MASSIMO_DIREZIONI) {
				this.stanzeAdiacenti.put(direzione,stanza);
			}

	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {

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
	public List<Attrezzo> getAttrezzi() {

		List<Attrezzo> l = new ArrayList<Attrezzo>();
		l.addAll(attrezzi.values());

		return l;

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


		Set<Direzione> direzioni = stanzeAdiacenti.keySet();
		//Iterator<String> it1 = direzioni.iterator();

		for (Direzione direzione : direzioni) {   //int i=0; i<stanzeAdiacenti.size();i++

			//direzione = it1.next()

			if (direzione!=null) {

				risultato.append(" " + direzione);

			}

		}

		risultato.append("\nAttrezzi nella stanza: ");

		Collection<Attrezzo> attrezzi = this.attrezzi.values();
		//Iterator<Attrezzo> it2 = attrezzi.iterator();

		for (Attrezzo attrezzo : attrezzi) {   //int i=0;i<attrezzi.size();i++

			//attrezzo = it2.next();

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


	public List<Direzione> getDirezioni() {

		List<Direzione> dir = new ArrayList<Direzione>();
		dir.addAll(stanzeAdiacenti.keySet());

		return dir;

	}

	public Map<Direzione, Stanza> getStanzeAdiacenti(){

		return stanzeAdiacenti;

	}

	public int getPeso() {

		int peso = 0;

		Collection<Attrezzo> attrezzi = this.attrezzi.values();
		Iterator<Attrezzo> it = attrezzi.iterator();

		for (int i= 0; i<this.attrezzi.size(); i++)
			peso += it.next().getPeso();

		return peso;

	}

	public boolean isMagica() {

		if(this instanceof StanzaMagica) {
			return true;
		}else {
			return false;
		}

	}

	@Override
	public boolean equals(Object obj) {

		Stanza s = (Stanza)obj;
		return this.nome.equals(s.getNome());

	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

}