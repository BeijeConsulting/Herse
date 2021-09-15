package it.beije.herse.veicolo;

public class Aereo extends Motore implements Vola, Ruote {

	@Override
	public String getAereoportoPartenza() {
	
		return "Roma";
	}

	@Override
	public String getAereoportoArrivo() {
		// TODO Auto-generated method stub
		return "Milano";
	}

	@Override
	public String getCarburante() {
		// TODO Auto-generated method stub
		carburante = "cherosene";
		return carburante;
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		tipo = "velivolo";
		return tipo;
	}

	@Override
	public int getVelMax() {
		velMax = 300;
		return velMax;
	}

	@Override
	public int getNumeroRuote() {
		// TODO Auto-generated method stub
		return 4;
	}

}
