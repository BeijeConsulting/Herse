package it.beije.herse.mezzi_di_trasporto.mezzi.auto;

import it.beije.herse.mezzi_di_trasporto.carburanti.*;

public class AutoIbrida extends Auto implements Benzina, Elettrico{

	@Override
	public void stampaTipoAuto() {
		System.out.print("Auto ibrida");
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
	public void proprietà(String proprietà) {
		System.out.println("Proprietà: "+proprietà);
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
