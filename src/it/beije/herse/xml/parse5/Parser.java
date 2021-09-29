package it.beije.herse.xml.parse5;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Parser {
		// TODO Auto-generated method stub
	/*
	 * difficoltà: oggetto senza tutte le caratteristiche, considerare gli attributi
	 */
	public static void main(String[] args) throws IOException {
		String path="/temp/test_parser1.xml";
		//leggiFileXml(path);;
	//	System.out.println(getRootElement(path););
	//	getTagName(path);
	//	getTextContent(path);
		getChildNodes(path,"utente");
	}
	public static List<Utente> leggiFileXml(String path) throws IOException {
		File file = new File(path);
		System.out.println("exists ? " + file.exists());
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String row = null;
		String stringaEncoding = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		List<Utente> listaUtenti = new ArrayList<Utente>();
		String nome ="";
		String cognome ="";
		int eta =-1;
		while (br.ready()) {
			row = br.readLine();
			row = row.trim();
			if (row.equals(stringaEncoding))
				continue;
			StringBuilder val = new StringBuilder(row);
			String tag = val.substring(val.indexOf("<") + 1, val.indexOf(">"));
			if (val.indexOf(">") == val.length() - 1) {
				if(tag.contains("/utente")) {
					listaUtenti.add(new Utente(nome, cognome, eta));
				}
				continue;
			} else {
				//if(tag.equals("/utente")) {
					String campo = val.substring(val.indexOf(">") + 1, val.lastIndexOf("<"));
					System.out.println(campo);
					switch (tag) {
					case "nome":
						nome = campo;
						break;
					case "cognome":
						cognome = campo;
						break;
					case "eta":
						eta = Integer.parseInt(campo);						
						break;
					}
				//}
			}
		}
		System.out.println(listaUtenti);
		return listaUtenti;
	}
	public static String getRootElement(String path) throws IOException {
		File file = new File(path);
		System.out.println("exists ? " + file.exists());
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String row = null;
		String stringaEncoding = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String stringaEncoding2 = "<?xml version=\"1.0\"?>";
		String root = "";
		while (br.ready()) {
			row = br.readLine();
			row = row.trim();
			if (row.equals(stringaEncoding) || row.equals(stringaEncoding2))
				continue;
			StringBuilder val = new StringBuilder(row);
			String tag = val.substring(val.indexOf("<") + 1, val.indexOf(">"));
			root = tag;
			break;
		}
		return root;
	}
	public static void getChildNodes(String path, String nodo) throws FileNotFoundException {
		File file = new File(path);
		System.out.println("exists ? " + file.exists());
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
//	String row = null;
//		String stringaEncoding = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//	while (br.ready()) {
//		row = row.trim();
//		if (row.equals(stringaEncoding))
//			continue;
//			StringBuilder val = new StringBuilder(row);
		//	String tag = val.substring(val.indexOf("<") + 1, val.indexOf(">"));
//			if(tag.contains("/")) {
//				
//			}
		
		
		
		
	}
	
	public static void getTagName(String path) throws IOException {
		File file = new File(path);
		System.out.println("exists ? " + file.exists());
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String row = null;
		String stringaEncoding = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		while (br.ready()) {
			row = br.readLine();
			row = row.trim();
			if (row.equals(stringaEncoding))
				continue;
			StringBuilder val = new StringBuilder(row);
	
			String tag = val.substring(val.indexOf("<") + 1, val.indexOf(">"));
			if(tag.contains("/")) {
				continue;
			}else {
				System.out.println(tag);
			}
			
			
		}
		
		
		return;
	}
	
	public static void getTextContent(String path) throws IOException {
		
		File file = new File(path);
		System.out.println("exists ? " + file.exists());
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String row = null;
		String stringaEncoding = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		while (br.ready()) {
			row = br.readLine();
			row = row.trim();
			if (row.equals(stringaEncoding))
				continue;
			StringBuilder val = new StringBuilder(row);
			String tag = val.substring(val.indexOf("<") + 1, val.indexOf(">"));
			if (val.indexOf(">") == val.length() - 1) {
				continue;
			} else {
				//if(tag.equals("/utente")) {
					String campo = val.substring(val.indexOf(">") + 1, val.lastIndexOf("<"));
					System.out.println(campo);
//					switch (tag) {
//					case "nome":
//						nome = campo;
//						break;
//					case "cognome":
//						cognome = campo;
//						break;
//					case "eta":
//						eta = Integer.parseInt(campo);						
//						break;
//					}
				//}
			}
		
	
		}
	}
		
		
	}



















