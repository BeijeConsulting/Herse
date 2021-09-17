package it.beije.herse.oca.cap3.array;

import java.util.Scanner;

/*
 * Scrivere un programma che chieda agli utenti due stringhe in ingresso, 
 * le stringhe possono valere solo: “carta”, “forbice” o “sasso”. 
 * Il programma dovrà quindi effettuare i dovuti controlli e dichiarare il vincitore secondo 
 * le note regole della “morra cinese” (forbice vince su carta, carta vince su sasso, sasso vince su forbice).
 */
public class MorraCineseArray {
	private String p1;
	private String p2;
	private String pairings = "SASSO CARTA FORBICE SASSO";
	
	public MorraCineseArray(String p1, String p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public String outcome() {
		int p1Index = pairings.indexOf(p1.toUpperCase());
		int p2Index;
		
		if(p1Index>pairings.indexOf("SASSO") && p1Index<pairings.indexOf("FORBICE")) p2Index = pairings.indexOf(p2.toUpperCase());
		else p2Index = pairings.indexOf(p2.toUpperCase(), p1Index);
		
		if(p1Index>p2Index) return "Player 1 WINS";
		else if(p1Index<p2Index) return "Player 2 WINS";
		return "DRAW";
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
		
		MorraCineseArray game = new MorraCineseArray(inputP1, inputP2);
		System.out.println(game.outcome());
	}
}