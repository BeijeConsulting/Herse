package esercizi;

import java.util.ArrayList;



public class PassaggioRifermento {
	
	public static void aggiunta (ArrayList l) {
		l.add(5);
	}

	public static void main(String[] args) {
		ArrayList <Integer> a = new ArrayList<>();
		aggiunta(a);
		System.out.println(a);
	}

}
