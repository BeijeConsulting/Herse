package metodistringa;
import java.util.Scanner;


public class MorraCinese2 {
	
	static  boolean test(String s) {
		boolean b = false;
		if(s.equalsIgnoreCase("forbice")) {
			b = true;
		}
		else if(s.equalsIgnoreCase("sasso")) {
			b = true;
		}
		else if(s.equalsIgnoreCase("carta")) {
		b = true;
		}
		return b;
	}
	
	public static void main(String[] args) {
		//String s1 = "sasso";
		//String s2 = "carta";
		System.out.println("avvio scanner...");
		Scanner s = new Scanner(System.in);
		String s1 = s.next();
		String s2 = s.next();
		s.close();
		String[] array = {"sasso", "forbice", "carta", "sasso"};
		int i;
		for(i =0; i<array.length; i++) {
			if(array[i].equals(s1))
				break;
		}
		if(s1.equals(s2))
			System.out.println("parità");
		// adesso in i ho l'indice della prima buttata
		else if(array[i+1].equals(s2)) {
			System.out.println("vince primo giocatore");
		}
		else  {
			System.out.println("vince secondo giocatore");
		}
		
		
	}

}
