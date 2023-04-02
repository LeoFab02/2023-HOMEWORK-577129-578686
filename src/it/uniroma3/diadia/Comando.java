package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public class Comando {

    private String nome;
    private String parametro;
    
    /**
     * Costruttore della classe comando.
     * Ha il compito di processare l'istruzione suddividendo il comando da un possibile parametro
     * 
     * @param istruzione
     */
    public Comando(String istruzione) {
    	
		Scanner scannerDiParole = new Scanner(istruzione);

		// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			this.nome = scannerDiParole.next(); 

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();
		
    }

    /**
     * Restituisce il nome del comando.
     * @return il nome del comando
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce il nome del parametro del comando.
     * @return il nome del parametro del comando
     */
    public String getParametro() {
        return this.parametro;
    }

    /**
     * Controlla che il nome del comando sia nullo.
     * @return true se il nome è null, false altrimenti
     */
    public boolean sconosciuto() {
        return (this.nome == null);
    }
}