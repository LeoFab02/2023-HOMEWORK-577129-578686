package it.uniroma3.diadia.giocatore;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;


import it.uniroma3.diadia.attrezzi.*;


public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String,Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		
		this(DEFAULT_PESO_MAX_BORSA);
		
	}

	public Borsa(int pesoMax) {
		
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>(); // speriamo bastino...
		
		
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		
		this.attrezzi.put(attrezzo.getNome(),attrezzo);
		
		return true;
	}

	public int getPesoMax() {
		
		return pesoMax;
		
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		
		return this.attrezzi.get(nomeAttrezzo);
				
	}

	public int getPeso() {
		
		int peso = 0;
		
		Collection<Attrezzo> attrezzi = this.attrezzi.values();
		Iterator<Attrezzo> it = attrezzi.iterator();
		
		for (int i= 0; i<this.attrezzi.size(); i++)
			peso += it.next().getPeso();

		return peso;
		
	}
	
	public boolean isEmpty() {
		
		return this.attrezzi.size() == 0;
		
	}

	/*public boolean isFull() {
		
		return this.numeroAttrezzi == 10;
		
	}*/
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		
		return this.getAttrezzo(nomeAttrezzo)!=null;
		
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		
		return this.attrezzi.remove(nomeAttrezzo);
		
	}
	
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		Collection<Attrezzo> attrezzi = this.attrezzi.values();
		Iterator<Attrezzo> it = attrezzi.iterator();
		Attrezzo a;

		if (!this.isEmpty()) {
			
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			
			for (int i= 0; i<this.attrezzi.size(); i++) {
				
				a = it.next();
				s.append(a.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		
		return s.toString();
		
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		
		List<Attrezzo> att = (List<Attrezzo>) attrezzi.values();
		ComparatoreDiAttrezzi cmp = new ComparatoreDiAttrezzi();
		
		Collections.sort(att,cmp);
		
		return att;
		
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		
		return new TreeSet<Attrezzo>(attrezzi.values());
		
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<Integer, Set<Attrezzo>>();
		Set<Attrezzo> s;
		
		for(Attrezzo a : attrezzi.values()) {
			
			if(mappa.containsKey(a.getPeso())) {
				
				mappa.get(a.getPeso()).add(a);
				
			}else {
				
				s = new HashSet<Attrezzo>();
				s.add(a);
				mappa.put(a.getPeso(),s);
				
			}
			
		}
		
		
		
		return mappa;
		
		
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(new ComparatoreDiAttrezzi());
		s.addAll(attrezzi.values());
		
		return s;
		
		
	}

}