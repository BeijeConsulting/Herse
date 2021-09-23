package it.beije.herse.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.herse.file.Contatto;
import it.beije.herse.file.csv.PhoneContactsCSV;
import it.beije.herse.file.xml.PhoneContactsXML;

/*
*  	implementare un gestore di rubrica a linea di comando, con le seguenti funzionalità:
*	X vedi lista contatti (con possibilità di ordinare per nome e cognome a scelta)
*	X cerca contatto
*	X inserisci nuovo contatto
*	->modifica contatto
*	X cancella contatto
*	->trova contatti duplicati
*	->unisci contatti duplicati
*	->import XML/CSV
*	->export XML/CSV
*/
public class PhoneContactsManager {
	
	private static Scanner s = new Scanner(System.in);
	private static String input;
	private static final String pathCSV = "/temp/file/RubricaManager/rubricaCSV.csv";
	private static final String pathXML = "/temp/file/RubricaManager/rubricaXML.xml";
	
	private static void menu() {
		do {
			System.out.println();
			System.out.println("SELEZIONARE AZIONE:");
			System.out.println("-> VEDERE LISTA CONTATTI");
			System.out.println("-> CERCARE CONTATTO");
			System.out.println("-> INSERIRE NUOVO CONTATTO");
			System.out.println("-> MODIFICARE CONTATTO");
			System.out.println("-> RIMUOVERE CONTATTO");
			System.out.println("-> TROVARE CONTATTI DUPLICATI");
			System.out.println("-> ELIMINARE CONTATTI DUPLICATI");
			System.out.println("-> IMPORTARE RUBRICA");
			System.out.println("-> ESPORTARE RUBRICA");
			System.out.println("-> USCIRE");
			
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "VEDERE LISTA CONTATTI":
				readListaContatti();
				break;
			case "CERCARE CONTATTO":
				searchContatto();
				break;
			case "INSERIRE NUOVO CONTATTO":
				writeContatto();
				break;
			case "MODIFICARE CONTATTO":
				modifyContatto();
				break;
			case "RIMUOVERE CONTATTO":
				removeContatto();
				break;
			case "TROVARE CONTATTI DUPLICATI":
				findDuplicate();
				break;
			case "ELIMINARE CONTATTI DUPLICATI":
				removeDuplicate();
				break;
			case "IMPORTARE RUBRICA":
				importXMLorCSV();
				break;
			case "ESPORTARE RUBRICA":
				exportXMLorCSV();
				break;
			case "USCIRE":
				return ;
			default:
				System.out.println("INSERIRE UNA RISPOSTA VALIDA: ");
				break;
			}
		}while(!input.equalsIgnoreCase("USCIRE"));
	}
	
	public static void exportXMLorCSV() {
		System.out.println("SELEZIONARE TIPO RUBRICA:");
		System.out.println("-> CSV");
		System.out.println("-> XML");
		boolean notValidInput = false;
		do {
			notValidInput = false;
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "CSV":
				//TODO EXPORT
				break;
			case "XML":
				//TODO EXPORT
				break;
			default:
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA: ");
				break;
			}
		}while(notValidInput);
	}
	
	public static void importXMLorCSV() {
		System.out.println("INSERIRE PATH DEL FILE: ");
		
		boolean notValidInput = false;
		do {
			notValidInput = false;
			input = s.nextLine().toLowerCase();
			
			if(input.endsWith("csv")) {
				//TODO IMPORT
			}
			else if(input.endsWith("xml")) {
				//TODO IMPORT
			}
			else{
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA: ");
			}
		}while(notValidInput);
	}

	private static void removeDuplicate() {
		// TODO Auto-generated method stub
		
	}

	private static void findDuplicate() {
		// TODO Auto-generated method stub
		
	}

	private static void removeContatto() {
		boolean notValidInput = false;
		String where = null;
		do {
			System.out.println();
			System.out.println("SPECIFICARE CAMPO PER LA RIMOZIONE: ");
			
			notValidInput = false;
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "NOME":
				where = "nome";
				break;
			case "COGNOME":
				where = "cognome";
				break;
			case "TELEFONO":
				where = "telefono";
				break;
			case "EMAIL":
				where = "email";
				break;
			case "NOTE":
				where = "note";
				break;
			default:
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA: ");
				break;
			}
		}while(notValidInput);
		System.out.println();
		System.out.println("SPECIFICARE VALORE DEL CAMPO: ");
		String val = s.nextLine();
		
		PhoneContactsJDBC.readRubricaJDBC(where, val);
	}

	private static void modifyContatto() {
		// TODO Auto-generated method stub
	}

	private static void writeContatto() {
		List<Contatto> contatto = new ArrayList<>();
		Contatto nuovoContatto = new Contatto();
		contatto.add(nuovoContatto);
		
		System.out.println();
		System.out.print("INSERIRE NOME: ");
		nuovoContatto.setNome(s.nextLine());
		System.out.print("INSERIRE COGNOME: ");
		nuovoContatto.setCognome(s.nextLine());
		System.out.print("INSERIRE TELEFONO: ");
		nuovoContatto.setTelefono(s.nextLine());
		System.out.print("INSERIRE EMAIL: ");
		nuovoContatto.setEmail(s.nextLine());
		System.out.print("INSERIRE NOTE: ");
		nuovoContatto.setNote(s.nextLine());
		
		PhoneContactsJDBC.preparedInsertRubricaJDBC(contatto);
		
		System.out.println("CONTATTO INSERITO");
	}

	private static void searchContatto() {
		boolean notValidInput = false;
		String where = null;
		do {
			System.out.println();
			System.out.println("SPECIFICARE CAMPO PER LA RICERCA: ");
			
			notValidInput = false;
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "NOME":
				where = "nome";
				break;
			case "COGNOME":
				where = "cognome";
				break;
			case "TELEFONO":
				where = "telefono";
				break;
			case "EMAIL":
				where = "email";
				break;
			case "NOTE":
				where = "note";
				break;
			default:
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA: ");
				break;
			}
		}while(notValidInput);
		System.out.println();
		System.out.println("SPECIFICARE VALORE DEL CAMPO: ");
		String val = s.nextLine();
		
		PhoneContactsJDBC.readRubricaJDBC(where, val);
	}

	private static void readListaContatti() {
		boolean notValidInput = false;
		do {
			System.out.println();
			System.out.print("ORDINARE PER: ");
			System.out.print("-> NOME");
			System.out.print("-> COGNOME");
			System.out.print("-> NON ORDINARE");
			
			notValidInput = false;
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "NOME":
				PhoneContactsJDBC.readRubricaJDBC("nome");
				break;
			case "COGNOME":
				PhoneContactsJDBC.readRubricaJDBC("cognome");
				break;
			case "NON ORDINARE":
				PhoneContactsJDBC.readRubricaJDBC();
				break;
			default:
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA: ");
				break;
			}
		}while(notValidInput);
	}

	public static void main(String[] args) {
		menu();
	}

}
