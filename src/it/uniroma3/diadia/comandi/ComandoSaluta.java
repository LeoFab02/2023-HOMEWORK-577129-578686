package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{

	private IO io;

	@Override
	public void esegui(Partita partita) {

		if(partita.getStanzaCorrente().getPersonaggio()!=null) {
			io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		}else {
			io.mostraMessaggio("Non ci sono personaggi da salutare");
		}

	}

	@Override
	public void setConsole(IO console) {
		io = console;
	}

	@Override
	public String getNome() {
		return "saluta";
	}



}
