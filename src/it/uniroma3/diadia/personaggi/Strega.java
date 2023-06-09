package it.uniroma3.diadia.personaggi;

import java.util.Collections;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.ComparatoreStanzePerNumeroAttrezzi;

import java.util.List;
import java.util.ArrayList;

public class Strega extends AbstractPersonaggio{
	
	private static final String MSG_HA_SALUTATO = "Sei stato molto educato a salutarmi, perciò ti sposto nella stanza adicente con "
			+ "più attrezzi!\n";
	
	private static final String MSG_NON_HA_SALUTATO = "Sono una strega molto permalosa, non mi hai salutato e quindi ti sposto nella "
			+ "con meno attrezzi!\n";
	
	public Strega(String nome, String presentazione) {
		
		super(nome,presentazione);
		
	}
	
	@Override
	public String agisci(Partita partita) {
		
		List<Stanza> cs = new ArrayList<Stanza>();
		cs.addAll(partita.getStanzaCorrente().getStanzeAdiacenti().values());
		
		ComparatoreStanzePerNumeroAttrezzi cmp = new ComparatoreStanzePerNumeroAttrezzi();
		Collections.sort(cs,cmp);
		
		if(this.haSalutato()) {
		
			partita.setStanzaCorrente(cs.get(0));
			return MSG_HA_SALUTATO;
			
		}else {
			
			partita.setStanzaCorrente(cs.get(cs.size()-1));
			return MSG_NON_HA_SALUTATO;
			
		}
		
		
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		this.setAttrezzo(attrezzo);
		
		return "AHAHAHAHAHAHAH adesso trattengo quello che mi hai regalato!!\n";
	}

}
