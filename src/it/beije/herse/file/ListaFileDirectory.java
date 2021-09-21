package it.beije.herse.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.FormatStyle;


	

public class ListaFileDirectory {
	
	static void getNameDirectory(String s) {
		File f = new File(s);
		if(!f.isDirectory())
			return;
		System.out.println("Elenco presente in : " + f.getAbsolutePath());
		for(String name : f.list())
			System.out.println("\t\t\t" + name);
		System.out.println();
		for(int i =0; i<f.listFiles().length; i++)
		getNameDirectory(f.listFiles()[i].getAbsolutePath());
			
	}
	
	static void getNameDirectory(String s, FileWriter w) throws Exception {
		File f = new File(s);
		if(!f.isDirectory())
			return;
		w.write("Elenco presente in : " + f.getAbsolutePath() + "\n");
		for(String name : f.list())
			w.write("\t\t\t" + name + "\n");
		w.write("\n");
		for(int i =0; i<f.listFiles().length; i++)
		getNameDirectory(f.listFiles()[i].getAbsolutePath(), w);
			
	}
	
	static void getNameDirectoryTabulato(String s, FileWriter w) throws Exception {
		File f = new File(s);
		if(f.isDirectory()) {
			w.write(f.getName() + "(dir)" + "\n");
			for(int i = 0; i<f.listFiles().length; i++) {
				w.write("\t");
				getNameDirectoryTabulato(f.listFiles()[i].getAbsolutePath(), w);
				}
			}
		else { 
				w.write("\t" + f.getName() + "\n");
		}
	}
	
		
	public static void main (String[] args)  throws Exception {

		
		File destinazioneFile = new File("/temp/ListaFile.txt");
		FileWriter writer = new FileWriter(destinazioneFile);
		getNameDirectoryTabulato("/Users/PC-HP/Desktop/vuze", writer);
		writer.flush();
		writer.close();
	}
}
