package it.beije.herse.mezzi_di_trasporto.mezzi.bici;

import it.beije.herse.mezzi_di_trasporto.elemento.MezziSuStrada;

public abstract class Bici implements MezziSuStrada{
	protected int nRuote;
	protected String sottotipo;
	protected String marca;
	protected String modello;
	protected int nPosti;
	
	public abstract void stampaTipoBici();
	
	public abstract void setBici(String marca, String modello);
}
