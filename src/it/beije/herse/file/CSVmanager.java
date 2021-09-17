package it.beije.herse.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * esercizio: aggiungere da parametro le informazioni con cui andare a riorganizzare gli elementi rubrica del primo file
 * dove il primo arg è separatore da utilizzare ed i restanti i nomi delle colonne, SE viene specificata una colonna di valori
 * NON presente nel file iniziale, aggiungere comunque la nuova colonna con valori "vuoti"
 * 
 * es. java CVSmanager | cognome Nome eMail NOTE Indirizzo
 * 
 * I NOMI DEI CAMPI NEL NUOVO FILE DEVONO COMUNQUE ESSERE MAIUSCOLI
 */
public class CSVmanager {
	
	private String[] title;
	private List<String[]> rows = new ArrayList<String[]>();
	
	public void readAndPrint(File f) throws Exception {
		//System.out.println("File exists ? " + f.exists());
		
		FileReader fileReader = new FileReader(f);
		
		BufferedReader reader = new BufferedReader(fileReader);
		String row = reader.readLine();
		title = row.split("\";\"");
		int last = title.length - 1;
		title[0] = title[0].substring(1);
		title[last] = title[last].substring(0, title[last].length()-1);
		String[] cols = null;
		
		while (reader.ready()) {
			row = reader.readLine();
			System.out.println(row);
			
			cols = row.split("\";\"");
			cols[0] = cols[0].substring(1);
			cols[last] = cols[last].substring(0, cols[last].length()-1);
			rows.add(cols);
			
			for (int i = 0; i <= last; i++) {
				System.out.println(title[i] + ": " + cols[i]);
			}
			
			System.out.println();
		}
		
		reader.close();
	}
	
	public void rewrite() throws Exception {
		File destFile = new File("/temp/file/CSVmanager/rubrica.csv");
		//System.out.println("destFile exists ? " + destFile.exists()+"\n");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserire Separatore e Nomi delle Colonne:");
		String input = scanner.nextLine();
		scanner.close();
		
		String[] destFileTitle = input.split(" ");
			
		FileWriter writer = new FileWriter(destFile);
		
		for(int i=1;i<destFileTitle.length;i++) {
			writer.write(destFileTitle[i].toUpperCase());
			if(i!=destFileTitle.length-1) writer.write(destFileTitle[0]);
			else writer.write('\n');
		}
		
		for (String[] columns : rows) {
			
			// Ricerca e Scrittura
			boolean newCol = false;
			for(int i=1;i<destFileTitle.length;i++) {
				for(int j=0;j<title.length;j++) {
					// Se ho già la colonna, ricopia i dati + passa alla nuova colonna
					if(destFileTitle[i].equalsIgnoreCase(title[j])) {
						newCol = false;
						writer.write(columns[j]);
						// se non sono all'ultimo campo, scrivi il separatore
						if(i!=destFileTitle.length-1) writer.write(destFileTitle[0]);
						else writer.write('\n');
						break;
					}
					newCol = true;
				}
				// Se ho una nuova colonna, inserisco un dato di default
				if(newCol) {
					writer.write("   ");
					if(i!=destFileTitle.length-1) writer.write(destFileTitle[0]);
					else writer.write('\n');
				}
			}
		}
		
		System.out.println("File creato");
		
		writer.flush();
		writer.close();
	}
	
	public static void main(String[] args) throws Exception {
		File f = new File("/temp/file/CSVmanager/info.txt");
		
		CSVmanager manager = new CSVmanager();
		manager.readAndPrint(f);
		manager.rewrite();
	}

}
