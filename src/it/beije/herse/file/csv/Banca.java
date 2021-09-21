package it.beije.herse.file.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
Gli agenti di borsa della banca Pincopallo compiono operazioni finanziare quotidianamente ed 
annotano le operazioni su un file, nel seguente formato: “ABC 50.0 210 B”, dove
- “ABC” è il nome dell’azione acquistata/venduta
- 50.0 è l’importo della singola azione
- 210 è la quantità
- ‘B’ è l’operazione, che può valere ‘B’ (Buy) o ‘S’ (Sell)
Si vuole quindi un programma che:
- legga il file inviato dagli agenti e lo restituisca come array (o List) di stringhe
- per ogni riga calcoli l’importo dell’operazione ed alla fine produca una semplice riga:
“Op: (nn) Buy: (bb) Sell: (ss)”
dove al posto di (nn) ho ul numero di operazioni lette, al posto di (bb) l’importo totale delle 
operazioni di acquisto ed in (ss) l’importo totale delle operazioni di vendita.
 */
public class Banca {
	
	private BufferedReader reader;
	private int numOperations = 0;
	private double totBuy = 0;
	private double totSell = 0;

	public void stampaTotOperzaioni() throws IOException {
		reader = new BufferedReader(new FileReader("/temp/file/EsBanca/OperazioniFinanziarie.txt"));
		String line = null;
		while (reader.ready()) {
			line = reader.readLine();
			numOperations++;
			
			String[] op = line.split(" ");
			if(op[3].equalsIgnoreCase("B")) totBuy += Double.parseDouble(op[1])*Integer.parseInt(op[2]);
			else totSell += Double.parseDouble(op[1])*Integer.parseInt(op[2]);
		}
		System.out.println("Op: "+numOperations+", Buy: "+totBuy+", Sell: "+totSell);
	}

	public static void main(String[] args) throws Exception {
		Banca b = new Banca();
		b.stampaTotOperzaioni();
	}

}
