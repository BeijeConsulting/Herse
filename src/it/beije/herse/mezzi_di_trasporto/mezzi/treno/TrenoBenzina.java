package it.beije.herse.mezzi_di_trasporto.mezzi.treno;

import it.beije.herse.mezzi_di_trasporto.carburanti.Benzina;

public class TrenoBenzina extends Treno implements Benzina{

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
		System.out.println("Treno a Benzina");
	}

	@Override
	public void setTreno(String modello, String produttore) {
		super.modello = modello;
		super.produttore = produttore;
	}

}
