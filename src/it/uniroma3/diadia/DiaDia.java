package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.*;
import java.util.Scanner;

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

	public DiaDia(Labirinto l, IO console) throws IOException {

		this.labirinto = l;
		this.partita = new Partita(labirinto);
		this.console = console;

	}

	/*public DiaDia(IO console) throws FileNotFoundException, FormatoFileNonValidoException {
		this.partita = new Partita();
		this.console = console;
	}*/

	public void gioca() throws Exception {

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
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {

		Comando comandoDaEseguire;

		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(console);

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

	public static void main(String[] argc) throws Exception {

		/*Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();*/

		//Scanner scanner = new Scanner(System.in);
		try(Scanner scanner = new Scanner(System.in)){
			
			Labirinto labirinto = Labirinto.newBuilder("labirinto1.txt").getLabirinto();
			IO console = new IOConsole(scanner);
			DiaDia gioco = new DiaDia(labirinto, console);
			gioco.gioca();

		}
	}
}