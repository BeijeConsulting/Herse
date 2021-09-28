package database;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import org.xml.sax.SAXException;

public class RubricaFile {

	public static List<Contatto> loadRubricaFromCSV (String pathFile, String separatorChar) throws IOException {
		FileReader fileReader = new FileReader(pathFile);

		BufferedReader reader = new BufferedReader(fileReader);
		String row = reader.readLine();
		String separator1 = new StringBuilder("\'").append(separatorChar).append('\'').toString();	
		String separator2 = new StringBuilder("\"").append(separatorChar).append('\"').toString();
		String separator = null;
		if (row.contains(separator1)) {
			separator = separator1;
		} else if (row.contains(separator2)) {
			separator = separator2;
		} else {
			separator = separatorChar;
		}
	//	System.out.println("separator: " + separator);

		String[] title = row.split(separator);
		int last = title.length - 1;
		if (separator.length() > 1) {
			title[0] = title[0].substring(1);
			title[last] = title[last].substring(0, title[last].length()-1);
		}

		List<Contatto> contatti = new ArrayList<Contatto>();
		Contatto contatto = null;
		String[] cols = null;
		while (reader.ready()) {
			row = reader.readLine();
			//System.out.println(row);

			cols = row.split(separator);
			if (separator.length() > 1) {
				cols[0] = cols[0].substring(1);
				cols[last] = cols[last].substring(0, cols[last].length()-1);
			}

			contatto = new Contatto();
			for (int i = 0; i <= last; i++) {
			//	System.out.println(title[i] + ": " + cols[i]);
				switch (title[i].toUpperCase()) {
				case "NOME":
					contatto.setNome(cols[i]);
					break;
				case "COGNOME":
					contatto.setCognome(cols[i]);		
					break;
				case "TELEFONO":
					contatto.setTelefono(cols[i]);		
					break;
				case "EMAIL":
					contatto.setEmail(cols[i]);		
					break;
				case "NOTE":
					contatto.setNote(cols[i]);		
					break;
				}
			}

			contatti.add(contatto);
		//	System.out.println();
		}

		reader.close();

		return contatti;
	}


	public static void writeRubricaCSV( List<Contatto> contatti, String pathFile, String separatorChar) throws IOException {
		File f = new File(pathFile);
		FileWriter writer = new FileWriter(f, f.exists());
		if(!f.exists()) {
		writer.write("NOME" + separatorChar + "COGNOME" + separatorChar + "TELEFONO" + separatorChar);
		writer.write("EMAIL" + separatorChar + "NOTE\n");
		}
		Contatto contatto = null;
		for(int i = 0; i<contatti.size(); i++) {
			contatto = contatti.get(i);
			writer.write(contatto.getNome() + separatorChar);
			writer.write(contatto.getCognome() + separatorChar);
			writer.write(contatto.getTelefono() + separatorChar);
			writer.write(contatto.getEmail() + separatorChar);
			writer.write(contatto.getNome() + "\n");
		}
		writer.flush();
		writer.close();

	}

	static void writeRubricaXML(List<Contatto> contatti, String pathfile) throws ParserConfigurationException, TransformerException, SAXException, IOException {
		File f = new File(pathfile);
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

			StreamResult result = new StreamResult(new File(pathfile));

			// Output to console for testing
			StreamResult syso = new StreamResult(System.out);

			transformer.transform(source, result);
			transformer.transform(source, syso);

			System.out.println("File saved!");	

		}
		else
			writeXMLifExists(contatti, pathfile);
	}

	public static void  writeXMLifExists(List<Contatto> rubricaLista, String pathname) throws ParserConfigurationException, TransformerException{
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}