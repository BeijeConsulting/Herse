package it.beije.herse.xml.parse2;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		Documento document = new Documento();
		document.parse("/temp/test_parser1.xml");
		Element elemento = new Element();
		elemento = document.getRootElement();
		System.out.println("root " + elemento.getTagName());
		System.out.println("-----------------------");
		List<Element> listElement = document.getChildElements();
		for(Element i : listElement)
			System.out.println("tag: " + i.getTagName() + " valore  " + i.getTextTag());
		listElement = document.getElementsByTagName("nome");
		System.out.println("-----------------------");
		for(Element i : listElement)
			System.out.println("tag: " + i.getTagName() + " valore  " + i.getTextTag());

	}

}
