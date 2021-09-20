package it.beije.herse.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	public static void writeRubricaCSV(List<Contatto> contatti) throws Exception {
		File destFile = new File("/temp/file/CSVmanager/rubrica.txt");
		//System.out.println("destFile exists ? " + destFile.exists()+"\n");
			
		FileWriter writer = new FileWriter(destFile);
		
		String[] rubricaTitle = {"NOME", "COGNOME", "TELEFONO", "EMAIL", "NOTE"};
		for(int i=0;i<rubricaTitle.length;i++) {
			writer.write(rubricaTitle[i]);
			if(i!=rubricaTitle.length-1) writer.write(";");
			else writer.write('\n');
		}
		
		for (Contatto c : contatti) {
			// Ricerca e Scrittura
			for(int i=0;i<rubricaTitle.length;i++) {
				switch(rubricaTitle[i]) {
				case "NOME":
					writer.write(c.getNome());
					if(i!=rubricaTitle.length-1) writer.write(";");
					else writer.write('\n');		
					break;
				case "COGNOME":
					writer.write(c.getCognome());
					if(i!=rubricaTitle.length-1) writer.write(";");
					else writer.write('\n');	
					break;
				case "TELEFONO":
					writer.write(c.getTelefono());
					if(i!=rubricaTitle.length-1) writer.write(";");
					else writer.write('\n');	
					break;
				case "EMAIL":
					writer.write(c.getEmail());
					if(i!=rubricaTitle.length-1) writer.write(";");
					else writer.write('\n');
					break;
				case "NOTE":
					writer.write(c.getNote());
					if(i!=rubricaTitle.length-1) writer.write(";");
					else writer.write('\n');	
					break;
				}
			}
		}
		
		System.out.println("File creato");
		
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) throws Exception {
		
//		List<Contatto> contatti = readRubricaCSV("/temp/file/CSVmanager/info.txt", ";");
//		System.out.println("contatti number: " + contatti.size());
		
		List<Contatto> contatti = new ArrayList<>();
		contatti.add(new Contatto("Matteo", "Capitano", "333", "thecap@gmail.com", ""));
		contatti.add(new Contatto("Hugo", "Boss", "444", "boss@gmail.com", ""));
		
		writeRubricaCSV(contatti);
	}

}
