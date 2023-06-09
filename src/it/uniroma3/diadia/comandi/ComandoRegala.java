package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoRegala implements Comando{

	private String attrezzo;
	private IO console;

	@Override
	public void esegui(Partita partita) {


		if(partita.getStanzaCorrente().getPersonaggio()!=null) {

			if(partita.getStanzaCorrente().getPersonaggio().getAttrezzo()!=null) {

				if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo)) {

					partita.getStanzaCorrente().getPersonaggio().riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(attrezzo),partita);
					partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);

				}else {
					console.mostraMessaggio("L'attrezzo che vuoi inserire non è presente nella tua borsa!!");
				}
				
			}else {
				console.mostraMessaggio("Non puoi regalare l'attrezzo al personaggio perchè ne ha già uno!!");
			}

		}else {
			console.mostraMessaggio("Non è presente un personaggio in questa stanza!!");
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;

	}

	@Override
	public void setConsole(IO console) {
		this.console = console;
	}

	@Override
	public String getNome() {
		return "regala";
	}

	@Override
	public String getParametro() {
		return attrezzo;
	}



}
