package it.beije.herse.mioDb;
import it.beije.herse.file.Contatto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestioneRubrica {


	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element)
				childElements.add((Element) nodeList.item(n));
		}
		return childElements;
	}

	public static List<Contatto> loadRubricaXml(String path) throws ParserConfigurationException, IOException, SAXException {
		
		File f = new File(path);
		System.out.println("exists ? " + f.exists());

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(f);

		Element rubrica = document.getDocumentElement();
		System.out.println("documentElement:" + rubrica.getTagName());

		NodeList contatti = rubrica.getElementsByTagName("contatto");
		System.out.println("num contatti: " + contatti.getLength());

		List<Contatto> listaContatti = new ArrayList<Contatto>();
		Element el = null;

		for (int i = 0; i < contatti.getLength(); i++) {
			el = (Element) contatti.item(i);
			System.out.println("el name:" + el.getTagName());
			System.out.println("el eta:" + el.getAttribute("eta"));

			List<Element> riferimenti = getChildElements(el);
			System.out.println("size" + riferimenti.size());
			Contatto c = new Contatto();
			for (Element r : riferimenti) {
				switch (r.getTagName()) {
				case "nome":
					c.setNome(r.getTextContent());
					break;
				case "cognome":
					c.setCognome(r.getTextContent());
					break;
				case "email":
					c.setEmail(r.getTextContent());
					break;
				case "telefono":
					c.setTelefono(r.getTextContent());
					break;
				case "note":
					c.setNote(r.getTextContent());
					break;
				default:
					break;
				}

			}
			
			if (c.getNome() == null) {
				c.setNome(" ");
			}
			if (c.getCognome() == null) {
				c.setCognome(" ");
			}
			if (c.getTelefono() == null) {
				c.setTelefono(" ");
			}
			if (c.getEmail() == null) {
				c.setEmail(" ");
			}
			if (c.getNote() == null) {
				c.setNote(" ");
			}
			
			listaContatti.add(c);
			System.out.println();
		}

		return listaContatti;

	}
	
	public static void writeRubricaXML(List<Contatto> contatti, String pathFile) throws ParserConfigurationException, TransformerException{
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		Element documentElement = document.createElement("contatti");
		document.appendChild(documentElement);
		
		for(int i = 0; i < contatti.size(); i++) {
			Contatto c = contatti.get(i);
			
			Element contatto = document.createElement("contatto");
			Element nome = document.createElement("nome");
			nome.setTextContent(c.getNome());
			c.setNome(nome.getTextContent());
			
			Element cognome = document.createElement("cognome");
			cognome.setTextContent(c.getCognome());
			c.setCognome(cognome.getTextContent());
			
			Element telefono = document.createElement("telefono");
			telefono.setTextContent(c.getTelefono());
			c.setTelefono(telefono.getTextContent());
			
			Element email = document.createElement("email");
			email.setTextContent(c.getEmail());
			c.setEmail(email.getTextContent());
			
			Element note = document.createElement("note");
			note.setTextContent(c.getNote());
			c.setNote(note.getTextContent());
			
			contatto.appendChild(nome);
			contatto.appendChild(cognome);
			contatto.appendChild(telefono);
			contatto.appendChild(email);
			contatto.appendChild(note);
			
			documentElement.appendChild(contatto);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(new File(pathFile));

		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);
	}
	
	
	static void addRubricaInFileXml(List<Contatto> contatti, String path) throws ParserConfigurationException, TransformerException, SAXException, IOException {
		
		File f = new File(path);
		if(f.exists()) {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(f);
			
			Element rubrica = document.getDocumentElement();
			Contatto c = null;
			for(int i = 0; i<contatti.size(); i++) {
				Element contatto1 = document.createElement("contatto");
				c = contatti.get(i);
				Element nome = document.createElement("nome");
				nome.setTextContent(c.getNome());
				Element cognome = document.createElement("cognome");
				cognome.setTextContent(c.getCognome());
				Element telefono = document.createElement("telefono");
				telefono.setTextContent(c.getTelefono());
				Element email = document.createElement("email");
				email.setTextContent(c.getEmail());
				Element note = document.createElement("note");
				note.setTextContent(c.getNote());
				
				contatto1.appendChild(nome);
				contatto1.appendChild(cognome);
				contatto1.appendChild(telefono);
				rubrica.appendChild(contatto1);
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			
			StreamResult result = new StreamResult(new File(path));
			// Output to console for testing
			StreamResult syso = new StreamResult(System.out);
			transformer.transform(source, result);
			transformer.transform(source, syso);
			System.out.println("File saved!");	
			
		}
		else
			writeRubricaXML(contatti, path);
	}
	
	
	
	public static List<Contatto> loadRubricaFromCSV(String pathFile, String separatorChar) throws IOException {
		
		FileReader fileReader = new FileReader(pathFile);
		
		BufferedReader reader = new BufferedReader(fileReader);
		String row = reader.readLine();
		String separator1 = new StringBuilder("\'").append(separatorChar).append('\'').toString();	
		String separator2 = new StringBuilder("\"").append(separatorChar).append('\"').toString();
		String separator = null;
		if (row.contains(separator1)) {
			separator = separator1;
		} else if (row.contains(separator2)) {
			separator = separator2;
		} else {
			separator = separatorChar;
		}
		
		String[] title = row.split(separator);
		int last = title.length - 1;
		if (separator.length() > 1) {
			title[0] = title[0].substring(1);
			title[last] = title[last].substring(0, title[last].length()-1);
		}
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		Contatto contatto = null;
		String[] cols = null;
		while (reader.ready()) {
			row = reader.readLine();
			
			cols = row.split(separator);
			if (separator.length() > 1) {
				cols[0] = cols[0].substring(1);
				cols[last] = cols[last].substring(0, cols[last].length()-1);
			}
			
			contatto = new Contatto();
			for (int i = 0; i <= last; i++) {
				System.out.println(title[i] + ": " + cols[i]);
				switch (title[i].toUpperCase()) {
				case "NOME":
					contatto.setNome(cols[i]);		
					break;
				case "COGNOME":
					contatto.setCognome(cols[i]);		
					break;
				case "TELEFONO":
					contatto.setTelefono(cols[i]);		
					break;
				case "EMAIL":
					contatto.setEmail(cols[i]);		
					break;
				case "NOTE":
					contatto.setNote(cols[i]);		
					break;
				}
			}
			
			contatti.add(contatto);
			System.out.println();
		}
		
		reader.close();
		
		return contatti;
	}
	
	
	public static void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) throws IOException {
		
		File destFile = new File(pathFile);
		System.out.println("destFile exists ? " + destFile.exists());

		FileWriter writer = new FileWriter(destFile, destFile.exists());
		if(!destFile.exists()) {
			writer.write("nome");
			writer.write(separator);
			writer.write("cognome");
			writer.write(separator);
			writer.write("telefono");
			writer.write(separator);
			writer.write("email");
			writer.write(separator);
			writer.write("note");
			writer.write('\n');
		}
		
		for (Contatto c : contatti) {
			writer.write(c.getNome());
			writer.write(separator);
			writer.write(c.getCognome());
			writer.write(separator);
			writer.write(c.getTelefono());
			writer.write(separator);
			writer.write(c.getEmail());
			writer.write(separator);
			writer.write(c.getNote());
			writer.write('\n');
		}
		
		writer.flush();
		writer.close();
	}
	
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

		List<Contatto> contatti = loadRubricaXml("/tmp/rubrica.xml");
		System.out.println(contatti);
		writeRubricaXML(contatti, "private/tmp/new_rubrica.xml");
		loadRubricaFromCSV("private/tmp/rubrica.txt", ";");
		addRubricaInFileXml(contatti, "private/tmp/rubrica.txt");
		writeRubricaCSV(contatti, "private/tmp/new_rubrica.txt", ";");
	}
	
}
