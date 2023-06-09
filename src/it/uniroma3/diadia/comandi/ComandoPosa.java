	package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{

	private IO console;
	private String attrezzo;

	@Override
	public void esegui(Partita partita) {

		if(this.attrezzo == null) {

			console.mostraMessaggio("Che oggetto vuoi posare?");
			this.attrezzo = console.leggiRiga();

		}

		if(partita.getGiocatore().getBorsa().isEmpty()) {

			console.mostraMessaggio("Non puoi posare attrezzi perche' la borsa e' vuota!");

		}else{

			Attrezzo rimosso;
			boolean inserito;

			rimosso = partita.getGiocatore().getBorsa().removeAttrezzo(this.attrezzo);

			if(rimosso != null) {

				inserito = partita.getStanzaCorrente().addAttrezzo(rimosso);

				if(inserito) {

					console.mostraMessaggio("Hai posato l'attrezzo "+this.attrezzo+" nella stanza!");
					console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());

				}else {
					console.mostraMessaggio("Non hai posato l'attrezzo perche' la stanza e' piena!");
				}

			}else {
				console.mostraMessaggio("Non hai l'oggetto che vuoi inserire!");
			}


		}

	}

	@Override
	public void setParametro(String parametro) {

		this.attrezzo = parametro;

	}
	
	@Override
	public String getNome() {
		return "posa";
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
