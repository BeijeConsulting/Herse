package EserciziStringhe;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		char c='c';
		Metodi esercizio=new Metodi();
		String s=new String();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Inserisci parola");
		s=scanner.next();
		esercizio.SoloVocali(s);
		//esercizio.StampaMaiuscole(s);
		esercizio.contaLettera(c, s);
		esercizio.Contrario(s);
		esercizio.Concatena();
		scanner.close();
	}

}
