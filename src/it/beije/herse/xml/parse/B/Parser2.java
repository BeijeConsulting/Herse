package it.beije.herse.xml.parse.B;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthScrollPaneUI;

/*
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<contatti>
<contatto><nome>Pippo</nome><cognome>Pluto</cognome><telefono>3331234567</telefono><email>pippo@pluto.net</email></contatto>
	<contatto>
		<nome>Paolino</nome>
		<cognome>Paperino</cognome>
		<telefono>00423803243423</telefono>
	</contatto>
</contatti>
 */
public class Parser2 {

	static List<Node> listNodes = new ArrayList<>();
	static boolean root = false;
	static List<String> instance = new ArrayList<>();
	static List<String> textVal = new ArrayList<>();

	public Parser2() {}

	public static List<Element> figli (Document document, Node n) {
		int j = 0;
		String tag = n.getTextContent();
		List<Element> child = new ArrayList<Element>();
		for(int i =0; i<instance.size(); i++) {
	//		System.out.println("sono nel for");
			if(instance.get(i).equals(tag)) {
		//		System.out.println("sono nell'if");
				for(int k = i+1; k<instance.size(); k++) {
		//			System.out.println("sono nel secondo ciclo");
					if(instance.get(k).equals("/" + tag)){
						break;
					}
					Element e = new Element();
					String s = instance.get(k);
					if(!s.startsWith("/")) {
						e.setTagName(s);
						if(instance.get(k+1).equals("/" + s)) {
							e.setTextValue(textVal.get(j));
							j++;
						}else child.add(e);
					}
				}
			}
		}
		System.out.println("fine metodo");
		return child;
	}

	public static Document xmlParse(String path) throws Throwable {		//completata
		//		String pathFile = "/temp/xmlParse/test_parser1.xml";
		File file = new File(path);
		if (file.exists()) {
			return parse(file);
		}

		System.out.println("Error");
		return null;
	}

	public static Document parse(File file) throws Throwable {
		Document document = new Document();
		StringBuilder sb = new StringBuilder();
		String intestazione = null;

		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);

		while (reader.ready()) {
			char carattere = (char) reader.read();			
			sb.append(carattere);
		}
		//		System.out.println(sb);

		if (sb.indexOf("<?xml") != -1) {
			int fineInt = sb.indexOf("?>") + 2;
			intestazione = sb.substring(sb.indexOf("<?xml"), fineInt);
			//			System.out.println("intestazione " + intestazione);
			sb.delete(sb.indexOf(intestazione),sb.indexOf("<", intestazione.length()));
			//			System.out.println("sb dopo delete" + sb);
			if (intestazione.contains("version")) {
				String numVersion = intestazione.substring(
						intestazione.indexOf("\"", intestazione.indexOf("version")) + 1,
						intestazione.indexOf("\"", intestazione.indexOf("version=\"") + 9));
				double version = Double.parseDouble(numVersion);
				//				System.out.println(version);
				document.setXmlVersion(version);
			}
			if (intestazione.contains("encoding")) {
				String encoding = intestazione.substring(
						intestazione.indexOf("\"", intestazione.indexOf("encoding")) + 1,
						intestazione.indexOf("\"", intestazione.indexOf("encoding=\"") + 11));
				//				System.out.println(encoding);
				document.setEncoding(encoding);;
			}
		}
		for(int i = 0; i < sb.length()-1;i++) {
			// IF NODE
			if(sb.charAt(i) == '<' && root==false) {
				int j = i + 1;
				String s = "";
				while(sb.charAt(j)!='>') {
					s+=sb.charAt(j);
					j++;
				}
				System.out.println("root " + s);
				root = true;
				Element e = new Element();
				e.setTagName(s);
				document.setRootElement(e);
				Node n = new Node();
				n.setElement(true);
				n.setTextContent(s);
				listNodes.add(n);
				instance.add(s);
				i += s.length()-1;
			}
			else if(sb.charAt(i) == '<' && root==true) {
				int j = i + 1;
				String s = "";
				while(sb.charAt(j)!='>') {
					s+=sb.charAt(j);
					j++;
				}
				instance.add(s);
				Node n = new Node();
				n.setElement(true);
				n.setTextContent(s);
				listNodes.add(n);
				i += s.length()-1;
			}
			else if(Character.toString(sb.charAt(i)).equals("\n") || Character.toString(sb.charAt(i)).equals("\t")) {
				String s = "";
				s+=sb.charAt(i);
				Node n = new Node();
				n.setElement(false);
				n.setTextContent(s);
				listNodes.add(n);				
			}
			else if(sb.charAt(i) == '>' && root == true && sb.charAt(i) != ' ') {
				if(sb.charAt(i+1)!='<') {
					int j = i + 1;
					String s = "";
					while(sb.charAt(j)!='<') {
						s+=sb.charAt(j);
						j++;
					}
					i += s.length()-1;
					s = s.trim();
					if(s.length() !=0)
						textVal.add(s);	
				}
			}


		}

		for(int i = 0; i<instance.size(); i++) {

		}

//
//		for(String text: textVal )
//			System.out.println("textVal " + text);
//		System.out.println("\n");
//		for(String text: instance )
//			System.out.println("instance " + text);
//		for(Node n : listNodes) System.out.print(n.getTextContent());
//

		return document;

	}
	static Element settaFigli(Document document, Element root) {
		return settaFigliRicorsiva(document, root, 0);
	}
	//TO-DO DA COMPLETARE
	
	static Element settaFigliRicorsiva(Document document, Element root, int i) {
		List <Element> child = figli(document, root);
		root.setChildNodes(child);
		if(child.size()==0)
			return root;

			return settaFigli(document, root.getChildNodes().get(i++));

	}

	public static void main(String[] args) throws Throwable {
		Document document = xmlParse("/temp/test_parser1.xml");
		List<Element> child = figli(document, document.getRootElement());
		for(Element e : child) System.out.println(e.getTagName());
		
	}
}
