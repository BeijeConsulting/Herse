package com.beije;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rubrica {

    public static List<Element> getChildElements(Element element) {
        List<Element> childElements = new ArrayList<Element>();
        NodeList nodeList = element.getChildNodes();
        for (int n = 0; n < nodeList.getLength(); n++) {
            if (nodeList.item(n) instanceof Element) childElements.add((Element)nodeList.item(n));
        }
        return childElements;
    }

    public List<Contatto> loadRubricaFromCSV(String pathFile, String separator) throws IOException {
        FileReader fileReader = new FileReader(pathFile);

        BufferedReader reader = new BufferedReader(fileReader);
        String row = reader.readLine();
        String separator1 = new StringBuilder("\'").append(separator).append('\'').toString();
        String separator2 = new StringBuilder("\"").append(separator).append('\"').toString();
        String separator3 = null;
        if (row.contains(separator1)) {
            separator3 = separator1;
        } else if (row.contains(separator2)) {
            separator3 = separator2;
        } else {
            separator3 = separator;
        }
        System.out.println("separator: " + separator);

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
            System.out.println(row);
            cols = row.split(separator);

            if (separator.length() > 1) {
                cols[0] = cols[0].substring(1);
                cols[last] = cols[last].substring(0, cols[last].length()-1);
            }
            contatto = new Contatto();
            for (int i = 0; i <= last; i++) {
                System.out.println(title[i] + ": " + cols[i]);
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
            System.out.println();
        }
        reader.close();

        return contatti;
    }

    public List<Contatto> loadRubricaFromXML(String pathFile) throws ParserConfigurationException, IOException, SAXException {

        File f = new File(pathFile);
        System.out.println("exists ? " + f.exists());

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(f);

        Element rubrica = document.getDocumentElement();
        System.out.println("documentElement: " + rubrica.getTagName());
        NodeList contatti = rubrica.getElementsByTagName("contatto");
        System.out.println("num contatti: " + contatti.getLength());

        Element el = null;
        List<Contatto> listContatti = new ArrayList<>();

        for (int i=0; i<contatti.getLength(); i++){
            el = (Element)contatti.item(i);

            List<Element> riferimenti = getChildElements(el);
            List<String> valori = new ArrayList<String>();

            for (int j=0; j<riferimenti.size(); j++){
                valori.add(riferimenti.get(j).getTextContent());
            }
            for (int s = valori.size()+1; s <= 5; s++){ //5 = lunghezza massima attributi assegnabili in rublica
                valori.add(" - ");
            }
            Contatto contatto = new Contatto(valori.get(0), valori.get(1),valori.get(2),valori.get(3),valori.get(4));

            listContatti.add(contatto);
        }

        return listContatti;
    }

    public void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) throws IOException {

        File file = new File(pathFile);
        FileWriter csvWriter = new FileWriter(file.getAbsoluteFile(), true);

        for (int i=0; i<contatti.size(); i++){
            csvWriter.append(contatti.get(i).getNome() + separator);
            csvWriter.append(contatti.get(i).getCognome() + separator);
            csvWriter.append(contatti.get(i).getTelefono() + separator);
            csvWriter.append(contatti.get(i).getEmail() + separator);
            csvWriter.append(contatti.get(i).getNote() + separator);
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public void writeRubricaXML(List<Contatto> contatti, String pathFile) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // root element
        Element root = document.createElement("rubrica");
        document.appendChild(root);

        for (int i = 0; i < contatti.size(); i++) {
            // employee element
            Element employee = document.createElement("contatto");

            root.appendChild(employee);

            // set an attribute to staff element
            Attr attr = document.createAttribute("eta");
            attr.setValue("33");
            employee.setAttributeNode(attr);

            //you can also use staff.setAttribute("id", "1") for this

            // firstname element
            Element nome = document.createElement("nome");
            nome.appendChild(document.createTextNode(contatti.get(i).getNome()));
            employee.appendChild(nome);

            // lastname element
            Element cognome = document.createElement("cognome");
            cognome.appendChild(document.createTextNode(contatti.get(i).getCognome()));
            employee.appendChild(cognome);

            // email element
            Element telefono = document.createElement("telefono");
            telefono.appendChild(document.createTextNode(contatti.get(i).getTelefono()));
            employee.appendChild(telefono);

            // department elements
            Element email = document.createElement("email");
            email.appendChild(document.createTextNode(contatti.get(i).getEmail()));
            employee.appendChild(email);

            Element note = document.createElement("note");
            note.appendChild(document.createTextNode(contatti.get(i).getNote()));
            employee.appendChild(note);

        }

        // create the xml file
        //transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(pathFile));

        // If you use
        // StreamResult result = new StreamResult(System.out);
        // the output will be pushed to the standard output ...
        // You can use that for debugging

        transformer.transform(domSource, streamResult);

    }

}
