package it.uniroma3.diadia;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigurazioneProperties {

	private static Properties prop=null;

	private static void carica() throws IOException {

		prop = new Properties();

		FileInputStream f = new FileInputStream("diadia.properties");
		prop.load(f);

	}

	public static int getCFU() throws IOException {

		if(prop==null) {
			carica();
		}

		return Integer.parseInt(prop.getProperty("CFU_iniziali"));

	}

	public static int getPesoMax() throws IOException {

		if(prop==null) {
			carica();
		}

		return Integer.parseInt(prop.getProperty("peso_max_borsa"));

	}

}
