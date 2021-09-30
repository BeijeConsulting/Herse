package it.beije.herse.xml.parse.B;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class Parser {

	static List<Node> documento = new ArrayList<>();
	static int countEl = -1;
	static boolean root = false;

	public Parser() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Throwable {
		System.out.println(xmlParse("/temp/xmlParse/test_parser1.xml"));
	}

	public static Document xmlParse(String path) throws Throwable {
		String pathFile = "/temp/xmlParse/test_parser1.xml";
		File file = new File(pathFile);
		if (file.exists()) {
			return parse(file);
		}

		System.out.println("Error");
		return null;
	}

	public static Document parse(File file) throws Throwable {
		// root corretta
		// tag con <,>
		// tag con <tag/>
		// corretta chiusura del tag </>
		Document document = new Document();
		StringBuilder sb = new StringBuilder();
		String intestazione = null;
		List<String> instance = new ArrayList<>();
		List<String> textVal = new ArrayList<>();
		
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);

		while (reader.ready()) {
			char carattere = (char) reader.read();
			sb.append(carattere);
		}

		if (sb.indexOf("<?xml") != -1) {
			int fineInt = sb.indexOf("?>") + 2;
			intestazione = sb.substring(sb.indexOf("<?xml"), fineInt);
			sb.delete(sb.indexOf(intestazione),sb.indexOf("<", intestazione.length()));
			if (intestazione.contains("version")) {
				String numVersion = intestazione.substring(
						intestazione.indexOf("\"", intestazione.indexOf("version")) + 1,
						intestazione.indexOf("\"", intestazione.indexOf("version=\"") + 9));
				double version = Double.parseDouble(numVersion);
				System.out.println(version);
				document.setXmlVersion(version);
			}
			if (intestazione.contains("encoding")) {
				String encoding = intestazione.substring(
						intestazione.indexOf("\"", intestazione.indexOf("encoding")) + 1,
						intestazione.indexOf("\"", intestazione.indexOf("encoding=\"") + 11));
				System.out.println(encoding);
				document.setEncoding(encoding);;
			}
		}
		for(int i = 0; i < sb.length();i++) {
			// IF NODE
			if(sb.charAt(i)!='<') {
				Node node = new Node();
				node.setElement(false);
				String s = "";
//				i++;
				while(sb.charAt(i)!='<') {
					
					s+=sb.charAt(i);
					i++;
				}
				
				if(s.trim().equals("")) {
					node.setTextContent(s);
					documento.add(node);
				}
				else {
					textVal.add(s);
				}
				//s = s.substring(1);
//				node.setTextContent(s);
				--i;
//				System.out.println(s);
				

			}
			// IF ELEMENT
			else {
				i++;
				if(!root) {
					//System.out.println("ROOT");
					root=true;  
//					countEl++;
					String s = "";
					Element el = new Element();
					
					while(sb.charAt(i)!='>') {
						
						s+=sb.charAt(i);
						i++;
					}
                    
					if(s.endsWith("/>")) {
					   countEl--;
					    s=s.substring(0,s.length()-3);
					    el.setTagName(s);
					    el.setElement(true);
					    document.setRootElement(el);
					    documento.add(el);
					    
					    return document;
					    
					} else if(s.startsWith("/") && s.equals(instance.get(countEl))) {
						instance.remove(countEl);
						
						countEl--;
						s=s.substring(1);
					    el.setTagName(s);
					    el.setElement(true);
					    document.setRootElement(el);
					    documento.add(el);
					} else {
						countEl++;
						instance.add(s);
					}
					
					--i;	
			}
				else {
					//System.out.println("EL");
//					countEl++;
					String s = "";
					Element el = new Element();
					
					while(sb.charAt(i)!='>') {
						
						s+=sb.charAt(i);
						i++;
					}
//					countEl++;
//					System.out.println(s);
					
					if(s.endsWith("/>")) {
					   countEl--;
					    s=s.substring(0,s.length()-3);
					    el.setTagName(s);
					    el.setElement(true);
					    documento.add(el);
					} else if(s.startsWith("/") && s.equals("/"+instance.get(countEl))) {
						//System.out.println("TAG CHIUSO");
						instance.remove(countEl);
						
						countEl--;
						s=s.substring(1);
					    el.setTagName(s);
					    el.setElement(true);
					    String content = sb.substring(sb.indexOf("<"+el.getTagName()+">")+el.getTagName().length()+2, 
					    		sb.indexOf("</"+el.getTagName()+">"));
					    if(!content.contains("<")) {
					    	el.setTextValue(textVal.get(countEl));
					    	textVal.remove(countEl);
					    }
					    System.out.println(s);
					    documento.add(el);
					} else {
						instance.add(s);
						countEl++;
//						System.out.println(instance.get(countEl));
					}
					
				}
			
		}
	}

	for(Node n : documento) System.out.println(n);
//	System.out.println(countEl);
	return null;

	}
}
