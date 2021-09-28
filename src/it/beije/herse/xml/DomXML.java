package it.beije.herse.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomXML {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("/temp/test_parser1.xml");
			
			Element root = document.getDocumentElement();
			System.out.println(root.getTagName());
			System.out.println(root.getTextContent());
			System.out.println("----------------");
			Node node = root.getFirstChild();
			System.out.println(node.getTextContent());
			System.out.println("----------------");
			NodeList list = node.getChildNodes();
			
			for(int i = 0; i<list.getLength(); i++) {
				System.out.println(list.toString());
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
