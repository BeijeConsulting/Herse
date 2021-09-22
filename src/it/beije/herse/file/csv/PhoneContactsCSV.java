package it.beije.herse.file.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.herse.file.Contatto;

public class PhoneContactsCSV {

	public static List<Contatto> readRubricaCSV(String pathFile, String separatorChar) {
		List<Contatto> contatti = new ArrayList<Contatto>();
		
		BufferedReader reader = null;
		
		try {
			FileReader fileReader = new FileReader(pathFile);
			
			reader = new BufferedReader(fileReader);
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
			
		}
		catch(IOException e){
			e.printStackTrace();
			contatti.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		}
		finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return contatti;
	}
	
	public static void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) {
		File destFile = new File(pathFile);
		Boolean fileExists = destFile.exists();	
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(destFile, fileExists);
			
			String[] rubricaTitle = {"NOME", "COGNOME", "TELEFONO", "EMAIL", "NOTE"};
			
			if(!fileExists)
				for(int i=0;i<rubricaTitle.length;i++) {
					writer.write(rubricaTitle[i]);
					if(i!=rubricaTitle.length-1) writer.write(separator);
					else writer.write('\n');
				}
			
			for (Contatto c : contatti) {
				// Ricerca e Scrittura
				for(int i=0;i<rubricaTitle.length;i++) {
					if(i==0) writer.write("\"");
					switch(rubricaTitle[i]) {
					case "NOME":
						writer.write(c.getNome());
						break;
					case "COGNOME":
						writer.write(c.getCognome());	
						break;
					case "TELEFONO":
						writer.write(c.getTelefono());
						break;
					case "EMAIL":
						writer.write(c.getEmail());
						break;
					case "NOTE":
						writer.write(c.getNote());	
						break;
					}
					if(i!=rubricaTitle.length-1) writer.write("\""+separator+"\"");
					else writer.write("\"\n");	
				}
			}
			
			//System.out.println("File creato");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<Contatto> contatti = new ArrayList<>();
		contatti.add(new Contatto("Lorenzo", "Rossi", "333", "lorenzo.rossi@gmail.com", ""));
		contatti.add(new Contatto("Lucia", "Verdi", "444", "lucia.verdi@gmail.com", ""));
		writeRubricaCSV(contatti, "/temp/file/CSVmanager/rubrica.txt", ";");
		
		contatti.clear();
		contatti.add(new Contatto("Rodrigo", "Bianchi", "555", "rodrigo.bianchi@gmail.com", ""));
		contatti.add(new Contatto("Maria", "Rosa", "666", "maria.rosa@gmail.com", ""));
		writeRubricaCSV(contatti, "/temp/file/CSVmanager/rubrica.txt", ";");
		
		readRubricaCSV("/temp/file/CSVmanager/info.txt", ";");
	}

}
