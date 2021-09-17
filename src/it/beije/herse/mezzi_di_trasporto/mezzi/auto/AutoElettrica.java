package it.beije.herse.mezzi_di_trasporto.mezzi.auto;

import it.beije.herse.mezzi_di_trasporto.carburanti.Elettrico;

public class AutoElettrica extends Auto implements Elettrico{

	@Override
	public void stampaTipoAuto() {
		System.out.print("Auto elettrica");
	}

	@Override
	public void numeroRuote(int nRuote) {
		super.nRuote = nRuote;
	}

	@Override
	public void sottotipo(String sottotipo) {
		super.sottotipo = sottotipo;
	}

	@Override
	public void proprietÓ(String proprietÓ) {
		System.out.println("ProprietÓ: "+proprietÓ);
	}

	@Override
	public void numeroPosti(int nPosti) {
		super.numeroPosti = nPosti;
	}

	@Override
	public void tipoTratta(String tratta) {
		System.out.println("Tratta: "+tratta);
	}

	@Override
	public void setCambio(char cambio) {
		super.cambio = cambio;
	}
}
