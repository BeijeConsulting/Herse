package it.beije.herse.mezzi_di_trasporto.mezzi.navi;

import it.beije.herse.mezzi_di_trasporto.carburanti.Diesel;

public class NaveDiesel extends Nave implements Diesel{

	@Override
	public void propriet�(String propriet�) {
		System.out.println("Propriet�: "+propriet�);
	}

	@Override
	public void numeroPosti(int nPosti) {
		super.numeroCabine = nPosti;
	}

	@Override
	public void tipoTratta(String tratta) {
		System.out.println("Tratta: "+tratta);
	}

	@Override
	public void setNave(String modello, String nome, String nomeCapitano) {
		super.modello = modello;
		super.nome = nome;
		super.nomeCapitano = nomeCapitano;
	}

	@Override
	public void stampaTipoNave() {
		System.out.print("Nave a diesel");
		
	}

}
