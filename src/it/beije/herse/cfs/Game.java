package it.beije.herse.cfs;
import java.util.Scanner;

public class Game {
	public static void start() {
		Scanner input = new Scanner(System.in);
		String gamer = "";
		String pcMove = "";
		String[] pc = {"carta","forbice","sasso"};
		
		System.out.println("Fai la tua scelta:"
				+ "\nSasso,"
				+ "\nCarta,"
				+ "\nForbice.");
		do {
			gamer = input.next().toLowerCase().trim();
			if(!gamer.equals(pc[0])&&!gamer.equals(pc[1])&&!gamer.equals(pc[2])) {
				System.out.println("assicurati di aver inserito un valore corretto!");
			}
		}while(!gamer.equals(pc[0])&&!gamer.equals(pc[1])&&!gamer.equals(pc[2]));
		
		pcMove += pc[(int)(Math.random()*3 - 1)];
		
		if(gamer.equals(pcMove)) {
			System.out.println("La sfida è un pareggio!");
		}else{
			if(gamer.equals("carta")&&pcMove.equals("sasso")) {
				System.out.println("Hai vinto!"
						+ "\n" + gamer + " batte " + pcMove);
			}else if (gamer.equals("forbice")&&pcMove.equals("carta")) {
				System.out.println("Hai vinto!"
						+ "\n" + gamer + " batte " + pcMove);
			}else if(gamer.equals("sasso")&&pcMove.equals("forbice")) {
				System.out.println("Hai vinto!"
						+ "\n" + gamer + " batte " + pcMove);
			}else {
				System.out.println("Hai perso!"
						+ "\n" + gamer + " perde contro " + pcMove);
			}
		}
		
	}
}
