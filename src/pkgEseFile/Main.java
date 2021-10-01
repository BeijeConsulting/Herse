package pkgEseFile;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Users\\samue\\Desktop\\rubrica.txt");
		Scanner sc = new Scanner(System.in);
		Contatto contatto;
		if(f.exists()) {
			int scelta = 0;
			do {
				System.out.println("1 - Inserisci nuovo contatto");
				System.out.println("2 - Visualizza rubrica");
				System.out.println("3 - Visualizza contatto");
				System.out.println("0 - Esci");
				scelta = sc.nextInt();
				System.out.println("");
				switch(scelta) {
					case 1:
						System.out.println("Inserisci nome: ");
						String nome = sc.next();
						System.out.println("");
						System.out.println("Inserisci cognome: ");
						String cognome = sc.next();
						System.out.println("");
						System.out.println("Inserisci numero di telefono: ");
						String telefono = sc.next();
						System.out.println("");
						System.out.println("Inserisci email: ");
						String email = sc.next();
						if(nome.equalsIgnoreCase("")) {
							nome = "vuoto";
						}
						if(cognome.equalsIgnoreCase("")) {
							cognome = "vuoto";
						}
						if(telefono.equalsIgnoreCase("")) {
							telefono = "vuoto";
						}
						if(email.equalsIgnoreCase("")) {
							email = "vuoto";
						}
						contatto = new Contatto(nome, cognome, telefono, email);
						if(inserisci(contatto)) {
							System.out.println("Contatto inserito");
						}else {
							System.out.println("Contatto non inserito");
						}
						break;
					case 2:
						ArrayList<Contatto> rubrica = visualizza();
						for(int i = 0; i < rubrica.size(); i++) {
							System.out.println("Cognome: " + rubrica.get(i).getCognome() + ";");
							System.out.println("Nome: " + rubrica.get(i).getNome() + ";");
							System.out.println("Telefono: " + rubrica.get(i).getTelefono() + ";");
							System.out.println("Email: " + rubrica.get(i).getEmail() + ";");
							System.out.println("");
						}
						break;
					case 3:
						System.out.println("Inserisci nome: ");
						String nom = sc.next();
						System.out.println("");
						System.out.println("Inserisci cognome: ");
						String cognom = sc.next();
						System.out.println("");
						Contatto c = visualizzaContatto(nom, cognom);
						if(c.getCognome().equalsIgnoreCase(cognom) || c.getNome().equalsIgnoreCase(nom)) {
							System.out.println("Cognome: " + c.getCognome() + ";");
							System.out.println("Nome: " + c.getNome() + ";");
							System.out.println("Telefono: " + c.getTelefono() + ";");
							System.out.println("Email: " + c.getEmail() + ";");
							System.out.println("");
						}else {
							System.out.println("Contatto non trovato");
						}
						break;
					case 0:
						System.out.println("Arrivederci!!!");
						break;
				}
			}while(scelta != 0);
		}
	}

	public static boolean inserisci(Contatto contatto) throws IOException {
		boolean b = false;
		File f = new File("C:\\Users\\samue\\Desktop\\rubrica.txt");
		if(f.exists()) {
			FileWriter fw = new FileWriter(f);
			fw.write(contatto.getCognome() + " ");
			fw.write(contatto.getNome() + " ");
			fw.write(contatto.getTelefono() + " ");
			fw.write(contatto.getEmail() + " ");
			fw.write('\n');
			b = true;
		}else {
			b = false;
		}
		return b;
	}
	
	public static ArrayList<Contatto> visualizza() throws IOException{
		File f = new File("C:\\Users\\samue\\Desktop\\rubrica.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<Contatto> rubrica = new ArrayList<Contatto>();
		String row = null;
		while(br.ready()) {
			row = br.readLine();
			String[] val = row.split(" ");
			Contatto c = new Contatto(val[0], val[1], val[2], val[3]);
			rubrica.add(c);
		}
		return rubrica;
	}
	
	public static Contatto visualizzaContatto(String nome, String cognome) throws IOException{
		File f = new File("C:\\Users\\samue\\Desktop\\rubrica.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String row = null;
		while(br.ready()) {
			row = br.readLine();
			String[] val = row.split(" ");
			if((cognome.equalsIgnoreCase(val[0]))&&(nome.equalsIgnoreCase(val[1]))) {
				Contatto contatto = new Contatto(val[0], val[1], val[2], val[3]);
				return contatto;
			}
		}
		return null;
	}
}
