package it.uniroma3.diadia.fixture;

import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class Fixture {
	
	public static IOSimulator creaSimulazionePartitaEGiocaFacile(List<String> comandiDaLeggere) {

		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.getLabirinto();

		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		return io;

	}
	
	public static IOSimulator creaSimulazionePartitaEGiocaDifficile(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.addStanza("Bagno")
				.addAdiacenza("Bagno", "Atrio", "sud")
				.addAdiacenza("Atrio", "Bagno", "nord")
				.addStanza("Studio")
				.addAdiacenza("Studio", "Atrio", "est")
				.addAdiacenza("Atrio", "Studio", "ovest")
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		return io;
		
	}
	
	public static IOSimulator creaSimulazionePartitaEGiocaMonolocale(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto") 
				.addStanzaVincente("salotto") 
				.getLabirinto();
		DiaDia gioco = new DiaDia(monolocale, io);
		gioco.gioca();
		return io;
	}


	public static IOSimulator creaSimulazionePartitaEGiocaBilocale(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10) // dove? fa riferimento all�ultima stanza aggiunta
				.addAdiacenza("salotto", "camera", "nord") // camera si trova a nord di salotto
				.getLabirinto();
		DiaDia gioco = new DiaDia(bilocale, io);
		gioco.gioca();
		return io;
	}

	public static IOSimulator creaSimulazionePartitaEGiocaTrilocale(List<String> comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto trilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1) // dove? fa riferimento all�ultima stanza aggiunta
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", "nord")
				.addAdiacenza("cucina", "camera", "est")
				.getLabirinto();
		DiaDia gioco = new DiaDia(trilocale, io);
		gioco.gioca();
		return io;
	}

}
