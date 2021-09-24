package it.beije.herse.oca.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class GestioneRubrica {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

//		List<Contatto> contatti = loadRubricaXml("C://Users//Account//Desktop/esercizi/file/rubrica.xml");
//		System.out.println(contatti);
//		writeRubricaXML(contatti, "C://Users//Account//Desktop/esercizi/file/new_rubrica.xml");
//		loadRubricaFromCSV("C://Users//Account//Desktop/esercizi/file/prova.txt", ";");
//		addRubricaInFileXml(contatti, "C://Users//Account//Desktop/esercizi/file/new_rubrica.xml");
//		writeRubricaCSV(contatti, "C://Users//Account//Desktop/esercizi/file/new_rubrica.csv", ";");
		Contatto contatto1 = new Contatto("Roberta", "Bianchi", "423903", "rb@yahoo.it", "contatto bianchi");
//		try {
//			insertContattoInDb(contatto1);
//			//readContattoFromDb();
//		} catch (ClassNotFoundException | SQLException e) {	
//			e.printStackTrace();
//		}
//		Contatto contatto = new Contatto("mattia","pistacchio","42789421","gs@gmail.com","email esistente");

	}

	
	public static boolean insertContattoInDb(Contatto c) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Collura1");
		
		System.out.println("connection open ? " + !connection.isClosed());

		Statement statement = connection.createStatement();
		
		boolean isInserted = false;
		
		String nome = c.getNome();
		String cognome = c.getCognome();
		String telefono = c.getTelefono();
		String email = c.getEmail();
		String note = c.getNote();
		
		int numInsert = 0;
		numInsert= statement.executeUpdate("INSERT INTO rubrica (cognome, nome, telefono, email, note) VALUES ('" + nome + "', '" + cognome + "', '" + telefono + "', '" + email + "', '" + note + "')");
		
		ResultSet rs = statement.getResultSet();
		
		if(numInsert > 0) {
			isInserted = true;
		}
		
		return true;
	}
	
	public static List<Contatto> readContattoFromDb() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Collura1");
		
		System.out.println("connection open ? " + !connection.isClosed());
		
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
		
		List<Contatto> listaContatti = new ArrayList<Contatto>();
		
		while (rs.next()) {
			Contatto c = new Contatto();
			System.out.println("id : " + rs.getInt("id"));
			System.out.println("nome : " + rs.getString("nome"));
			c.setNome(rs.getString("nome"));
			System.out.println("cognome : " + rs.getString("cognome"));
			c.setCognome(rs.getString("cognome"));
			System.out.println("telefono : " + rs.getString("telefono"));
			c.setTelefono(rs.getString("telefono"));
			System.out.println("email : " + rs.getString("email"));
			c.setEmail(rs.getString("email"));
			System.out.println("note : " + rs.getString("note"));
			c.setNote(rs.getString("note"));
			System.out.println();
			
			listaContatti.add(c);
		}
		
		rs.close();
		statement.close();
		connection.close();
		
		for(Contatto ct : listaContatti) {
			System.out.println("contatto lista"+ct);
		}
		return listaContatti;
	}
	
	
	
}
