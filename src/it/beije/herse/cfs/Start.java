package it.beije.herse.cfs;
import java.util.Scanner;

public class Start {
	public static void menu() {
		Scanner input = new Scanner(System.in);
		int scelta;
		boolean exit = true;
		do {
			System.out.println("Menu:"
						+ "\n1) Gioca"
						+ "\n2) Regole"
						+ "\n3) Exit");
			scelta = input.nextInt();
			
			switch(scelta) {
			case 1:
				Game.start();
				break;
			case 2:
				regole();
				break;
			case 3:
				System.out.println("e' stato un piacere giocare con te! ci rivedremo...");
				exit = false;
				input.close();
				break;
			default:
				System.out.println("Se vuoi uscire premi 3!");
				break;
			}
		}while(exit);
	}
	
	private static void regole() {
		System.out.println("Regole del gioco:"
				+ "\nCarta -> Sasso -> Forbici -> Carta");
	}
}
