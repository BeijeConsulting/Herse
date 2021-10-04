package it.beije.herse.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CreaRubricaXML {

	public static void main(String[] args) {
		

		
	}
	
	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element) childElements.add((Element)nodeList.item(n));
		}
		
		return childElements;
	}

	
	public static void readXML() throws ParserConfigurationException, IOException, SAXException {
		File f = new File("/Users/Dinamite/Desktop/rubrica.xml");
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

}
