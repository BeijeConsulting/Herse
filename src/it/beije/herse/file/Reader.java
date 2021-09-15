package it.beije.herse.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Reader {

	public static void main(String[] args) throws Exception {
		File f = new File("/temp/prova.txt");
		System.out.println("exists ? " + f.exists());
		
		FileReader fileReader = new FileReader(f);//new FileReader("/temp/prova.txt");
//		while (fileReader.ready()) {
//			System.out.print((char)fileReader.read());
//		}
		
		BufferedReader reader = new BufferedReader(fileReader);
		String row = null;
		while (reader.ready()) {
			row = reader.readLine();
			System.out.println(row);
			
			String[] val = row.split(";");
			System.out.println("nome: " + val[0]);
			System.out.println("cognome: " + val[1]);
			System.out.println("telefono: " + val[2]);
			System.out.println("email: " + val[3]);
			System.out.println();
		}
 
	}

}