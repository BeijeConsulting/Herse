package it.beije.herse.mezzi_di_trasporto.mezzi.aereo;

import it.beije.herse.mezzi_di_trasporto.carburanti.Diesel;

public class AereoDiesel extends Aereo implements Diesel{

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
	public void stampaTipoAereo() {
		System.out.println("Aereo a diesel");
	}

	@Override
	public void setAereo(String modello, String produttore, int anno) {
		super.anno = anno;
		super.modello = modello;
		super.produttore = produttore;
	}

}
