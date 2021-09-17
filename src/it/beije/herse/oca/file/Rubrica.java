package it.beije.herse.oca.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Rubrica {

	public static void main(String[] args) throws Exception {
		List<String> titleToFileWritten = new ArrayList<String>();
		titleToFileWritten.add("eta");
		titleToFileWritten.add("nome");
		titleToFileWritten.add("telefono");
		titleToFileWritten.add("email");
		leggiScriviFile("\";\"", titleToFileWritten);

	}

	public static void leggiScriviFile(String separatore, List<String> titleCsv) throws Exception {
		File f = new File("C://Users//Account//Desktop/esercizi/file/rubrica.txt");
		System.out.println("exists ? " + f.exists());

		FileReader fileReader = new FileReader(f);

		BufferedReader reader = new BufferedReader(fileReader);
		String row = reader.readLine();
		String[] title = row.split(separatore);
		int last = title.length - 1;
		title[0] = title[0].substring(1);
		title[last] = title[last].substring(0, title[last].length() - 1);
		String[] cols = null;
		List<String[]> rows = new ArrayList<String[]>();

		while (reader.ready()) {
			row = reader.readLine();
			cols = row.split(separatore);
			cols[0] = cols[0].substring(1);
			cols[last] = cols[last].substring(0, cols[last].length() - 1);
			rows.add(cols);
			for (int i = 0; i <= last; i++) {
				System.out.println(title[i] + ": " + cols[i]);
			}
			System.out.println();
		}
		reader.close();

		System.out.println("rows number: " + rows.size());

		File destFile = new File("C://Users//Account//Desktop/esercizi/file/rubrica.csv");
		System.out.println("destFile exists ? " + destFile.exists());

		FileWriter writer = new FileWriter(destFile);

		//MAIN_HEADING: for (String element : title) {
			for (int i = 0; i < titleCsv.size(); i++) {
				if (titleCsv.get(i).equalsIgnoreCase(title[i])) {
					writer.write(titleCsv.get(i).toUpperCase());
					writer.write('\t');
					if (i == titleCsv.size() - 1) {
						writer.write('\n');
					}
					
				} else {
					writer.write(titleCsv.get(i).toUpperCase());
					writer.write('\t');
					if (i == titleCsv.size() - 1) {
						writer.write('\n');
					}
//					for (String[] columns : rows) {
//						writer.write(columns[i] = "");
//						writer.write('\t');
//						if (i == titleCsv.size() - 1) {
//							writer.write('\n');
//						}
//					}

				}
				for (String[] columns : rows) {

					writer.write(columns[i]);
					writer.write('\t');
					if (columns[i] == columns[3]) {
						writer.write('\n');
					}
				}

			}
		//}
		writer.flush();
		writer.close();
	}

}
