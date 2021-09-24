package it.beije.herse.xml.parse1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/*
   	X getRootElement() //torna l'elemento root
	getChildNodes() //torna tutti i nodi "figli" interni all'elemento su cui viene eseguito
	X getChildElements() //torna i soli elementi figli dell'elemento su cui viene eseguito
	getElementsByTagName(String tagName) //torna TUTTI gli elementi con quello specifico nome
	getTagName() //torna il nome del tag
	getTextContent() //torna il contenuto del tag
	getAttributes() //torna l'elenco degli attributi dell'elemento
	getAttribute(String attribute) //torna il valore dell'attributo specificato (modificato) 
 */
public class Parser {
	
	private static Node root = new Node();
	private static String intestazione;
	
	public static String setRoot(String s) {
		if(s.startsWith("<")) {
			int fineNodo = s.indexOf(">");
			String nomeNodo = s.substring(1, fineNodo);
					
			if(s.contains("=")) nomeNodo = nomeNodo.substring(0, nomeNodo.indexOf(" "));
			if(s.endsWith("</"+nomeNodo+">")) {
				root.setTagName(nomeNodo);
			}
			
		}
		return s.substring(s.indexOf(">")+1, s.indexOf("</"+root.getTagName()+">"));
	}
	
	public static void addNode(String s, Node n) {
		Node newNode = new Node();
		if(s.startsWith("<")){
			int fineNodo = s.indexOf(">");
			String nomeNodo = s.substring(1, fineNodo);
			//System.out.println(nomeNodo);
			
			//if(s.contains("=")) nomeNodo = nomeNodo.substring(0, nomeNodo.indexOf(" "));
			if(s.indexOf("</"+nomeNodo+">")!=-1) {
				newNode.setTagName(nomeNodo);
			}
			
			n.addChild(newNode);
			//System.out.println(s);
			int start = s.indexOf("<",s.indexOf("</"+newNode.getTagName()+">")+1);
			if(start != -1) {
				String pS = s.substring(start);
//				System.out.println(pS);
				addNode(pS, n);	
			}
			
		}
	}
	
	public static Node getRootElement() {
		return root;
	}
	
	public static List<Node> getChildElements(Node n) {
		return n.getChild();
	}

	public static void parse(String file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		
		StringBuilder sb = new StringBuilder("");
		String s;
		
		while(reader.ready()) {
			sb.append(reader.readLine());
		}
		s  = sb.toString().replaceAll("\\t", "");
		
		//Leggere e rimuovere intestazione
		if(s.startsWith("<?xml")) {
			int fineInt = s.indexOf("?>") + 2;
			intestazione = s.substring(0, fineInt);
			s = s.substring(fineInt);
		}
		
		s = setRoot(s);
		//System.out.println(s);
		
		addNode(s, root);
		
			
		getRootElement();
		System.out.println(getRootElement().getTagName());
		
		for(Node n : getChildElements(root)) {
			System.out.println(n.getTagName());			
		}
	}
	
	public static void main(String[] args) throws IOException {
		String path = "/temp/test/test_parser1.xml";
		parse(path);
	}

}
