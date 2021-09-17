package it.beije.herse.mezzi_di_trasporto.elemento;

import it.beije.herse.mezzi_di_trasporto.MezziDiTrasporto;

public interface MezziSuStrada extends MezziDiTrasporto{
	public abstract void numeroRuote(int nRuote);
	public abstract void sottotipo(String sottotipo);
	public abstract void proprietà(String proprietà);
}
