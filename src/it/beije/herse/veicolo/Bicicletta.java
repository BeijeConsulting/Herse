package it.beije.herse.veicolo;

public class Bicicletta extends Veicolo implements Ruote {

	@Override
	public int getNumeroRuote() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getTipo() {
		tipo = "bicicletta";
		return tipo;
	}

	@Override
	public int getVelMax() {
		velMax = 25;
		return velMax;
	}

}
