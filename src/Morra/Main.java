package Morra;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Morra morra=new Morra();
		String s1=new String();
		String s2=new String();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Mossa giocatore 1: ");
		s1=scanner.next();
		System.out.println("Mossa giocatore 2: ");
		s2=scanner.next();
		morra.Start(s1, s2);
	}

}
