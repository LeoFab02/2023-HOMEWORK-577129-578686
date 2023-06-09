package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoVai implements Comando{

	private Direzione direzione;
	private IO console;

	@Override
	public void esegui(Partita partita) {

		if(direzione==null) {

			console.mostraMessaggio("Dove vuoi andare?");

			String dir = console.leggiRiga();

			if(dir.equals("nord")) {
				direzione = Direzione.nord;
			}else if(dir.equals("sud")) {
				direzione = Direzione.sud;
			}else if(dir.equals("est")) {
				direzione = Direzione.est;
			}else if(dir.equals("ovest")) {
				direzione = Direzione.ovest;
			}

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
		console.mostraMessaggio("Borsa:" + partita.getGiocatore().getBorsa().toString());

	}
	
	@Override
	public void setParametro(String dir) {
		
		if(dir.equals("nord")) {
			direzione = Direzione.nord;
		}else if(dir.equals("sud")) {
			direzione = Direzione.sud;
		}else if(dir.equals("est")) {
			direzione = Direzione.est;
		}else if(dir.equals("ovest")) {
			direzione = Direzione.ovest;
		}
		
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
	
	@Override
	public String getParametro() {
		return this.direzione.toString();
	}
	
	@Override
	public void setConsole(IO console) {
		this.console = console;
	}

}
