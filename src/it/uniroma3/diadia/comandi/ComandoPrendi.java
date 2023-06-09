package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoPrendi implements Comando{
	
	private IO console;
	private String attrezzo;

	@Override
	public void esegui(Partita partita) {

		if(attrezzo == null) {

			console.mostraMessaggio("Che oggetto vuoi prendere?");
			attrezzo = console.leggiRiga();

		}

		//Controlli sulla stanza e sulla borsa

		//Stanza senza oggetti
		if(partita.getStanzaCorrente().getAttrezzi().size() == 0) {

			console.mostraMessaggio("Non ci sono attrezzi da prendere nella stanza!");

		}else{

			boolean inserito;

			if(partita.getStanzaCorrente().hasAttrezzo(attrezzo)) {

				inserito = partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(attrezzo));

				if(inserito) {
					partita.getStanzaCorrente().removeAttrezzo(partita.getGiocatore().getBorsa().getAttrezzo(attrezzo));
					console.mostraMessaggio("Hai preso l'attrezzo " + attrezzo + " dalla stanza!");
					console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
				}else {
					console.mostraMessaggio("Non hai preso niente perche' la borsa e' piena o stai trasportando troppo peso!");
				}

			}else {

				console.mostraMessaggio("Non c'e' l'attrezzo "+attrezzo+"!");

			}

		}

	}
	
	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
	}
	
	@Override
	public String getNome() {
		return "prendi";
	}
	
	@Override
	public String getParametro() {
		return this.attrezzo;
	}
	
	@Override
	public void setConsole(IO console) {
		this.console = console;
	}

}
