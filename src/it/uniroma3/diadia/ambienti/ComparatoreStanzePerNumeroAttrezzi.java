package it.uniroma3.diadia.ambienti;

import java.util.Comparator;

import it.uniroma3.diadia.ambienti.Stanza;

public class ComparatoreStanzePerNumeroAttrezzi implements Comparator<Stanza>{
	
	@Override
	public int compare(Stanza s1, Stanza s2) {
		
		if(s1.getAttrezzi().size() - s2.getAttrezzi().size() == 0) {
			
			return s1.getPeso() - s2.getPeso();
			
		}else {
			
			return s1.getAttrezzi().size() - s2.getAttrezzi().size();
			
		}
		
	}

}
