package it.beije.herse.file;

import java.io.BufferedReader;
import java.io.FileReader;
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
		reader.close();

		return new ArrayList<Contatto>();
	}

	public static void main(String[] args) throws Exception {

		//readRubricaCSV("/temp/prova.txt", ";");
		//readRubricaCSV("/temp/prova1.txt", ",");
		List<Contatto> contatti = readRubricaCSV("/temp/prova2.txt", "\t");
		System.out.println("contatti number: " + contatti.size());

	}

}
