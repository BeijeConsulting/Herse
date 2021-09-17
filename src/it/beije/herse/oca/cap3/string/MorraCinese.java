package it.beije.herse.oca.cap3.string;

import java.util.Scanner;

/*
 * Scrivere un programma che chieda agli utenti due stringhe in ingresso, 
 * le stringhe possono valere solo: “carta”, “forbice” o “sasso”. 
 * Il programma dovrà quindi effettuare i dovuti controlli e dichiarare il vincitore secondo 
 * le note regole della “morra cinese” (forbice vince su carta, carta vince su sasso, sasso vince su forbice).
 */
public class MorraCinese {
	private String p1;
	private String p2;
	
	public MorraCinese(String p1, String p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public String outcome() {
		switch(p1.toUpperCase()) {
		case "SASSO":
			switch(p2.toUpperCase()) {
			case "CARTA": return "Player 2 WINS";
			case "FORBICE": return "Player 1 WINS";
			}
			break;
		case "CARTA":
			switch(p2.toUpperCase()) {
			case "SASSO": return "Player 1 WINS";
			case "FORBICE": return "Player 2 WINS";
			}
		case "FORBICE":
			switch(p2.toUpperCase()) {
			case "CARTA": return "Player 1 WINS";
			case "SASSO": return "Player 2 WINS";
			}
		}
		return "PAREGGIO";
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String inputP1;
		do {
			System.out.print("Player 1: ");
			inputP1 = scanner.nextLine();
		}while(!inputP1.equalsIgnoreCase("SASSO") && !inputP1.equalsIgnoreCase("CARTA") && !inputP1.equalsIgnoreCase("FORBICE"));
		
		String inputP2;
		do {
			System.out.print("Player 2: ");
			inputP2 = scanner.nextLine();
		}while(!inputP2.equalsIgnoreCase("SASSO") && !inputP2.equalsIgnoreCase("CARTA") && !inputP2.equalsIgnoreCase("FORBICE"));
		
		scanner.close();
		
		MorraCinese game = new MorraCinese(inputP1, inputP2);
		System.out.println(game.outcome());
	}
}
