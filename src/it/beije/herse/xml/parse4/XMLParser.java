package it.beije.herse.xml.parse4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.w3c.dom.Element;

public class XMLParser {
	public boolean exist(File file) {
		return file.exists();
	}

	public boolean isXML(File file) throws IOException {
		if (exist(file)) {
			String xmlVersion = "\"1.0\"";
			String xmlEncoding = "\"UTF-8\"";
			String xmlExtension = "<?xml version=" + xmlVersion + " encoding=" + xmlEncoding + "?>";
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String row = reader.readLine();
			row.split(">");

			if (row.equals(xmlExtension)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
//	public Element getElementByName() {
//		
//	}

	}
}
