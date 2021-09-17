package it.beije.herse.mezzi_di_trasporto.mezzi.aereo;

import it.beije.herse.mezzi_di_trasporto.carburanti.Benzina;

public class AereoBenzina extends Aereo implements Benzina{

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
	public void stampaTipoAereo() {
		System.out.println("Aereo a benzina");
	}

	@Override
	public void setAereo(String modello, String produttore, int anno) {
		super.anno = anno;
		super.modello = modello;
		super.produttore = produttore;
	}

}
