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

	public Parser() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Throwable {
		System.out.println(xmlParse("C:\\Users\\nicol\\Desktop\\Beije\\test_parser1.xml"));
	}

	public static Document xmlParse(String path) throws Throwable {
		String pathFile = "C:\\Users\\nicol\\Desktop\\Beije\\test_parser1.xml";
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
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);

		while (reader.ready()) {
			char carattere = (char) reader.read();
			sb.append(carattere);
		}
		
		if (sb.indexOf("<?xml") != -1) {
			int fineInt = sb.indexOf("?>") + 2;
			intestazione = sb.substring(sb.indexOf("<?xml"), fineInt);
			sb.delete(sb.indexOf(intestazione),intestazione.length());
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
		for(int i = 0; i < sb.length(); i++) {
			if(sb.charAt(i)!='<') {
				Node node = new Node();
				node.setElement(false);
			}else {
				//crei un element
			}
		}
		
//		System.out.println(intestazione);
//		System.out.println();
	System.out.println(sb);
		return null;

	}
}
