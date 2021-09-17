package it.beije.herse.oca.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Banca {
	
	public static void main(String[] args)  {
		leggiFile();
		
	}

	

	public static void leggiFile() {
		// new FileReader("/temp/prova.txt");
//		while (fileReader.ready()) {
//			System.out.print((char)fileReader.read());
//		}
		
		File f = new File("C://Users//Account//Desktop/esercizi/file/prova.txt");
		
		System.out.println("exists ? " + f.exists());

		FileReader fileReader;
		try {
			fileReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fileReader);
			String row = null;
			long lines = 0;
			String operazione = null;
			double totBuy = 0;
			double totSell = 0;
			
			while (reader.ready()) {
				lines++;
				row = reader.readLine();
				System.out.println(row);

				//valuto il contenuto di ogni riga
				String[] val = row.split(" ");
				
				System.out.println("nome azienda: " + val[0]);
				System.out.println("importo azione: " + val[1]);
				System.out.println("totale numero azioni: " + val[2]);
				if(val[3].equals("B")) {
					operazione = "Buy";
					//calcolo totale azioni comprate
					totBuy += Double.parseDouble(val[2]) * Double.parseDouble(val[1]);
				} else if(val[3].equals("S")) {
					operazione = "Sell";
					//calcolo totale azioni vendute
					totSell += Double.parseDouble(val[2]) * Double.parseDouble(val[1]);
				}
				System.out.println("tipo operazione: " + operazione);
				System.out.println();
				
				
			}
			
			System.out.println("Op: " + lines + ". Buy: " + totBuy + ". Sell: " + totSell);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
