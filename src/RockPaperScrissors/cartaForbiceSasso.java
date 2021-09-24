package RockPaperScrissors;
import java.util.Scanner;

public class cartaForbiceSasso {
	
	public static void main(String args[]) {
		boolean play = true;
		
		while (play) {
			System.out.println("Utente 1 Scegli tra Carta Forbice e Sasso");
			Scanner input1 = new Scanner(System.in);
			String ut1 = input1.next();
			
			System.out.println("Utente 2 Scegli tra Carta Forbice e Sasso");
			Scanner input2 = new Scanner(System.in);
			String ut2 = input2.next();
			
			boolean giusto = false;
			
			if(ut1.equalsIgnoreCase("carta") || ut1.equalsIgnoreCase("forbice") || ut1.equalsIgnoreCase("sasso")) {
				giusto = true;
			} else {
				giusto = false;
				System.out.println("Utente 1 DEVI inserire un valore valido");
				System.out.println();
			} 
			if(ut2.equalsIgnoreCase("carta") || ut2.equalsIgnoreCase("forbice") || ut2.equalsIgnoreCase("sasso")) {
				giusto = true;
			} else {
				giusto = false;
				System.out.println("Utente 2 DEVI inserire un valore valido");
				System.out.println();
			}  if (giusto) {
				System.out.println(controllo(ut1, ut2));
				System.out.println();
			}
			System.out.println("Vuoi giocare ancora? Digita si/sì o no");
			System.out.println("Qualunque altra cosa altre al si/sì verrà considerato no");
			Scanner input3 = new Scanner(System.in);
			String answer = input3.next();
			if(answer.equalsIgnoreCase("si")|| answer.equalsIgnoreCase("sì"))
				play = true;
			else {
				play = false;
			}
		}
			
	}
	
	public static String controllo(String u1, String u2) {
		String ReturnS ="";
		if(u1.equalsIgnoreCase("carta") && u2.equalsIgnoreCase("forbice") || u1.equalsIgnoreCase("forbice") && u2.equalsIgnoreCase("sasso") || u1.equalsIgnoreCase("sasso") && u2.equalsIgnoreCase("carta"))
			ReturnS ="Ha vinto l'utente 2, Complimenti";
		if(u2.equalsIgnoreCase("carta") && u1.equalsIgnoreCase("forbice") || u2.equalsIgnoreCase("forbice") && u1.equalsIgnoreCase("sasso") || u2.equalsIgnoreCase("sasso") && u2.equalsIgnoreCase("carta"))
			ReturnS ="Ha vinto l'utente 2, Complimenti";
		if(u1.equalsIgnoreCase(u2))
			ReturnS = "Pareggio";
		
		return ReturnS;
	}

}
