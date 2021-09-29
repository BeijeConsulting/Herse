package it.beije.herse.oca.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.herse.file.Contatto;

public class GestioneRubricaCsv {

	
	public static List<Contatto> loadRubricaFromCSV(String pathFile, String separatorChar) throws IOException {
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
		//System.out.println("separator: " + separator);
		
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
			//System.out.println(row);
			
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
	
	
	public static void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) throws IOException {
		File destFile = new File(pathFile);
		System.out.println("destFile exists ? " + destFile.exists());

		FileWriter writer = new FileWriter(destFile, destFile.exists());
		if(!destFile.exists()) {
			writer.write("nome");
			writer.write(separator);
			writer.write("cognome");
			writer.write(separator);
			writer.write("telefono");
			writer.write(separator);
			writer.write("email");
			writer.write(separator);
			writer.write("note");
			writer.write('\n');
		}
		
		for (Contatto c : contatti) {
			writer.write(c.getNome());
			writer.write(separator);
			writer.write(c.getCognome());
			writer.write(separator);
			writer.write(c.getTelefono());
			writer.write(separator);
			writer.write(c.getEmail());
			writer.write(separator);
			writer.write(c.getNote());
			writer.write('\n');
		}
		
		writer.flush();
		writer.close();
	}
	
	
	public static boolean cercaContattoCSV(String pathFile, String separatorChar, Contatto c) throws IOException {
		boolean isFound = false;
		List<Contatto> contatti = loadRubricaFromCSV(pathFile, separatorChar);
		for(Contatto cont1 : contatti) {
			System.out.println(cont1);
		}
		for (Contatto con : contatti) {
			if(c.getNome().equals(con.getNome()) && c.getCognome().equals(con.getCognome()) && c.getTelefono().equals(con.getTelefono()) && c.getEmail().equals(con.getEmail()) && c.getNote().equals(con.getNote())) {
				isFound = true;
				break;
			}
		}
		
		return isFound;
	}
	
	
	public static void inserisciContattoCsv(Contatto c, String pathFile, String separator) throws IOException {
		File destFile = new File(pathFile);
		FileWriter writer = new FileWriter(destFile, destFile.exists());
		
		writer.write(c.getNome());
		writer.write(separator);
		writer.write(c.getCognome());
		writer.write(separator);
		writer.write(c.getTelefono());
		writer.write(separator);
		writer.write(c.getEmail());
		writer.write(separator);
		writer.write(c.getNote());
		writer.write('\n');
		
		writer.flush();
		writer.close();
	}
	
	public static void listaContatti(String pathFile, String separator) throws IOException {
		List<Contatto> contatti = loadRubricaFromCSV(pathFile, separator);
		for(Contatto cont1 : contatti) {
			System.out.println(cont1);
		}
	}
	
	public static void modificaContatto(int id, String[] infoNuove, String pathFile, String separator) throws IOException {
		List<Contatto> contatti = loadRubricaFromCSV(pathFile, separator);
		for(int i = 0; i < contatti.size(); i++) {
			if(contatti.get(i).getId() == id) {
			}
		}
	}
}
