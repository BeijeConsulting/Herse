package it.beije.herse.file;

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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Rubrica {

	private List<Element> getChildElements(Element element) {

		List<Element> child = new ArrayList<Element>();
		NodeList list = element.getChildNodes();

		for(int i = 0; i < list.getLength(); i++)
			if(list.item(i) instanceof Element)
				child.add((Element)list.item(i));

		return child;

	}

	private void addField(Contatto c, String fieldName, String fieldValue) {

		switch(fieldName.toLowerCase()) {

		case "id":
			c.setId(Integer.parseInt(fieldValue));
			break;
		case "nome":
			c.setNome(fieldValue);
			break;
		case "cognome":
			c.setCognome(fieldValue);
			break;
		case "telefono":
			c.setTelefono(fieldValue);
			break;
		case "email":
			c.setEmail(fieldValue);
			break;
		case "note":
			c.setNote(fieldValue);
			break;
		}

	}

	public List<Contatto> loadRubricaFromXML(String file) throws ParserConfigurationException, SAXException, IOException {

		List<Contatto> list = new ArrayList<Contatto>();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.parse(file);

		Element rubrica = doc.getDocumentElement();

		List<Element> contatti = getChildElements(rubrica);

		for(Element el : contatti) {

			List<Element> listEl = getChildElements(el);
			Contatto c = new Contatto();

			for(Element e : listEl) {
				addField(c,e.getTagName(),e.getTextContent());
			}

			list.add(c);
		}

		return list;

	}

	public List<Contatto> loadRubricaFromCSV(String file, String separator) throws IOException {

		List<Contatto> list = new ArrayList<Contatto>();
		List<String[]> contatti = new ArrayList<String[]>();
		BufferedReader reader = new BufferedReader(new FileReader(file));

		while(reader.ready()) {
			String s = reader.readLine();
			contatti.add(s.substring(1, s.length()-1).split(separator));
		}

		for(int i = 1; i < contatti.size(); i++) {

			Contatto c = new Contatto();

			for(int j = 0; j < contatti.get(0).length; j++) {
				addField(c, contatti.get(0)[j], contatti.get(i)[j]);
			}

			list.add(c);

		}

		reader.close();

		return list;

	}

	public void writeRubricaCSV(List<Contatto> list, String pathFile, String separator) throws IOException {

		FileWriter fw;

		if(!new File(pathFile).exists()) {

			fw = new FileWriter(pathFile);

			fw.write("\"COGNOME" + separator + "NOME" + separator + "TELEFONO" + separator + "EMAIL" + separator + "NOTE" + "\"\n");

			for(Contatto c : list) {

				String s = "\"" + c.getCognome() + separator + c.getNome() + separator + c.getTelefono() + separator + c.getEmail() + separator + c.getNote() + "\"\n";
				fw.write(s);

			}

		} else {

			fw = new FileWriter(pathFile,true);

			for(Contatto c : list) {

				String s = "\"" + c.getCognome() + separator + c.getNome() + separator + c.getTelefono() + separator + c.getEmail() + separator + c.getNote() + "\"\n";
				fw.write(s);

			}

		}

		fw.flush();
		fw.close();

	}

	public void writeRubricaXML(List<Contatto> contatti, String pathFile) throws ParserConfigurationException, TransformerException, SAXException, IOException {

		if(new File(pathFile).exists()) {

			List<Contatto> oldContatti = loadRubricaFromXML(pathFile);

			for(int i = oldContatti.size()-1; i >= 0; i--)
				contatti.add(0, oldContatti.get(i));

		}	

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.newDocument();

		Element elContatti = doc.createElement("contatti");

		doc.appendChild(elContatti);

		for(Contatto c : contatti) {

			Element contatto = doc.createElement("Contatto");

			Element id = doc.createElement("Id");
			id.setTextContent(String.valueOf(c.getId()));
			Element cognome = doc.createElement("Cognome");
			cognome.setTextContent(c.getCognome());
			Element nome = doc.createElement("Nome");
			nome.setTextContent(c.getNome());
			Element telefono = doc.createElement("Telefono");
			telefono.setTextContent(c.getTelefono());
			Element email = doc.createElement("Email");
			email.setTextContent(c.getEmail());
			Element note = doc.createElement("Note");
			note.setTextContent(c.getNote());

			contatto.appendChild(id);
			contatto.appendChild(cognome);
			contatto.appendChild(nome);
			contatto.appendChild(telefono);
			contatto.appendChild(email);
			contatto.appendChild(note);

			elContatti.appendChild(contatto);

		}

		// write the content into xml file
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new File(pathFile));

		transformer.transform(source, result);

	}


	/*
	 * vedi lista contatti (con possibilità di ordinare per nome e cognome a scelta)
	 * cerca contatto
	 * inserisci nuovo contatto
     * modifica contatto
     * cancella contatto
     * trova contatti duplicati
     * unisci contatti duplicati
	 */
	
	private void showContact(List<Contatto> list) {

		for(Contatto c : list) {

			System.out.println("Cognome: " + (c.getCognome() != null ? c.getCognome() : ""));
			System.out.println("Nome: " + (c.getNome() != null ? c.getNome() : ""));
			System.out.println("Telefono: " + (c.getTelefono() != null ? c.getTelefono() : ""));
			System.out.println("Email: " + (c.getEmail() != null ? c.getEmail() : ""));
			System.out.println("Note: " + (c.getNote() != null ? c.getNote() : "") + "\n");

		}

	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		// TODO Auto-generated method stub

		List<Contatto> list = new ArrayList<Contatto>();

		Rubrica r = new Rubrica();

		list = r.loadRubricaFromCSV("\\Users\\savin\\Desktop\\fileTest\\rubrica.csv","\";\"");

		r.showContact(list);

		//r.writeRubricaXML(list, "\\Users\\savin\\Desktop\\fileTest\\newProvaRubricaXML.xml");

	}

}
