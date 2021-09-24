package it.beije.herse.xml.parse4;

import java.io.File;
import java.io.IOException;

public class TestXMLParser {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		XMLParser parser = new XMLParser();
		File file = new File("C:\\Users\\nicol\\Desktop\\Beije\\rubrica.xml");
		
		System.out.println(parser.exist(file));
		System.out.println(parser.isXML(file));
	}

}
