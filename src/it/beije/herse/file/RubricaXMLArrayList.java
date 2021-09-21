package it.beije.herse.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.dsig.TransformException;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RubricaXMLArrayList {
	
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
	
	public static List<Contatto> readXMLToList() throws ParserConfigurationException, IOException, SAXException {
		List<Contatto> rubricaList = new ArrayList<Contatto>();
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
			Contatto c = new Contatto();
			List<Element> riferimenti = getChildElements(el);
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
			if(c.getNome()==null) c.setNome("");
			if(c.getCognome()==null) c.setCognome("");
			if(c.getEmail()==null) c.setEmail("");
			if(c.getNote()==null) c.setNote("");
			if(c.getTelefono()==null) c.setTelefono("");
			
			rubricaList.add(c);

		}
		return rubricaList;
	}

	
	public static void  writeXMLfromList(List<Contatto> rubricaLista, String pathname) throws ParserConfigurationException, TransformerException{
		Contatto c = null;
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element documentElement = document.createElement("contatti");
		document.appendChild(documentElement);
		
		
		for(int i = 0; i<rubricaLista.size(); i++) {
			Element contatto1 = document.createElement("contatto");
			c = rubricaLista.get(i);
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
			documentElement.appendChild(contatto1);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File(pathname));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("File saved!");	
		
	}
	
	
	static void writeXMLifExist(List<Contatto> contatti, String path) throws ParserConfigurationException, TransformerException, SAXException, IOException {
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
			writeXMLfromList(contatti, path);
	}
	
	public static void main(String args[]) throws TransformerConfigurationException, ParserConfigurationException,
	TransformerException, IOException, SAXException {
//		FileWriter writer = new FileWriter("/temp/prova.xml");
//		writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		writer.append("<rubrica><nome>Mario</nome></rubrica>");
//		writer.flush();
//		writer.close();

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.newDocument();
		
		Element documentElement = document.createElement("contatti");
		document.appendChild(documentElement);
		
		Element contatto1 = document.createElement("contatto");
		Element nome1 = document.createElement("nome");
		nome1.setTextContent("Pippo");
		Element cognome1 = document.createElement("cognome");
		cognome1.setTextContent("rossi");
		Element telefono1 = document.createElement("telefono");
		telefono1.setTextContent("3331234567");
		
		contatto1.appendChild(nome1);
		contatto1.appendChild(cognome1);
		contatto1.appendChild(telefono1);
		
		//... altri contatti
		
		documentElement.appendChild(contatto1);
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File("/temp/new_rubrica.xml"));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("File saved!");	
	//	readXML();
		List<Contatto> rubrica = readXMLToList();
		System.out.println(rubrica.get(1).getNote());
//		writeXMLfromList(rubrica, "/temp/list_new_rubrica.xml");
//		System.out.println(rubrica.get(0).getNome());
		
		writeXMLifExist(rubrica, "/temp/list_new_rubrica.xml" );
	}

}
