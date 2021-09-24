package it.beije.herse.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Documento {

	Element root;
//	List <Node> node;
	List <Element> child;


	public  void parse (String file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		
		String s = reader.readLine();
		String rootName;
		
		if(s.charAt(1) == '?')
			s = reader.readLine();
		
		rootName= s.substring(1, s.length()-1);
		root.setTag(rootName);
		
		while(reader.ready()) {
			Element e = new Element();
			s = reader.readLine();
			if(s.contains("<")) {
				int inizioTag = s.indexOf('<');
				int fineTag = s.indexOf('>');
				e.setTag(s.substring(inizioTag, fineTag));
				s = s.substring(fineTag+1);
				if(s.indexOf('<')!=-1) {
					 inizioTag = s.indexOf('<');
				}
			}
				
			
		}
		
//		<contatto>
 // Pippo</nome>
		
	}
	
	public Element getRootElement() {
		return this.root;
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
	