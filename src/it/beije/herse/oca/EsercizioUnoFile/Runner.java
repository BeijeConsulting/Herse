package it.beije.herse.oca.EsercizioUnoFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class Runner {

	public static void main(String[] args) throws Exception{
				File f = new File("/Users/omarelmounjid/Desktop/File.txt");
				//System.out.println("exists ? " + f.exists()+"\n");
				FileReader fileReader = new FileReader(f);			
				while (fileReader.ready()) {
					System.out.print((char)fileReader.read());
				}
				
				BufferedReader reader = new BufferedReader(fileReader);
				String row = null;
				//System.out.println(reader.);
				while (!reader.ready()) {
					row = reader.readLine();
					System.out.println(row);
					String[] val = row.split(";");
					System.out.println("nome: " + val[0]);
					System.out.println("nome: " + val[1]);
					System.out.println("nome: " + val[2]);
					System.out.println("nome: " + val[3]);
				}
}
}


