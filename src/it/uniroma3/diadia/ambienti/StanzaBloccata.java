package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{
	
	private String attrezzoSbloccante;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
		
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		
		if(/*direzioneBloccata.equals(dir) &&*/ !(this.hasAttrezzo(attrezzoSbloccante))) {
			
			return this;
			
		}else {
			
			return super.getStanzaAdiacente(dir);
			
		}
		
	}
	
	@Override
	public String getDescrizione() {
		
		if(!(this.hasAttrezzo(attrezzoSbloccante))) {
			
			return super.getDescrizione() + "\n" + "Ops...in questa stanza non e' presente l'attrezzo per sbloccare la stanza a "+ direzioneBloccata + "\n";
			
		}else {
			return super.getDescrizione();
		}
		
	}
	
	/*@Override
	public String toString() {

		StringBuilder risultato = new StringBuilder();

		risultato.append(this.getNome());

		risultato.append("\nUscite: ");

		
		Set<String> direzioni = this.getStanzeAdiacenti().keySet();
		//Iterator<String> it1 = direzioni.iterator();

		for (String direzione : direzioni) {   //int i=0; i<stanzeAdiacenti.size();i++
			
			//direzione = it1.next()

			if (direzione!=null && this.hasAttrezzo(attrezzoSbloccante)) {

				risultato.append(" " + direzione);

			}

		}

		risultato.append("\nAttrezzi nella stanza: ");
		
		Collection<Attrezzo> attrezzi = this.getAttrezzi();
		//Iterator<Attrezzo> it2 = attrezzi.iterator();

		for (Attrezzo attrezzo : attrezzi) {   //int i=0;i<attrezzi.size();i++

			//attrezzo = it2.next();

			risultato.append(attrezzo.toString()+" ");

		}

		return risultato.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		StanzaBloccata sb = (StanzaBloccata)obj;
		return this.getDescrizione().equals(sb.getDescrizione());
		
	}*/
	
	/*é@Override
	public Map<String,Stanza> getStanzeAdiacenti(){
		
		if(this.hasAttrezzo(attrezzoSbloccante)) {
			return super.getStanzeAdiacenti();
		}else {
			return null;
		}
		
	}*/

}
