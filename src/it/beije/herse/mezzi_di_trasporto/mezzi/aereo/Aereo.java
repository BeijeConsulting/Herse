package it.beije.herse.mezzi_di_trasporto.mezzi.aereo;

import it.beije.herse.mezzi_di_trasporto.elemento.MezziAria;

public abstract class Aereo implements MezziAria{
	protected String modello;
	protected String produttore;
	protected int anno;
	protected int numeroPosti;
	
	public abstract void stampaTipoAereo();
	
	public abstract void setAereo(String modello, String produttore, int anno);
	
}
