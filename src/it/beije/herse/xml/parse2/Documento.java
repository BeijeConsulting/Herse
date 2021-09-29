package it.beije.herse.xml.parse2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public  class Documento {

	Element root = new Element();
	//	List <Node> node;
	List <Element> child = new ArrayList<>();


	public  void parse (String file) throws IOException {
		File f = new File(file);
		FileReader fileReader = new FileReader(f);
		System.out.println("exists ? " + f.exists() );


		BufferedReader reader = new BufferedReader(fileReader);

		String s = reader.readLine();
		String rootName;

		if(s.charAt(1) == '?')
			s = reader.readLine();
		//		System.out.println(s);
		rootName= s.substring(1, s.length()-1);
		//		System.out.println(rootName);
		root.setTag(rootName);

		while(reader.ready()) {
			Element e = new Element();
			s = reader.readLine();
			if(s.contains("<")) {
				int inizioTag = s.indexOf('<');
				int fineTag = s.indexOf('>');
				e.setTag(s.substring(inizioTag + 1, fineTag));
				s = s.substring(fineTag+1);
				if(s.indexOf('<')!=-1) {
					inizioTag = s.indexOf('<');
					s = s.substring(0, inizioTag);
					e.setVal(s);
					child.add(e);
				}
			}


		}
	}

	public Element getRootElement() {
		return this.root;
	}

	public List<Element> getChildElements(){
		return this.child;
	}

	List<Element> getElementsByTagName(String tagName){
		List<Element> lista = new ArrayList<>();
		for(Element i : child) {
			if(i.getTagName().equals(tagName))
				lista.add(i);
		}
		return lista;
	}



}


//<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//<contatti>
//	<contatto>
//		<nome>Pippo</nome>
//		<cognome>Pluto</cognome>
//		<telefono>3331234567</telefono>
//		<email>pippo@pluto.net</email>
//	</contatto>
//	<contatto>
//		<nome>Paolino</nome>
//		<cognome>Paperino</cognome>
//		<telefono>00423803243423</telefono>
//	</contatto>
//</contatti>
