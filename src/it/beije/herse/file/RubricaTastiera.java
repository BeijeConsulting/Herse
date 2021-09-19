package it.beije.herse.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class RubricaTastiera {
	
//	static String inputScanner() {
//		Scanner sc = new Scanner(System.in);
//		String s = sc.next();
//		sc.close();
//		return s;
//	}
	
	static boolean inserireContatto(Scanner s) {
			System.out.println("Vuoi inserire un nuovo contatto?(SI//NO)");
			String risposta = s.next();
			if(risposta.equalsIgnoreCase("si"))
				return true;
			else return false;
	}
	
	static String inputNome(Scanner s) {
		System.out.println("Inserire nome ");
		return s.next();
	}
	
	static String inputCognome(Scanner s) {
		System.out.println("Inserire cognome ");
		return s.next();
	}
	
	static String inputTelefono(Scanner s) {
		System.out.println("Inserire telefono ");
		return s.next();
		
	}
	
	static String inputEmail(Scanner s) {
		System.out.println("Inserire Email ");
		return s.next();
	}
	
	static String inputNote(Scanner s) {
		System.out.println("Inserire Note ");
		return s.next();
	}
	
	private static void scrivereFile(List<Contatto> contatti)  throws IOException {
		String separatorChar = "\";\"";
		File f = new File("/temp/rubricaScanner.txt");
		FileWriter writer = new FileWriter(f, true);
//		writer.write("NOME" + separatorChar + "COGNOME" + separatorChar + "TELEFONO" + separatorChar);
//		writer.write("EMAIL" + separatorChar + "NOTE\n");
		Contatto contatto = null;
		for(int i = 0; i<contatti.size(); i++) {
			contatto = contatti.get(i);
			writer.write(contatto.getNome() + separatorChar);
			writer.write(contatto.getCognome() + separatorChar);
			writer.write(contatto.getTelefono() + separatorChar);
			writer.write(contatto.getEmail() + separatorChar);
			writer.write(contatto.getNome() + "\n");
		}
		writer.flush();
		writer.close();
		
	}


	
	

	public static void main(String[] args) throws Exception {
		List<Contatto> contatti = new ArrayList<Contatto>();
		Contatto contatto = new Contatto();
		Scanner s = new Scanner(System.in);
		while(inserireContatto(s)) {
			contatto.setNome(inputNome(s));
			contatto.setCognome(inputCognome(s));
			contatto.setTelefono(inputTelefono(s));
			contatto.setEmail(inputEmail(s));
			contatto.setNote(inputNote(s));
			contatti.add(contatto);
			
		}
		s.close();
		scrivereFile(contatti);

	}

}
