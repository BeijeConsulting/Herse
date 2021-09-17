package it.beije.herse.oca;

import java.util.Scanner;
import java.util.ArrayList;

public class NewMorraCinese {

	private  ArrayList<String> scelta = new ArrayList<String>();

	public NewMorraCinese() {
		scelta.add("carta");
		scelta.add("sasso");
		scelta.add("forbici");
	}

	public String calcoloVincitore(String input1, String input2) {

		String result = null;

		if(scelta.indexOf(input1) - scelta.indexOf(input2) == 1) {
			result = scelta.get(scelta.indexOf(input2));
		}else if(scelta.indexOf(input1) - scelta.indexOf(input2) == -1) {
			result = scelta.get(scelta.indexOf(input1));
		}else if((scelta.indexOf(input1) - scelta.indexOf(input2)) == 2 || (scelta.indexOf(input1) - scelta.indexOf(input2)) == -2){
			result = scelta.get(scelta.size()-1);
		}else {
			result = "Pareggio";
		}

		return result;

	}

	public boolean inputControl(String input) {

		boolean result = false;

		switch (input.toLowerCase()) {

			case "carta":
				result = true;
				break;
	
			case "sasso":
				result = true;
				break;
	
			case "forbici":
				result = true;
				break;

		}

		return result;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean correctInput = false;
		NewMorraCinese play = new NewMorraCinese();
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

		if((result = play.calcoloVincitore(input1.toLowerCase(), input2.toLowerCase())).equalsIgnoreCase("Pareggio"))
			System.out.println(result);
		else
			System.out.println(result + " ha vinto.");

		scan.close();

	}

}
