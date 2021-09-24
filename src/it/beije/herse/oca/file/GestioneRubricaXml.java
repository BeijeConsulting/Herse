package it.beije.herse.oca.file;

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

public class GestioneRubricaXml {
	
	
	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element)
				childElements.add((Element) nodeList.item(n));
		}
		return childElements;
	}


	public static List<Contatto> loadRubricaXml(String path)
			throws ParserConfigurationException, IOException, SAXException {
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

	public static void writeRubricaXML(List<Contatto> contatti, String pathFile)
			throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		Element documentElement = document.createElement("contatti");
		document.appendChild(documentElement);

		for (int i = 0; i < contatti.size(); i++) {
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

	//metodo di scrittura se il file è già presente
	static void addRubricaInFileXml(List<Contatto> contatti, String path)
			throws ParserConfigurationException, TransformerException, SAXException, IOException {
		File f = new File(path);
		if (f.exists()) {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse(f);

			Element rubrica = document.getDocumentElement();
			Contatto c = null;
			for (int i = 0; i < contatti.size(); i++) {
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

			StreamResult syso = new StreamResult(System.out);
			transformer.transform(source, result);
			transformer.transform(source, syso);
			System.out.println("File saved!");

		} else
			writeRubricaXML(contatti, path);
	}
}
