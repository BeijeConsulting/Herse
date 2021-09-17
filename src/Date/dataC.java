package Date;
import java.time.DayOfWeek;
import java.time.*;
import java.time.format.DateTimeFormatter;

/*
 * fate un programma che, ricevuta in input una data in formato 13/09/2021 stampi le seguenti informazioni:
 *	Lunedì 13 Settembre, giorno 256 dell'anno 2021, settimana numero 37
 */

public class dataC {
	
	public static void main(String[] args) {
		
		LocalDate data = LocalDate.of(2021, 9, 13);
		DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd, MM, yyyy");
		data.format(f1);
		
		new dataC().calcolo(data);
	}
	
	
	public void calcolo(LocalDate d) {
		DayOfWeek dayWeek = d.getDayOfWeek();
		int days;
		days = d.getDayOfYear();
		System.out.println(dayWeek + " " + d.getDayOfMonth() + " " + d.getMonth());
		System.out.println("giorno " + days + " dell'anno " + d.getYear());
		double weeks;
		weeks = (double) days / 7;
		int weeknum = (int) Math.round(weeks);
		System.out.println("Settimana numero: " + weeknum);
		
	}
}

