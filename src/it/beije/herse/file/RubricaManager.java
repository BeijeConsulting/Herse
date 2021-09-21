package it.beije.herse.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.herse.file.csv.RubricaCSV;
import it.beije.herse.file.xml.RubricaXML;

/*
*  	implementare un gestore di rubrica a linea di comando, con le seguenti funzionalità:
*	->vedi lista contatti (con possibilità di ordinare per nome e cognome a scelta)
*	->cerca contatto
*	->inserisci nuovo contatto
*	->modifica contatto
*	->cancella contatto
*	->trova contatti duplicati
*	->unisci contatti duplicati
*/
public class RubricaManager {
	
	private static Scanner s = new Scanner(System.in);
	private static String input;
	private static final String pathCSV = "/temp/file/RubricaManager/rubricaCSV.csv";
	private static final String pathXML = "/temp/file/RubricaManager/rubricaXML.xml";
	
	public static void startRubrica() {
		System.out.println("SELEZIONARE TIPO RUBRICA:");
		System.out.println("-> CSV");
		System.out.println("-> XML");
		boolean notValidInput = false;
		do {
			notValidInput = false;
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "CSV":
				menu(input);
				break;
			case "XML":
				menu(input);
				break;
			default:
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA: ");
				break;
			}
		}while(notValidInput);
	}
	
	private static void menu(String rubricaType) {
		do {
			System.out.println();
			System.out.println("SELEZIONARE AZIONE:");
			System.out.println("-> VEDERE LISTA CONTATTI");
			System.out.println("-> CERCARE CONTATTO");
			System.out.println("-> INSERIRE NUOVO CONTATTO");
			System.out.println("-> MODIFICA CONTATTO");
			System.out.println("-> CANCELLA CONTATTO");
			System.out.println("-> TROVA CONTATTI DUPLICATI");
			System.out.println("-> ELIMINA CONTATTI DUPLICATI");
			System.out.println("-> USCIRE");
			
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "VEDERE LISTA CONTATTI":
				readListaContatti(rubricaType);
				break;
			case "CERCARE CONTATTO":
				searchContatto(rubricaType);
				break;
			case "INSERIRE NUOVO CONTATTO":
				writeContatto(rubricaType);
				break;
			case "MODIFICA CONTATTO":
				modifyContatto(rubricaType);
				break;
			case "CANCELLA CONTATTO":
				removeContatto(rubricaType);
				break;
			case "TROVA CONTATTI DUPLICATI":
				findDuplicate(rubricaType);
				break;
			case "ELIMINA CONTATTI DUPLICATI":
				removeDuplicate(rubricaType);
				break;
			case "USCIRE":
				return ;
			default:
				System.out.println("INSERIRE UNA RISPOSTA VALIDA: ");
				break;
			}
		}while(!input.equalsIgnoreCase("USCIRE"));
	}

	private static void removeDuplicate(String rubricaType) {
		// TODO Auto-generated method stub
		
	}

	private static void findDuplicate(String rubricaType) {
		// TODO Auto-generated method stub
		/*
		 * Idea: Contatto come List<String>,
		 * c1 e c2 sono duplicati se c1.equals(c2) è true
		 */
	}

	private static void removeContatto(String rubricaType) {
		// TODO Auto-generated method stub
		
	}

	private static void modifyContatto(String rubricaType) {
		// TODO Auto-generated method stub
		/*
		 * Idea: dopo che l'utente seleziona (come?) e modifica il contatto,
		 * removeContatto(quello vecchio) + writeContatto(quello nuovo)
		 */
	}

	private static void writeContatto(String rubricaType) {
		List<Contatto> contatto = new ArrayList<>();
		Contatto nuovoContatto = new Contatto();
		contatto.add(nuovoContatto);
		
		System.out.println();
		System.out.print("INSERISCI NOME: ");
		nuovoContatto.setNome(s.nextLine());
		System.out.print("INSERISCI COGNOME: ");
		nuovoContatto.setCognome(s.nextLine());
		System.out.print("INSERISCI TELEFONO: ");
		nuovoContatto.setTelefono(s.nextLine());
		System.out.print("INSERISCI EMAIL: ");
		nuovoContatto.setEmail(s.nextLine());
		System.out.print("INSERISCI NOTE: ");
		nuovoContatto.setNote(s.nextLine());
		
		if(rubricaType.equalsIgnoreCase("CSV")) RubricaCSV.writeRubricaCSV(contatto, pathCSV, ";");
		else if(rubricaType.equalsIgnoreCase("XML")) RubricaXML.writeRubricaXML(contatto, pathXML);
		
		System.out.println("CONTATTO INSERITO");
	}

	private static void searchContatto(String rubricaType) {
		// TODO Auto-generated method stub
		
	}

	// TODO: ordinare per nome/cognome
	private static void readListaContatti(String rubricaType) {
		if(rubricaType.equalsIgnoreCase("CSV") && new File(pathCSV).exists()) {
			for(Contatto c : RubricaCSV.readRubricaCSV(pathCSV, ";")) {
				System.out.println("NOME: "+c.getNome());
				System.out.println("COGNOME: "+c.getCognome());
				System.out.println("TELEFONO: "+c.getTelefono());
				System.out.println("EMAIL: "+c.getEmail());
				System.out.println("NOTE: "+c.getNote());
			}
		}
		else if(rubricaType.equalsIgnoreCase("XML") && new File(pathXML).exists()) {
			for(Contatto c : RubricaXML.readRubricaXML(pathXML)) {
				System.out.println("NOME: "+c.getNome());
				System.out.println("COGNOME: "+c.getCognome());
				System.out.println("TELEFONO: "+c.getTelefono());
				System.out.println("EMAIL: "+c.getEmail());
				System.out.println("NOTE: "+c.getNote());
			}
		}
		else System.out.println("RUBRICA NON ESISTE");
	}

	public static void main(String[] args) {
		startRubrica();
	}

}
