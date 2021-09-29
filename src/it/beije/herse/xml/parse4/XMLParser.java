package it.beije.herse.xml.parse4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
public class XMLParser {
	public boolean exist(File file) {
		return file.exists();
	}

	public List<String> isXML(File file) throws IOException {
		if (exist(file)) {
			String xmlVersion = "\"1.0\"";
			String xmlEncoding = "\"UTF-8\"";
			String xmlExtension = "<?xml version=" + xmlVersion + " encoding=" + xmlEncoding + "?>";
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String row = reader.readLine();
			List<String> rows = getRows(row);

			if (rows.get(0).equals(xmlExtension)) {
				for (int i = 1; i < rows.size(); i++) {
					System.out.println((rows.get(i)));
				}
				while (row != null) {
					row = reader.readLine();
					if (row != null) {
						rows = getRows(row);
					}
				}
				return rows;
			}
			return null;
		} else {
			return null;
		}
	}

	private List<String> getRows(String row) {
		List<String> rows = new ArrayList<String>();
		int start;

		for (int i = 0; i < row.length(); i++) {
			if (row.charAt(i) == '<') {
				start = i;
				while (row.charAt(i) != '>') {
					i++;
				}
				rows.add(row.substring(start, i + 1));
			} else {
				start = i;
				while (row.charAt(i) != '<') {
					i++;
				}
				rows.add(row.substring(start, i).trim());
				i--;
			}
		}
		return rows;
	}

	private boolean checkSintax(String str) {
		int count = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '<' || str.charAt(i) == '>') {
				count++;
			}
		}
		if (str.charAt(0) == '<' && str.charAt(str.length() - 1) == '>' && (count == 2 || count == 0)) {
			return true;
		}
		return false;
	}

	public static OggettoXML parse(File file) throws IOException {
		XMLParser xmlParser = new XMLParser();

		System.out.println(xmlParser.exist(file));
		System.out.println(xmlParser.isXML(file)!=null);
		
		if (xmlParser.exist(file) && xmlParser.isXML(file)!=null) {
			OggettoXML oggettoXML = new OggettoXML();
			List<String> oList = new ArrayList<String>();
			oList=xmlParser.isXML(file);
			for (String string : oList) {
				System.out.println(string);
			}
			return new OggettoXML();
		} else System.out.println("KO");
		return null;
	}
}
