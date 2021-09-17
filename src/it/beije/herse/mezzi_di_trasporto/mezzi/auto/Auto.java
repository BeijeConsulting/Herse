package it.beije.herse.mezzi_di_trasporto.mezzi.auto;

import it.beije.herse.mezzi_di_trasporto.elemento.MezziSuStrada;

public abstract class Auto implements MezziSuStrada{
	protected int nRuote;
	protected String sottotipo;
	protected char cambio;
	protected String targa;
	protected int numeroPosti;
	
	public abstract void stampaTipoAuto();
	
	public abstract void setCambio(char cambio);
}
