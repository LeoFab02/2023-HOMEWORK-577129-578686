package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	public Cane(String nome, String presentazione) {
		
		super(nome, presentazione);
		
	}
	
	@Override
	public String agisci(Partita partita) {
		
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu-1);
		
		return "GRRRRRR....WOOF WOOF\n";
		
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		final String msg;
	
		if("osso".equals(attrezzo.getNome())) {
			
			this.setAttrezzo(attrezzo);
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("chiave",3));
			
			msg = "woof woof\n";
			
		}else {
			
			msg = this.agisci(partita);
			
		}
		
		return msg;
	}

}
