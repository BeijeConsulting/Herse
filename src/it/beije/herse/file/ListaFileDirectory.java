package it.beije.herse.file;

import java.io.File;
	
	

public class ListaFileDirectory {
	
	static void getNameDirectory(String s) {
		File f = new File(s);
		if(!f.isDirectory())
			return;
		System.out.println("Elenco presente in : " + f.getName());
		for(String name : f.list())
			System.out.println(name);
		System.out.println();
		for(int i =0; i<f.listFiles().length; i++)
		getNameDirectory(f.listFiles()[i].getAbsolutePath());
			
	}
	
	
		
	public static void main (String[] args) {
		
		//C:\Users\PC-HP\Desktop\vuze
		getNameDirectory("/Users/PC-HP/Desktop/vuze");
	}
}
