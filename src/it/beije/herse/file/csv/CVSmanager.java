package it.beije.herse.file.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CVSmanager {
	
/*
 
"NOME";"COGNOME";"TELEFONO";"EMAIL";"NOTE"
"mario";"rossi";"333333333";"mario@rossi.it";"zio mario"
"carlo maria";"rossi";"333333334";"carlo@rossi.it";"nonno carlo"
"giovanni";"rossi";"333333335";"giovanni@rossi.it";"vicino di casa; persona gentile"	


 * esercizio: aggiungere da parametro le informazioni con cui andare a riorganizzare gli elementi rubrica del primo file
 * dove il primo arg è separatore da utilizzare ed i restanti i nomi delle colonne, SE viene specificata una colonna di valori
 * NON presente nel file iniziale, aggiungere comunque la nuova colonna con valori "vuoti"
 * 
 * es. java CVSmanager | cognome Nome eMail NOTE Indirizzo
 * 
 * I NOMI DEI CAMPI NEL NUOVO FILE DEVONO COMUNQUE ESSERE MAIUSCOLI
 */

	public static void main(String[] args) throws Exception {
		File f = new File("/temp/prova.txt");
		System.out.println("exists ? " + f.exists());
		
		FileReader fileReader = new FileReader(f);//new FileReader("/temp/prova.txt");
//		while (fileReader.ready()) {
//			System.out.print((char)fileReader.read());
//		}
		
		BufferedReader reader = new BufferedReader(fileReader);
		String row = reader.readLine();
		String[] title = row.split("\";\"");
		int last = title.length - 1;
		title[0] = title[0].substring(1);
		title[last] = title[last].substring(0, title[last].length()-1);
		String[] cols = null;
		List<String[]> rows = new ArrayList<String[]>();
		
		while (reader.ready()) {
			row = reader.readLine();
			System.out.println(row);
			
			cols = row.split("\";\"");
			cols[0] = cols[0].substring(1);
			cols[last] = cols[last].substring(0, cols[last].length()-1);
			rows.add(cols);
//			System.out.println("nome: " + val[0].substring(1));
//			System.out.println("cognome: " + val[1]);
//			System.out.println("telefono: " + val[2]);
//			System.out.println("email: " + val[3]);
//			System.out.println("note: " + val[4].substring(0,val[4].length()-1));
			for (int i = 0; i <= last; i++) {
				System.out.println(title[i] + ": " + cols[i]);
			}
			
			System.out.println();
		}
		
		reader.close();
		
		System.out.println("rows number: " + rows.size());
		
		File destFile = new File("/temp/rubrica.csv");
		System.out.println("destFile exists ? " + destFile.exists());

		FileWriter writer = new FileWriter(destFile);
		writer.write(title[1]);
		writer.write('\t');
		writer.write(title[0]);
		writer.write('\t');
		writer.write(title[2]);
		writer.write('\t');
		writer.write(title[3]);
		writer.write('\n');
		for (String[] columns : rows) {
			writer.write(columns[1]);
			writer.write('\t');
			writer.write(columns[0]);
			writer.write('\t');
			writer.write(columns[2]);
			writer.write('\t');
			writer.write(columns[3]);
			writer.write('\n');
		}
		
		writer.flush();
		writer.close();
	}
	
}
