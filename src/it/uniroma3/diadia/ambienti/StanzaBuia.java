package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.*;

public class StanzaBuia extends Stanza{

	private String attrezzo;
	
	public StanzaBuia(String nome, String attrezzo) {
		
		super(nome);
		this.attrezzo = attrezzo;
		
	}
	
	@Override
	public String getDescrizione() {
		
		if(this.hasAttrezzo(attrezzo)) {
			
			return super.getDescrizione();
			
		}else {
			
			return "c'e' un buio pesto qui\n";
			
		}
		
	}
	
	
	
	
}
