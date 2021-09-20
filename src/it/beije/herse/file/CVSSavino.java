package it.beije.herse.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

public class CVSSavino {

	/*
	 * esercizio: aggiungere da parametro le informazioni con cui andare a riorganizzare gli elementi rubrica del primo file
	 * dove il primo arg è separatore da utilizzare ed i restanti i nomi delle colonne, SE viene specificata una colonna di valori
	 * NON presente nel file iniziale, aggiungere comunque la nuova colonna con valori "vuoti"
	 * 
	 * es. java CVSmanager | cognome Nome eMail NOTE Indirizzo
	 * 
	 * I NOMI DEI CAMPI NEL NUOVO FILE DEVONO COMUNQUE ESSERE MAIUSCOLI
	 */

	public static void managerFile(File oldFile, File newFile, String...strings) throws IOException {

		FileWriter fw = new FileWriter(newFile);
		BufferedReader br = new BufferedReader(new FileReader(oldFile));
		ArrayList<String[]> list = new ArrayList<String[]>();

		while(br.ready()) {
			String s = br.readLine();
			list.add(s.substring(1, s.length()-1).split(strings[0]));
		}

		for(int i = 1; i < list.size(); i++) {

			for(int j = 1, z = 0; j < strings.length; j++) {

				if(z < list.get(0).length) {

					if(list.get(0)[z].equalsIgnoreCase(strings[j])) {
						fw.write(strings[j].toUpperCase() + "\t" + list.get(i)[z] + "\n");
						z++;
					}else {
						fw.write(strings[j].toUpperCase() + "\tvuoto\n");
					}

				}else {
					fw.write(strings[j].toUpperCase() + "\tvuoto\n");
				}

			}

			fw.write("\n");

		}

		fw.flush();
		br.close();
		fw.close();

	}

	public static void main(String[] args) throws Exception {
		File f = new File("\\Users\\savin\\Desktop\\fileTest\\rubrica.txt");
		//System.out.println("exists ? " + f.exists());

		FileReader fileReader = new FileReader(f);
		
		managerFile(f,new File("\\Users\\savin\\Desktop\\fileTest\\newrubrica.txt"),"\";\"","nome","cognome","telefono","email","indirizzo","note");

		fileReader.close();

	}

}
