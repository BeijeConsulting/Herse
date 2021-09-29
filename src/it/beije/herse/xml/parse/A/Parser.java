package it.beije.herse.xml.parse.A;

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
	
	public static Document parse(String file) throws IOException {
		File f = new File(file);
		
		System.out.println("exists ? " + f.exists());
		
		FileReader fileReader = new FileReader(f);
		BufferedReader reader = new BufferedReader(fileReader);
		
		String rows = null;
		
		StringBuilder content = new StringBuilder();
		
		while(reader.ready()) {
			content.append(reader.readLine());
			content.append('\n');
		}
		
		reader.close();
		
		rows = content.toString();
		
		Document doc = new Document();
		
		rows.trim();
		if(rows.startsWith("<?")) {
			String intestazione = rows.substring(0, rows.indexOf("?>") + 2);
			
			String version = intestazione.substring(rows.indexOf("version"));
			int start = version.indexOf("\"");
			int end = version.indexOf("\"", start+1);
			doc.setXmlVersion(Double.parseDouble(version.substring(start+1, end)));
			
			String encoding = version.substring(end+1).trim();
			System.out.println(encoding);
			start = encoding.indexOf("\"");
			end = encoding.indexOf("\"", start+1);
			doc.setEncoding(encoding.substring(start+1, end));
			
			rows = rows.substring(rows.indexOf("?>")+2);
		}
		
		
		
		Node node = new Node();
		
		if(rows.startsWith("\n") || rows.startsWith("\t")) {
			node.setElement(false);
			rows = rows.trim();
		}
		
		
		Element e = new Element();
			
		String root = rows.substring(rows.indexOf("<")+1, rows.indexOf(">"));
		//System.out.println(root);
		if (rows.contains("</"+ root + ">")) {
			e.setTagName(root);
			doc.setRootElement(e);
		} else {
			doc.setRootElement(null);
		}
		
		//setChildNodes(e, rows);
		//Alla fine creare eccezioni che controllano che un tag sia chiuso o meno, se non � chiuso allora lancio eccezione.
		System.out.println("row dopo: " + rows);
		
		e.setTagName("contatto");
		setChildNodes(e, rows);
		return doc;
	}
	
	public static void setChildNodes(Element mom, String row) {
		List<Element> list = new ArrayList<Element>();
		
		
		if(row.contains("<" + mom.getTagName() + ">")) {
			String tmp = "<" + mom.getTagName() + ">";
			System.out.println("tmp1: "+ tmp);
			row = row.substring(row.indexOf(tmp));
			row = row.substring(tmp.length()).trim();
			System.out.println("1: " +row);
		}
		
		if(row.contains("</" + mom.getTagName() + ">")) {
			String tmp = "</" + mom.getTagName() + ">";
			row = row.substring(0, row.indexOf(tmp)).trim();
			System.out.println("2: " + row);
		}
		
		for(int i = 0; i<row.length(); i++) {
			Element e = new Element();
			String element = row.substring(row.indexOf("<")+1, row.indexOf(">"));
			System.out.println(element);
			e.setTagName(element);
			list.add(e); 
			String tmp = row.substring(0, row.indexOf("</" + e.getTagName()));
			System.out.println("tmp: " + tmp);
			String controllo = tmp.substring(tmp.indexOf(">")+1).trim();
			System.out.println("controllo: " + controllo);
			if(controllo.charAt(0) != '<') {
				e.setTextValue(controllo);
			}
			System.out.println("text " + e.getTextValue());
			row = row.substring(tmp.length());
			row = row.substring(row.indexOf(">")+1).trim();
			System.out.println("row: " + row);
		}
		
		for(Element t: list) {
			System.out.println(t.getTagName());
		}

	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Document d = new Document();
		d = parse("/temp/parser.xml");
	}

}
