package it.beije.herse.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

	public static void readXML() throws ParserConfigurationException, IOException, SAXException {
		File f = new File("/temp/rubrica.xml");
		System.out.println("exists ? " + f.exists());
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse(f);
		
		Element rubrica = document.getDocumentElement();
		System.out.println("documentElement:" + rubrica.getTagName());
		
		NodeList contatti = rubrica.getElementsByTagName("contatto");
		System.out.println("num contatti: " + contatti.getLength());
		
		Element el = null;
		for (int i = 0; i < contatti.getLength(); i++) {
			el = (Element)contatti.item(i);
			System.out.println("el name:" + el.getTagName());
			System.out.println("el eta:" + el.getAttribute("eta"));
			
			List<Element> riferimenti = getChildElements(el);
			for (Element r : riferimenti) {
				System.out.println(r.getTagName() + " : " + r.getTextContent());
			}
			
			System.out.println();
		}
		
	}
	
	public static void writeXML() throws ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.newDocument();
		
		Element documentElement = document.createElement("contatti");
		document.appendChild(documentElement);
		
		Element contatto1 = document.createElement("contatto");
		Element nome1 = document.createElement("nome");
		nome1.setTextContent("Hugo");
		Element cognome1 = document.createElement("cognome");
		cognome1.setTextContent("Boss");
		Element telefono1 = document.createElement("telefono");
		telefono1.setTextContent("3331234567");
		
		contatto1.appendChild(nome1);
		contatto1.appendChild(cognome1);
		contatto1.appendChild(telefono1);
		
		//... altri contatti
		
		documentElement.appendChild(contatto1);
	}
	
	public static List<Contatto> readXML(File f) throws ParserConfigurationException, SAXException, IOException{
		List<Contatto> rubrica = new ArrayList<>();
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse(f);
		
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
		
		return rubrica;
	}
	
	public static void writeXML(List<Contatto> rubrica, File f) throws ParserConfigurationException, TransformerException {
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
		
		StreamResult result = new StreamResult(f);

//		// Output to console for testing
//		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
//		transformer.transform(source, syso);

		System.out.println("File saved!");		
	}
	
	public static void main(String args[]) throws TransformerConfigurationException, ParserConfigurationException,
	TransformerException, IOException, SAXException {
		File readFile = new File("/temp/file/RubricaXML/readRubrica.xml");
		for(Contatto c : RubricaXML.readXML(readFile)) {
			System.out.println(c.getNome());
			System.out.println(c.getCognome());
			System.out.println(c.getTelefono());
			System.out.println(c.getEmail());
			System.out.println(c.getNote());
			System.out.println();
		}
		
		File writeFile = new File("/temp/file/RubricaXML/writeRubrica.xml");
		List<Contatto> rubrica = new ArrayList<>();
		rubrica.add(new Contatto("Francesco", "Gigante", "333444555", "fragiant@gmail.com", "Mio fratello"));
		rubrica.add(new Contatto("Hugo", "Boss", "444", "boss@gmail.com", ""));
		RubricaXML.writeXML(rubrica, writeFile);
	}

}
