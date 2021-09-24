package RockPaperScrissors;
/*
 * Versione 2 di Sasso Carta e Forbice (utilizzo array)
 */

import java.util.Scanner;

public class csfV2 {
	
	public static void main(String args[]) {
		String[] elementi = new String[] {"carta", "forbice", "sasso", "carta"};
		
		String u1 = new csfV2().scegli(elementi);
		String u2 = new csfV2().scegli(elementi);
		
		System.out.println("Il vincitore è: " + new csfV2().vincitore(elementi, u1, u2));
	}
	
	public String scegli(String[] ar) {
		
		String scelto = "";
		boolean play = true;
		int cont = 0;
				
		while(play) {
			System.out.println("Scegli tra Carta Forbice e Sasso");
			Scanner input = new Scanner(System.in);
			scelto = input.next();
			for(int i = 0; i < ar.length; i++) {
				if(scelto.equalsIgnoreCase(ar[i])) {
					cont++;
					play = false;
				}
			}
		}
	
		return scelto;
	}
	
	public String vincitore(String[] a, String ut1, String ut2){
		String winner ="utente 1";
		int temp1 = 0;
		int temp2 = 0;
		for(int i = 0; i < a.length -1; i++) {
			if(ut1.equalsIgnoreCase(a[i]) && ut2.equalsIgnoreCase(a[i])) {
				winner = "pareggio";
				return winner;
			}
			if(ut1.equalsIgnoreCase(a[i]) && ut2.equalsIgnoreCase(a[i+1])) {
				winner ="utente 2";
				return winner;
			} 
				
				
		}
		return winner;
	}
	
}
