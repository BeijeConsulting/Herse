package it.beije.herse.oca;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;

public class EserciziDate {

	//fate un programma che, ricevuta in input una data in formato 13/09/2021 stampi le seguenti informazioni:
	//Lunedì 13 Settembre, giorno 256 dell'anno 2021, settimana numero 37
	public static String stringToDate(String data) {

		LocalDate d = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		Calendar c = Calendar.getInstance();
		c.set(d.getYear(),d.getMonth().getValue(),d.getDayOfMonth());
		
		return (selectDay(d.getDayOfWeek()) + " " + d.getDayOfMonth() + " " + selectMonth(Month.of(d.getMonthValue())) 
				+ ", giorno " + d.getDayOfYear() + " dell'anno " + d.getYear() + ", settimana numero " + c.get(Calendar.WEEK_OF_YEAR));
		
	}

	private static String selectDay(DayOfWeek day) {
		
		String d = "";

		switch(day.getValue()) {

		case 1:
			d = "Lunedi'";
			break;
		case 2:
			d = "Martedi'";
			break;
		case 3:
			d = "Mercoledi'";
			break;
		case 4:
			d = "Giovedi'";
			break;
		case 5:
			d = "Venerdi'";
			break;
		case 6:
			d = "Sabato";
			break;
		case 7:
			d = "Domenica";
			break;

		}
		
		return d;

	}

	private static String selectMonth(Month month) {

		String m = "";

		switch(month.name()) {

		case "JANUARY":
			m = "Gennaio";
			break;
		case "FEBRUARY":
			m = "Febbraio";
			break;
		case "MARCH":
			m = "Marzo";
			break;
		case "APRIL":
			m = "Aprile";
			break;
		case "MAY":
			m = "Maggio";
			break;
		case "JUNE":
			m = "Giugno";
			break;
		case "JULY":
			m = "Luglio";
			break;
		case "AUGUST":
			m = "Agosto";
			break;
		case "SEPTEMBER":
			m = "Settembre";
			break;
		case "OCTOBER":
			m = "Ottobre";
			break;
		case "NOVEMBER":
			m = "Novembre";
			break;
		case "DICEMBER":
			m = "Dicembre";
			break;

		}

		return m;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		System.out.println("Inserisci la data nel seguente formato dd/mm/yyyy:");
		System.out.println(stringToDate(scan.nextLine()));
		scan.close();

	}

}
