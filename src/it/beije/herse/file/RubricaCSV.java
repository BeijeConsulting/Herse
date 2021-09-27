package it.beije.herse.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RubricaCSV {
	
	public static List<Contatto> readRubricaCSV(String pathFile, String separatorChar) throws IOException {
		FileReader fileReader = new FileReader(pathFile);
		
		BufferedReader reader = new BufferedReader(fileReader);
		String row = reader.readLine();
		String separator1 = new StringBuilder("\'").append(separatorChar).append('\'').toString();	
		String separator2 = new StringBuilder("\"").append(separatorChar).append('\"').toString();
		String separator = null;
		if (row.contains(separator1)) {
			separator = separator1;
		} else if (row.contains(separator2)) {
			separator = separator2;
		} else {
			separator = separatorChar;
		}
		System.out.println("separator: " + separator);
		
		String[] title = row.split(separator);
		int last = title.length - 1;
		if (separator.length() > 1) {
			title[0] = title[0].substring(1);
			title[last] = title[last].substring(0, title[last].length()-1);
		}
		
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		Contatto contatto = null;
		String[] cols = null;
		while (reader.ready()) {
			row = reader.readLine();
			System.out.println(row);
			
			cols = row.split(separator);
			if (separator.length() > 1) {
				cols[0] = cols[0].substring(1);
				cols[last] = cols[last].substring(0, cols[last].length()-1);
			}
			
			contatto = new Contatto();
			for (int i = 0; i <= last; i++) {
				System.out.println(title[i] + ": " + cols[i]);
				switch (title[i].toUpperCase()) {
				case "NOME":
					contatto.setNome(cols[i]);		
					break;
				case "COGNOME":
					contatto.setCognome(cols[i]);		
					break;
				case "TELEFONO":
					contatto.setTelefono(cols[i]);		
					break;
				case "EMAIL":
					contatto.setEmail(cols[i]);		
					break;
				case "NOTE":
					contatto.setNote(cols[i]);		
					break;
				}
			}
			
			contatti.add(contatto);
			System.out.println();
		}
		
		reader.close();
		
		return contatti;
	}
	
	public static void writeRubricaCSV(String path, String separator) throws IOException {
				
		File addInRubrica = new File(path);
		
		FileWriter writer =  null;
		
		if(addInRubrica.exists()) {
			writer = new FileWriter(addInRubrica, true);
		} else {
			writer = new FileWriter(addInRubrica);
		}
		
		String[] titles = new String[] {"nome", "cognome", "telefono", "email", "note"};
		
		FileReader f = new FileReader(path);
		BufferedReader r = new BufferedReader(f);
		String row = r.readLine();
		
		for(int x = 0; x < titles.length; x++) {
			if(row == null) {
				writer.write(titles[x].toUpperCase());
				writer.write(separator);
			}
		}
		
		writer.write('\n');
		
		Contatto contatto = new Contatto();
		
		for(int i = 0; i < titles.length; i++) {
			switch (titles[i].toUpperCase()) {
			case "NOME":
				contatto.setNome();
				writer.write(contatto.getNome());
				writer.write(separator);
				break;
			case "COGNOME":
				contatto.setCognome();
				writer.write(contatto.getCognome());	
				writer.write(separator);
				break;
			case "TELEFONO":
				contatto.setTelefono();
				writer.write(contatto.getTelefono());
				writer.write(separator);
				break;
			case "EMAIL":
				contatto.setEmail();
				writer.write(contatto.getEmail());	
				writer.write(separator);
				break;
			case "NOTE":
				contatto.setNote();
				writer.write(contatto.getNote());	
				writer.write(separator);
				break;
			}
		}
		
		
		writer.flush();
		writer.close();
		
	}

	public static void main(String[] args) throws Exception {
	
		List<Contatto> contatti = readRubricaCSV("/temp/prova.txt", ";");
		System.out.println("contatti number: " + contatti.size());
		
		boolean b = true;
		while(b) {
			writeRubricaCSV("/temp/rubrica.txt", "\t");
			System.out.println("Vuoi inserire un altro contatto?");
			Scanner input = new Scanner(System.in);
			String answer = input.next();
			
			if(!answer.equalsIgnoreCase("si"))
				b = false;
		}
		
		
	}

}
