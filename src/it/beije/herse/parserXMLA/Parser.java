package it.beije.herse.parserXMLA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

		Document doc = new Document();

		try {

			File f = new File(file);
			FileReader fileReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fileReader);
			String rows = null;		
			Element e = new Element();
			StringBuilder content = new StringBuilder();

			while(reader.ready())
				content.append(reader.readLine()).append('\n');

			reader.close();

			rows = content.toString();

			rows = getHead(doc, rows);

			if(rows.startsWith("\n") || rows.startsWith("\t"))
				rows = rows.trim();

			try {
				e = getElement(doc,rows);
			} catch (StringIndexOutOfBoundsException ex) {
				e = null;
				throw new StringIndexOutOfBoundsException();
			}
			//Alla fine creare eccezioni che controllano che un tag sia chiuso o meno, se non è chiuso allora lancio eccezione.
			System.out.println("row dopo: " + rows);

			e.setTagName("contatto");
			setChildNodes(e, rows);

		} catch (FileNotFoundException ex) {
			doc = null;
			throw new FileNotFoundException();
		}

		return doc;

	}

	private static String getHead(Document doc, String rows) {

		try {

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

		} catch(StringIndexOutOfBoundsException e) {
			throw new StringIndexOutOfBoundsException();
		}

		return rows;

	}

	private static Element getElement(Document doc, String rows) {

		Element e = new Element();

		try {

			getAttributes(e, rows.substring(rows.indexOf("<")+1, rows.indexOf(">")));

			if (rows.contains("</" + e.getTagName() + ">"))
				doc.setRootElement(e);
			else
				doc.setRootElement(null);
			
			System.err.println(e.getAttributes().toString());
			
		}catch(StringIndexOutOfBoundsException ex) {
			throw new StringIndexOutOfBoundsException();
		}

		return e;
	}

	private static void getAttributes(Element e, String inner) {

		inner = inner.trim();
		if(inner.contains(" ")) {
			e.setTagName(inner.substring(0, inner.indexOf(" ")));
			inner = inner.substring(inner.indexOf(" ")+1);
			
			StringBuilder s = new StringBuilder(inner);

			while(inner.contains("=")) {

				if(inner.charAt(inner.indexOf("=")-1) == ' ' || inner.charAt(inner.indexOf("=")+1) == ' ') {

					if(inner.charAt(inner.indexOf("=")-1) == ' ') {
						s.append(inner.substring(0, inner.indexOf(" "))).append(inner.substring(inner.indexOf(" ")+1));
					}
					if(inner.charAt(inner.indexOf("=")+1) == ' ') {
						s.append(inner.substring(0, inner.indexOf(" "))).append(inner.substring(inner.indexOf(" ")+1));
					}

					inner = s.toString();

				} else {
					String[] attribute = (inner.substring(0, inner.indexOf(" "))).split("=");
					inner = inner.substring(inner.indexOf(" ")+1);
					if(attribute[1].contains("\""))
						e.addAttribute(attribute[0], attribute[1].replace("\"", ""));
					else
						e.addAttribute(attribute[0], attribute[1].replace("'", ""));
					
				}

			}

		} else if(Pattern.matches("[a-zA-Z]*", inner)) {
			e.setTagName(inner);
			inner = "";
		}else {
			throw new RuntimeException();
		}

		if(!inner.equals(""))
			throw new RuntimeException();

	}

	private static void setChildNodes(Element mom, String row) {
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

		try {
			parse("\\Users\\savin\\Downloads\\test_parser5.xml");
		}catch (RuntimeException e) {
			System.err.println("Sintassi non corretta");
		} catch (IOException e) {
			System.err.println("File non trovato");
		}
	}

}
