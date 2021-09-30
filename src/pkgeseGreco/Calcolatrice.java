package pkgeseGreco;

import java.util.*;

public class Calcolatrice {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double numero, risultato;
		String operazione;
		System.out.println("Inserisci numero:");
		risultato = sc.nextDouble();
		System.out.println("");
		while(true) {
			boolean b = false;
			do {
				System.out.println("Inserisci operazione:");
				operazione = sc.next();
				System.out.println("");
				if((operazione.equalsIgnoreCase("+"))||(operazione.equalsIgnoreCase("-"))||(operazione.equalsIgnoreCase("*"))||(operazione.equalsIgnoreCase("/"))) {
					b = true;
				}
			}while(b == false);
			System.out.println("Inserisci numero:");
			numero = sc.nextDouble();
			System.out.println("");
			switch(operazione) {
				case "+":
					risultato = risultato + numero;
					break;
				case "-":
					risultato = risultato - numero;
					break;
				case "*":
					risultato = risultato * numero;
					break;
				case "/":
					risultato = risultato / numero;
					break;
			}
			System.out.println("Risultato: " + risultato);
			System.out.println("");
		}
	}

}
