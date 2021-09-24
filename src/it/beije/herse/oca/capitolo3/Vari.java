package it.beije.herse.oca.capitolo3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Scanner;

public class Vari {

	public static void main(String[] args) {
		//stampaData("13/09/2021");
		
		//DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
		//String s1 = date.format(f);
		//stampaData(s1);
		//prova(args[0]);
		
		
	}
	
	public static void prova(String s) {
		System.out.println(5);
	}
	
		

	public static void morra(String a, String b) {
		String s1 = "carta";
		String s2 = "forbice";
		String s3 = "sasso";
		if (a.equalsIgnoreCase(s1) && b.equalsIgnoreCase(s2) || a.equalsIgnoreCase(s3) && b.equalsIgnoreCase(s1)
				|| a.equalsIgnoreCase(s2) && b.equalsIgnoreCase(s3)) {
			System.out.println("Il secondo giocatore ha vinto");
		} else if (a.equalsIgnoreCase(s2) && b.equalsIgnoreCase(s1) || a.equalsIgnoreCase(s1) && b.equalsIgnoreCase(s3)
				|| a.equalsIgnoreCase(s3) && b.equalsIgnoreCase(s2)) {
			System.out.println("Il primo giocatore ha vinto");
		} else {
			System.out.println("pareggio");
		}

	}

	public static void morra2(char a, char b) { // non so se andava fatto così, non capisco il senso
		char c[] = { 'c', 'f', 's', 'c' };

		for (int i = 0; i < c.length; i++) {
			if (c[i] == a && c[i + 1] == b) {
				System.out.println("Il giocatore due ha vinto");
				break;
			} else if (c[i] == a && c[i] == b) {
				System.out.println("Pareggio");
				break;
			} else if (c[i + 1] == a && c[i] == b) {
				System.out.println("Il giocatore uno ha vinto");
				break;
			}
		}
	}
	
	public static void calcolatrice() {
		System.out.println("avvio scanner...");
		Scanner s = new Scanner(System.in);
		System.out.println("inserisci una stringa o più stringhe contenenti operazioni");
		String st = s.next();
		char[] ch = st.toCharArray();
		    
		while (!st.equalsIgnoreCase("exit")) {
			
					
		
		}
		
		System.out.println("BYE!!");
		s.close();
	}
	

	
	public static void stampaData(String s) {
		//if(s != null && s.contains("/")) {
//			String day = ""+s.charAt(0) + s.charAt(1);
//			String month = ""+s.charAt(3) + s.charAt(4);
//			String year = ""+s.charAt(6) + s.charAt(7) + s.charAt(8) + s.charAt(9);
//			String day1 = day.substring(1);
//			String month1 = month.substring(1);
//			String year1 = year.substring(1);
//			String inputDate = day + month + year;
//			System.out.println(inputDate);
			
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(s, f);
			
			String stampa = "";
			DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
			String date1 = shortDateTime.format(date);
			int numSett = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
			stampa = date1 + ", giorno " + date.getDayOfYear()+" dell'anno " + date.getYear() + ", settimana numero " + numSett;
			System.out.println(stampa);
		//}
		
	}
}
