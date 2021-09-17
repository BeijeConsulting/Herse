package it.beije.herse.mezzi_di_trasporto.mezzi.bici;

import it.beije.herse.mezzi_di_trasporto.carburanti.Energia;

public class BiciPedali extends Bici implements Energia{

	@Override
	public void numeroRuote(int nRuote) {
		super.nRuote = nRuote;
	}

	@Override
	public void sottotipo(String sottotipo) {
		super.sottotipo = sottotipo;
	}

	@Override
	public void propriet�(String propriet�) {
		System.out.println("Propriet�: "+propriet�);
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
		System.out.println("Bici a pedali");
	}

	@Override
	public void setBici(String marca, String modello) {
		super.marca = marca;
		super.modello = modello;
	}
	
}
