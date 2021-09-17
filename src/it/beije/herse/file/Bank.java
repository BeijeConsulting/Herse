/*
	Gli agenti di borsa della banca Pincopallo compiono operazioni finanziare quotidianamente
	ed annotano le operazioni su un file, nel seguente formato: “ABC 50.0 210 B”, dove:
	- “ABC” è il nome dell’azione acquistata/venduta
	- 50.0 è l’importo della singola azione
	- 210 è la quantità
	- ‘B’ è l’operazione, che può valere ‘B’ (Buy) o ‘S’ (Sell)
	Si vuole quindi un programma che:
	- legga il file inviato dagli agenti e lo restituisca come array (o List) di stringhe
	- per ogni riga calcoli l’importo dell’operazione ed alla fine produca una semplice riga:
	“Op: (nn) Buy: (bb) Sell: (ss)”
	dove al posto di (nn) ho ul numero di operazioni lette, al posto di (bb) l’importo totale
	delle operazioni di acquisto ed in (ss) l’importo totale delle operazioni di vendita.
*/

package it.beije.herse.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Bank {
	
	private ArrayList<String[]> operations = new ArrayList<String[]>();
	private File file;
	
	public Bank(String fileName) throws IOException {
		file = new File(fileName);
		scanFile();
	}
	
	public Bank() {}
	
	private boolean scanFile() throws IOException {
		
		if(file.exists()) {
			
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			
			while(buffer.ready())
				operations.add(buffer.readLine().split(" "));
			
			buffer.close();
			
			return true;
			
		}else {
			
			file = null;
			
			System.out.println("File non trovato");
			
			return false;
			
		}
		
	}

	public void changeFile(String fileName) throws IOException {
		operations.clear();
		file = new File(fileName);
		scanFile();
	}
	
	public void copyFileOp(String fileName) throws IOException {
		
		File newFile = new File(fileName);
		
		FileWriter writer = new FileWriter(newFile);
		
		for(String[] s : operations) {
			writer.write(Arrays.asList(s).toString().replace("[", "").replace("]", "").replace(",", "")  + "\n");
		}
		
		writer.flush();
		writer.close();
		
	}
	
	public void totalReport() {
		
		int nn = 0;
		double ss = 0;
		double bb = 0;
		
		for(String[] s : operations) {
			
			nn++;
			if(s[3].equals("S"))
				ss += Double.parseDouble(s[1]) * Double.parseDouble(s[2]);
			else
				bb += Double.parseDouble(s[1]) * Double.parseDouble(s[2]);
			
		}
		
		System.out.println("Op = " + nn + " Buy = " + bb + " Sell = " + ss);
		
	}

	public void printOperations() {
		
		for(String[] arrString : operations) {
			for(String s : arrString)
				System.out.print(s + " ");
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Bank banca = new Bank("\\Users\\savin\\Desktop\\operazioni.txt");
		banca.printOperations();
		banca.totalReport();
		banca.copyFileOp("\\Users\\savin\\Desktop\\newoperazioni.txt");
		
	}

}
