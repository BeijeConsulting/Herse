package it.beije.herse.oca;

import java.util.Scanner;

public class MorraCinese {

	private final static String carta = "carta";
	private final static String sasso = "sasso";
	private final static String forbici = "forbici";

	public boolean inputControl(String input) {

		boolean result = false;

		switch (input.toLowerCase()) {

			case carta:
				result = true;
				break;

			case sasso:
				result = true;
				break;

			case forbici:
				result = true;
				break;

		}

		return result;

	}

	public String calcoloVincitore(String input1, String input2) {

		String vincitore = null;

		if(!input1.equalsIgnoreCase(input2)) {

			if(input1.equalsIgnoreCase(carta) && input2.equalsIgnoreCase(sasso)) {
				vincitore = carta;
			}else if(input1.equalsIgnoreCase(carta) && input2.equalsIgnoreCase(forbici)) {
				vincitore = forbici;
			}else if(input1.equalsIgnoreCase(sasso) && input2.equalsIgnoreCase(forbici)) {
				vincitore = sasso;				
			}else if(input2.equalsIgnoreCase(carta) && input1.equalsIgnoreCase(sasso)) {
				vincitore = carta;
			}else if(input2.equalsIgnoreCase(carta) && input1.equalsIgnoreCase(forbici)) {
				vincitore = forbici;
			}else {
				vincitore = sasso;
			}

		}else {			
			vincitore = "Pareggio";			
		}

		return vincitore;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean correctInput = false;
		MorraCinese play = new MorraCinese();
		Scanner scan = new Scanner(System.in);
		String input1 = null;
		String input2 = null;
		String result = null;

		do {

			System.out.println("Input1");
			input1 = scan.nextLine();

			if(!(correctInput = play.inputControl(input1)))
				System.out.println("Input non valido");

		}while(!correctInput);

		do {

			System.out.println("Input2");
			input2 = scan.nextLine();

			if(!(correctInput = play.inputControl(input2)))
				System.out.println("Input non valido");

		}while(!correctInput);

		if((result = play.calcoloVincitore(input1, input2)).equalsIgnoreCase("Pareggio"))
			System.out.println(result);
		else
			System.out.println(result + " ha vinto.");

		scan.close();

	}

}
