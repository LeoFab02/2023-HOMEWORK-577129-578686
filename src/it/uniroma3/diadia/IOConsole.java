package it.uniroma3.diadia;
import java.util.Scanner;

/**
 * Questa classe disaccoppia il gioco dall'uso diretto e pervasivo di 
 * System.out/System.in centralizzando il loro accesso.
 * @author  docente di POO
 * @version base
 */

public class IOConsole {
	
	
	/**
     * Si occupa di stampare un messaggio tramite l'uso di System.out.println().
     * @param stringa che contiene il messaggio da stampare.
     */
	public void mostraMessaggio(String msg) {
		
		System.out.println(msg);
		
	}
	
	/**
     * Si occupa di leggere una riga tramite l'uso di uno scanner e
     * del suo metodo nextline().
     * @return la stringa scritta dall'utente.
     */
	public String leggiRiga() {
		
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
		
	}

}
