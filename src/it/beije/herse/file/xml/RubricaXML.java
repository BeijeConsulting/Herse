package it.beije.herse.file.xml;

import java.io.File;
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

import it.beije.herse.file.Contatto;

public class RubricaXML {

	private static Contatto c;

	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element) childElements.add((Element)nodeList.item(n));
		}
		
		return childElements;
	}

	public static List<Contatto> readRubricaXML(String pathFile) {
		List<Contatto> rubrica = new ArrayList<>();
		
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(new File(pathFile));
			
			NodeList contatti = document.getDocumentElement().getElementsByTagName("contatto");
			
			Element el = null;
			for (int i = 0; i < contatti.getLength(); i++) {
				el = (Element)contatti.item(i);
				List<Element> riferimenti = getChildElements(el);
				c = new Contatto();
				for (Element r : riferimenti) {
					switch(r.getTagName().toUpperCase()) {
					case "NOME":
						c.setNome(r.getTextContent());
						break;
					case "COGNOME":
						c.setCognome(r.getTextContent());	
						break;
					case "TELEFONO":
						c.setTelefono(r.getTextContent());		
						break;
					case "EMAIL":
						c.setEmail(r.getTextContent());	
						break;
					case "NOTE":
						c.setNote(r.getTextContent());	
						break;
					}
				}
				rubrica.add(c);
			}
		}
		catch(ParserConfigurationException e){
			e.printStackTrace();
		}
		catch(SAXException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return rubrica;
	}
	
	public static void writeRubricaXML(List<Contatto> rubrica, String pathFile) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.newDocument();
			
			Element documentElement = document.createElement("rubrica");
			document.appendChild(documentElement);
			
			for(Contatto c : rubrica) {
				Element contatto = document.createElement("contatto");
				
				Element nome = document.createElement("nome");
				nome.setTextContent(c.getNome());
				contatto.appendChild(nome);
				
				Element cognome = document.createElement("cognome");
				cognome.setTextContent(c.getCognome());
				contatto.appendChild(cognome);
				
				Element telefono = document.createElement("telefono");
				telefono.setTextContent(c.getTelefono());
				contatto.appendChild(telefono);
				
				Element email = document.createElement("email");
				email.setTextContent(c.getEmail());
				contatto.appendChild(email);
				
				Element note = document.createElement("note");
				note.setTextContent(c.getNote());
				contatto.appendChild(note);
				
				documentElement.appendChild(contatto);
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			
			StreamResult result = new StreamResult(new File(pathFile));
	
	//		// Output to console for testing
	//		StreamResult syso = new StreamResult(System.out);
	
			transformer.transform(source, result);
	//		transformer.transform(source, syso);
	
			//System.out.println("File saved!");
		}
		catch(ParserConfigurationException e){
			e.printStackTrace();
		}
		catch(TransformerException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		for(Contatto c : RubricaXML.readRubricaXML("/temp/file/RubricaXML/readRubrica.xml")) {
			System.out.println(c.getNome());
			System.out.println(c.getCognome());
			System.out.println(c.getTelefono());
			System.out.println(c.getEmail());
			System.out.println(c.getNote());
			System.out.println();
		}
		
		List<Contatto> rubrica = new ArrayList<>();
		rubrica.add(new Contatto("Francesco", "Gialli", "333444555", "francesco.gialli@gmail.com", "Cugino"));
		rubrica.add(new Contatto("Carolina", "Marrone", "321654987", "carolina.marrone@gmail.com", ""));
		RubricaXML.writeRubricaXML(rubrica, "/temp/file/RubricaXML/writeRubrica.xml");
		
		rubrica.add(new Contatto("Raffaele", "Viola", "333123456", "raffa.viola@gmail.com", ""));
		rubrica.add(new Contatto("Elisa", "Indaco", "398765421", "", "Sorella"));
		RubricaXML.writeRubricaXML(rubrica, "/temp/file/RubricaXML/writeRubrica.xml");
	}

}
