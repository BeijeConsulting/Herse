package it.beije.herse.db.hbm;

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
*	X modifica contatto
*	X cancella contatto
*	X trova contatti duplicati
*	X unisci contatti duplicati
*	X import XML/CSV
*	X export XML/CSV
*/
public class PhoneContManagerHBM {
	
	private static Scanner s = new Scanner(System.in);
	private static String input;
	private static final String pathExportCSV = "/temp/file/RubricaManager/rubricaCSV.csv";
	private static final String pathExportXML = "/temp/file/RubricaManager/rubricaXML.xml";
	
	private static void menu() {
		// HBM log all'inizio
		PhoneContactsHBM.readRubricaHBM("id", "-1");
		
		do {
			System.out.println();
			System.out.println("SELEZIONARE AZIONE:");
			System.out.println("-> VEDERE LISTA CONTATTI");
			System.out.println("-> CERCARE CONTATTO");
			System.out.println("-> INSERIRE NUOVO CONTATTO");
			System.out.println("-> MODIFICARE CONTATTO");
			System.out.println("-> RIMUOVERE CONTATTO");
			System.out.println("-> TROVARE CONTATTI DUPLICATI");
			System.out.println("-> UNIRE CONTATTI DUPLICATI");
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
			case "UNIRE CONTATTI DUPLICATI":
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
				System.out.println("INSERIRE UNA RISPOSTA VALIDA");
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
				System.out.println("INSERIRE SEPARATORE: ");
				String separator = s.nextLine();
				PhoneContactsCSV.writeRubricaCSV(PhoneContactsHBM.readRubricaHBM(), pathExportCSV, separator);
				System.out.println("CONTATTI ESPORTATI");
				break;
			case "XML":
				PhoneContactsXML.writeRubricaXML(PhoneContactsHBM.readRubricaHBM(), pathExportXML);
				System.out.println("CONTATTI ESPORTATI");
				break;
			default:
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA");
				break;
			}
		}while(notValidInput);
	}
	
	public static void importXMLorCSV() {
		boolean notValidInput = false;
		do {
			System.out.println("INSERIRE PATH DEL FILE: ");
			notValidInput = false;
			String path = s.nextLine().toLowerCase();
			
			if(path.endsWith("csv") && new File(path).exists()) {
				System.out.println("INSERIRE SEPARATORE: ");
				String separator = s.nextLine();
				PhoneContactsHBM.insertRubricaHBM(PhoneContactsCSV.readRubricaCSV(path, separator));
				System.out.println("CONTATTI IMPORTATI");
			}
			else if(path.endsWith("xml") && new File(path).exists()) {
				PhoneContactsHBM.insertRubricaHBM(PhoneContactsXML.readRubricaXML(path));
				System.out.println("CONTATTI IMPORTATI");
			}
			else{
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA");
			}
		}while(notValidInput);
	}

	private static void removeDuplicate() {
		PhoneContactsHBM.removeDuplicateHBM();
		System.out.println("DUPLICATI UNITI");
	}

	private static void findDuplicate() {
		System.out.println();
		System.out.println("DUPLICATI: ");
		PhoneContactsHBM.printRubrica(PhoneContactsHBM.findDuplicateHBM());
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
			case "ID":
				where = "id";
				break;
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
				System.out.println("INSERIRE UNA RISPOSTA VALIDA");
				break;
			}
		}while(notValidInput);
		System.out.println();
		System.out.println("SPECIFICARE VALORE DEL CAMPO: ");
		String val = s.nextLine();
		
		PhoneContactsHBM.deleteRubricaHBM(where, val);
		
		System.out.println("CONTATTO RIMOSSO");
	}

	private static void modifyContatto() {
		boolean notValidInput = false;
		String where = null;
		do {
			System.out.println();
			System.out.println("SPECIFICARE CAMPO PER LA RICERCA: ");
			
			notValidInput = false;
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "ID":
				where = "id";
				break;
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
				System.out.println("INSERIRE UNA RISPOSTA VALIDA");
				break;
			}
		}while(notValidInput);
		System.out.println();
		System.out.println("SPECIFICARE VALORE DEL CAMPO: ");
		String whereVal = s.nextLine();
		
		notValidInput = false;
		String attr = null;
		do {
			System.out.println();
			System.out.println("INSERIRE CAMPO DA MODIFICARE: ");
			
			notValidInput = false;
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "ID":
				attr = "id";
				break;
			case "NOME":
				attr = "nome";
				break;
			case "COGNOME":
				attr = "cognome";
				break;
			case "TELEFONO":
				attr = "telefono";
				break;
			case "EMAIL":
				attr = "email";
				break;
			case "NOTE":
				attr = "note";
				break;
			default:
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA");
				break;
			}
		}while(notValidInput);
		System.out.println();
		System.out.println("INSERIRE NUOVO VALORE DEL CAMPO: ");
		String attrVal = s.nextLine();
		
		PhoneContactsHBM.modifyRubricaHBM(attr, attrVal, where, whereVal);
		
		System.out.println("CONTATTO MODIFICATO");
	}

	private static void writeContatto() {
		List<Contatto> contatto = new ArrayList<>();
		Contatto nuovoContatto = new Contatto();
		contatto.add(nuovoContatto);
		
		System.out.println();
		System.out.println("INSERIRE NOME: ");
		nuovoContatto.setNome(s.nextLine());
		System.out.println("INSERIRE COGNOME: ");
		nuovoContatto.setCognome(s.nextLine());
		System.out.println("INSERIRE TELEFONO: ");
		nuovoContatto.setTelefono(s.nextLine());
		System.out.println("INSERIRE EMAIL: ");
		nuovoContatto.setEmail(s.nextLine());
		System.out.println("INSERIRE NOTE: ");
		nuovoContatto.setNote(s.nextLine());
		
		PhoneContactsHBM.insertRubricaHBM(contatto);
		
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
			case "ID":
				where = "id";
				break;
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
				System.out.println("INSERIRE UNA RISPOSTA VALIDA");
				break;
			}
		}while(notValidInput);
		System.out.println();
		System.out.println("SPECIFICARE VALORE DEL CAMPO: ");
		String val = s.nextLine();
		
		PhoneContactsHBM.printRubrica(PhoneContactsHBM.readRubricaHBM(where, val));
	}

	private static void readListaContatti() {
		boolean notValidInput = false;
		do {
			System.out.println();
			System.out.println("ORDINARE PER: ");
			System.out.println("-> NOME");
			System.out.println("-> COGNOME");
			System.out.println("-> NON ORDINARE");
			
			notValidInput = false;
			input = s.nextLine().toUpperCase();
			switch(input) {
			case "NOME":
				PhoneContactsHBM.printRubrica(PhoneContactsHBM.readRubricaHBM("nome"));
				break;
			case "COGNOME":
				PhoneContactsHBM.printRubrica(PhoneContactsHBM.readRubricaHBM("cognome"));
				break;
			case "NON ORDINARE":
				PhoneContactsHBM.printRubrica(PhoneContactsHBM.readRubricaHBM());
				break;
			default:
				notValidInput = true;
				System.out.println("INSERIRE UNA RISPOSTA VALIDA");
				break;
			}
		}while(notValidInput);
	}

	public static void main(String[] args) {
		menu();
	}

}
