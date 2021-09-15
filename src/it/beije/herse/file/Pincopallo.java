package it.beije.herse.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Pincopallo {

	public static void main(String[] args)  throws Exception {
		File f = new File("/temp/pincopallo.txt");
		System.out.println("exist? " + f.exists());
		
		FileReader fileReader = new FileReader(f);
		BufferedReader reader = new BufferedReader(fileReader);
		ArrayList <String> lista = new ArrayList<>();
		while(reader.ready()) {
			lista.add(reader.readLine());
			}
		
	//	System.out.println(lista);
		
		double buy = 0;
		double sell = 0;
		int num = lista.size();
		System.out.println(num);
		String [] s = new String[4];
		for(int i = 0; i<lista.size(); i++) {
			s = lista.get(i).split(" ");
			if(s[3].equals("B"))
				buy += Double.parseDouble(s[1]);
			else
				sell +=Double.parseDouble(s[1]);
		}
		
		System.out.println("Op:(" + num + ") Buy:(" + buy + ") Sell:(" + sell + ")");
		
		
	}

}


