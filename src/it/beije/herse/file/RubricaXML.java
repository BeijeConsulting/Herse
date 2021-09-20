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

public class RubricaXML {


	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element)
				childElements.add((Element) nodeList.item(n));
		}
		return childElements;
	}

	public static void readXML() throws ParserConfigurationException, IOException, SAXException {
		File f = new File("C://Users//Account//Desktop/esercizi/file/rubrica.xml");
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
			System.out.println("size"+riferimenti.size());
			
			Contatto c = new Contatto();
			c.setNome(riferimenti.get(0).getTextContent());
			c.setCognome(riferimenti.get(1).getTextContent());
			c.setTelefono(riferimenti.get(2).getTextContent());
			c.setEmail(riferimenti.get(3).getTextContent());
			c.setNote(riferimenti.get(4).getTextContent());
			listaContatti.add(c);
			
			for (Element r : riferimenti) {
				System.out.println(r.getTagName() + " : " + r.getTextContent());
			}
			System.out.println();
		}
		System.out.println(listaContatti.size());
	}

	public static void main(String args[]) throws ParserConfigurationException, TransformerException {
		//readXML();
		writeXml();
		
	}
	
	public static void writeXml() throws ParserConfigurationException, TransformerException {
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
		
		List<Contatto> listaContatti = new ArrayList<Contatto>();
		
		Contatto c1 = new Contatto();
		Element contatto1 = document.createElement("contatto");
		contatto1.setAttribute("eta", "50");
		Element nome1 = document.createElement("nome");
		nome1.setTextContent("Mario");
		c1.setNome(nome1.getTextContent());
		Element cognome1 = document.createElement("cognome");
		cognome1.setTextContent("Rossi");
		c1.setCognome(cognome1.getTextContent());
		Element telefono1 = document.createElement("telefono");
		telefono1.setTextContent("3331234567");
		c1.setTelefono(telefono1.getTextContent());
		Element email1 = document.createElement("email");
		email1.setTextContent("mr@gmail.com");
		c1.setEmail(email1.getTextContent());
		Element note1 = document.createElement("note");
		note1.setTextContent("persona da contattare");
		c1.setNote(note1.getTextContent());
		
		
		listaContatti.add(c1);
		
		Contatto c2 = new Contatto();
		Element contatto2 = document.createElement("contatto");
		contatto2.setAttribute("eta", "39");
		Element nome2 = document.createElement("nome");
		nome2.setTextContent("Francesco");
		c2.setNome(nome2.getTextContent());
		Element cognome2 = document.createElement("cognome");
		cognome2.setTextContent("Bianchi");
		c2.setCognome(cognome2.getTextContent());
		Element telefono2 = document.createElement("telefono");
		telefono2.setTextContent("33321987");
		c2.setTelefono(telefono2.getTextContent());
		Element email2 = document.createElement("email");
		email2.setTextContent("fb@gmail.com");
		c2.setEmail(email2.getTextContent());
		Element note2 = document.createElement("note");
		note2.setTextContent("email sbagliata");
		c2.setNote(note2.getTextContent());	
		
		listaContatti.add(c2);
		
		contatto1.appendChild(nome1);
		contatto1.appendChild(cognome1);
		contatto1.appendChild(telefono1);
		contatto1.appendChild(email1);
		contatto1.appendChild(note1);
		
		contatto2.appendChild(nome2);
		contatto2.appendChild(cognome2);
		contatto2.appendChild(telefono2);
		contatto2.appendChild(email2);
		contatto2.appendChild(note2);

		// ... altri contatti

		documentElement.appendChild(contatto1);
		documentElement.appendChild(contatto2);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(new File("C://Users//Account//Desktop/esercizi/file/new_rubrica.xml"));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("File saved!");
	}

}
