package it.beije.herse.mezzi_di_trasporto.mezzi.navi;

import it.beije.herse.mezzi_di_trasporto.carburanti.Benzina;

public class NaveBenzina extends Nave implements Benzina{

	@Override
	public void stampaTipoNave() {
		System.out.print("Nave a bezina");
	}

	@Override
	public void proprietÓ(String proprietÓ) {
		System.out.println("ProprietÓ: "+proprietÓ);
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

}
