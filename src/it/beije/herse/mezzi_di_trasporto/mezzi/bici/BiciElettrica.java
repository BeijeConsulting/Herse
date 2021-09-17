package it.beije.herse.mezzi_di_trasporto.mezzi.bici;

import it.beije.herse.mezzi_di_trasporto.carburanti.Elettrico;

public class BiciElettrica extends Bici implements Elettrico{

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
		super.nPosti = nPosti;
	}

	@Override
	public void tipoTratta(String tratta) {
	}

	@Override
	public void stampaTipoBici() {
		System.out.println("Bici elettrica");
	}

	@Override
	public void setBici(String marca, String modello) {
		super.marca = marca;
		super.modello = modello;
	}
	
}
