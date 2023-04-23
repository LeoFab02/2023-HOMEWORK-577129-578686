package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoVai implements Comando{

	private String direzione;
	private IO console;

	public ComandoVai(IO console) {
		this.console = console;
	}

	@Override
	public void esegui(Partita partita) {

		if(direzione==null) {

			console.mostraMessaggio("Dove vuoi andare?");

			direzione = console.leggiRiga();

		}

		Stanza prossimaStanza = null;

		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);

		if (prossimaStanza == null) {

			console.mostraMessaggio("Direzione inesistente");

		}else {

			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfu-1);

		}

		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio("Borsa:");
		console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());

	}
	
	@Override
	public void setParametro(String parametro) {
		
		this.direzione = parametro;
		
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
	
	@Override
	public String getParametro() {
		return this.direzione;
	}
	


}
