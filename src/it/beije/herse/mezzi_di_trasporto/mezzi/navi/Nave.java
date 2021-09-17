package it.beije.herse.mezzi_di_trasporto.mezzi.navi;

import it.beije.herse.mezzi_di_trasporto.elemento.MezziAcqua;

public abstract class Nave implements MezziAcqua{
	protected String modello;
	protected String nome;
	protected String nomeCapitano;
	protected int numeroCabine;
	
	public abstract void stampaTipoNave();
	
	public abstract void setNave(String modello, String nome, String nomeCapitano);
}
