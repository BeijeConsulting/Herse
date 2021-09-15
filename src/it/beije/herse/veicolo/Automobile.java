package it.beije.herse.veicolo;

public class Automobile extends Motore implements Ruote {
	
	
	
	public  String getTipo() {
		tipo = "automobile";
		return tipo;
	}
	public  int getVelMax() {
		velMax = 180;
		return velMax;
	}
	public  String getCarburante() {
		carburante = "gasolio";
		return carburante;
		
	}
	
	public int getNumeroRuote() {
		return 4;
	}

}
