package it.beije.herse.veicolo;

public class Motoscafo extends Motore implements Naviga {

	@Override
	public boolean Vele() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCarburante() {
		carburante = "benzina";
		return carburante;
	}

	@Override
	public String getTipo() {
		tipo = "motoscafo";
		return tipo;
	}

	@Override
	public int getVelMax() {
		velMax = 100;
		return velMax;
	}
	

}
