package it.beije.herse.mezzi_di_trasporto.mezzi.treno;

import it.beije.herse.mezzi_di_trasporto.carburanti.Elettrico;

public class TrenoElettrico extends Treno implements Elettrico{

	@Override
	public void numeroVagoni(int nVagoni) {
		super.nVagoni = nVagoni;
	}

	@Override
	public void numeroPosti(int nPosti) {
		super.nPosti = nPosti;
	}

	@Override
	public void tipoTratta(String tratta) {
		System.out.println("Tratta: "+tratta);
	}

	@Override
	public void stampaTipoTreno() {
		System.out.println("Treno elettrico");
	}

	@Override
	public void setTreno(String modello, String produttore) {
		super.modello = modello;
		super.produttore = produttore;
	}
}
