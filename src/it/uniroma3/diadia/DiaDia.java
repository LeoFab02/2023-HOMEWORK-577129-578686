package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO console;
	private Labirinto labirinto;
	
	public DiaDia(Labirinto l, IO console) {
		
		this.labirinto = l;
		this.partita = new Partita(labirinto);
		this.console = console;
		
	}

	public DiaDia(IO console) {
		this.partita = new Partita();
		this.console = console;
	}

	public void gioca() {

		String istruzione; 

		console.mostraMessaggio(getMessaggioBenvenuto());

		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));

	}


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {

		Comando comandoDaEseguire;
		
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(console);
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			console.mostraMessaggio("Hai vinto!");
		
		if (!this.partita.giocatoreisVivo())

			console.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();
	}  	

	public static String getMessaggioBenvenuto() {
		return MESSAGGIO_BENVENUTO;
	}

	public static void main(String[] argc) {
		
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addStanza("N11")
				.addStanzaBuia("N10","lanterna").addAttrezzo("lanterna", 3)
				.addStanza("Laboratorio Campus")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "N11", "est")
				.addAdiacenza("Atrio", "N10", "sud")
				.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
				.addAdiacenza("N11", "Atrio", "ovest")
				.addAdiacenza("N10", "Atrio", "nord")
				.addAdiacenza("Laboratorio Campus", "Atrio", "est")
				.getLabirinto();
		
		IO console = new IOConsole();
		DiaDia gioco = new DiaDia(labirinto, console);
		gioco.gioca();
	}
}