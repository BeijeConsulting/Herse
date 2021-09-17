package it.beije.herse.mezzi_di_trasporto.mezzi.treno;

import it.beije.herse.mezzi_di_trasporto.elemento.MezziSuRotaie;

public abstract class Treno implements MezziSuRotaie{
	protected String modello;
	protected int nPosti;
	protected int nVagoni;
	protected String produttore;
	
	public abstract void stampaTipoTreno();
	
	public abstract void setTreno(String modello, String produttore);
}
