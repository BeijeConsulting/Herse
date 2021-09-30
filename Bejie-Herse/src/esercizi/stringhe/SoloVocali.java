package esercizi.stringhe;

import java.util.Scanner;

public class SoloVocali {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Scanner input=new Scanner(System.in);
		System.out.println("Inserisci una stringa: ");
		String stringa=input.next();

		
		for(int i=0;i<stringa.length();i++) {
		switch(stringa.charAt(i)) {
		case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
			System.out.println(stringa.charAt(i));
			}
		}
		
	}

}
