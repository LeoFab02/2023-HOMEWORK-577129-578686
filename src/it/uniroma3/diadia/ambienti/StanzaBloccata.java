package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private String attrezzoSbloccante;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
		
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		
		if(direzioneBloccata.equals(dir) && !(this.hasAttrezzo(attrezzoSbloccante))) {
			
			return this;
			
		}else {
			
			return super.getStanzaAdiacente(dir);
			
		}
		
	}
	
	@Override
	public String getDescrizione() {
		
		if(!(this.hasAttrezzo(attrezzoSbloccante))) {
			
			return super.getDescrizione() + "\n" + "Ops...in questa stanza non e' presente l'attrezzo per sbloccare la stanza a "+ direzioneBloccata + "\n";
			
		}else {
			return super.getDescrizione();
		}
		
	}

}
