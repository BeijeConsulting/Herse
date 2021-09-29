package esercizi.stringhe;

import java.util.Scanner;

public class SoloVocali {

	public static void main(String[] args) {
		long a8 = 123456789;
		// TODO Auto-generated method stub
		Scanner input=new Scanner(System.in);
		System.out.println("Inserisci una stringa: ");
		String stringaInput=input.next();
		
		for(int i=0;i<stringaInput.length();i++) {
			switch(stringaInput.charAt(i)) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
			//	System.out.println(stringaInput.);
			}
		}
		
	}

}
