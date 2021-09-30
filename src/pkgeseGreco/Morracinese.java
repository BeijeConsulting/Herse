package pkgeseGreco;

import java.util.*;

public class Morracinese {

	public static void main(String[] args) {
		String s1 = "";
		String s2 = "";
		String[] array = {"sasso", "carta", "forbice"};
		boolean b = false;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Giocatore 1: Carta, forbice o sasso?");
			s1 = sc.next();
			System.out.println("");
			if((s1.equalsIgnoreCase("carta"))||(s1.equalsIgnoreCase("forbice"))||(s1.equalsIgnoreCase("sasso"))) {
				b = true;
			}
		}while(b == false);
		b = false;
		do {
			System.out.println("Giocatore 2: Carta, forbice o sasso?");
			s2 = sc.next();
			System.out.println("");
			if((s2.equalsIgnoreCase("carta"))||(s2.equalsIgnoreCase("forbice"))||(s2.equalsIgnoreCase("sasso"))) {
				b = true;
			}
		}while(b == false);
		
		
		//if
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		if(s1.equalsIgnoreCase(s2)) {
			System.out.println("Patta");
			System.out.println("");
		}else if(s1.equalsIgnoreCase("carta") && s2.equalsIgnoreCase("sasso")) {
			System.out.println("Giocatore 1 ha vinto");
			System.out.println("");
		}else if(s1.equalsIgnoreCase("carta") && s2.equalsIgnoreCase("forbice")) {
			System.out.println("Giocatore 2 ha vinto");
			System.out.println("");
		}else if(s1.equalsIgnoreCase("forbice") && s2.equalsIgnoreCase("carta")) {
			System.out.println("Giocatore 1 ha vinto");
			System.out.println("");
		}else if(s1.equalsIgnoreCase("forbice") && s2.equalsIgnoreCase("sasso")) {
			System.out.println("Giocatore 2 ha vinto");
			System.out.println("");
		}else if(s1.equalsIgnoreCase("sasso") && s2.equalsIgnoreCase("carta")) {
			System.out.println("Giocatore 2 ha vinto");
			System.out.println("");
		}else if(s1.equalsIgnoreCase("sasso") && s2.equalsIgnoreCase("forbice")) {
			System.out.println("Giocatore 1 ha vinto");
			System.out.println("");
		}
		
		
		//switch
		s1 = s1.toLowerCase();
		if(s1.equalsIgnoreCase(s2)) {
			System.out.println("Patta");
			System.out.println("");
		}else {
			switch(s1) {
			case "sasso":
				if(s2.equalsIgnoreCase("forbice")) {
					System.out.println("Giocatore 1 ha vinto");
					System.out.println("");
				}else {
					System.out.println("Giocatore 2 ha vinto");
					System.out.println("");
				}
				break;
			case "forbice":
				if(s2.equalsIgnoreCase("carta")) {
					System.out.println("Giocatore 1 ha vinto");
					System.out.println("");
				}else {
					System.out.println("Giocatore 2 ha vinto");
					System.out.println("");
				}
				break;
			case "carta":
				if(s2.equalsIgnoreCase("sasso")) {
					System.out.println("Giocatore 1 ha vinto");
					System.out.println("");
				}else {
					System.out.println("Giocatore 2 ha vinto");
					System.out.println("");
				}
				break;
			}
		}
		
		
		//array
		int c1 = -1, c2 = -1;
		for(int i = 0; i < array.length; i++) {
			if(c1 == -1 || c2 == -1) {
				if(s1.equalsIgnoreCase(array[i])) {
					c1 = i;
				}
				if(s2.equalsIgnoreCase(array[i])) {
					c2 = i;
				}
			}
		}
		if(c1 == c2) {
			System.out.println("Patta");
			System.out.println("");
		}else if(c1 - 1 == c2 || c2 - 2 == c1) {
			System.out.println("Giocatore 1 ha vinto");
			System.out.println("");
		}else {
			System.out.println("Giocatore 2 ha vinto");
			System.out.println("");
		}
		sc.close();
	}

}
