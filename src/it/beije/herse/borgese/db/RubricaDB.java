package it.beije.herse.borgese.db;

import it.beije.herse.file.RubricaFile;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.herse.file.Contatto;



public class RubricaDB {



	public static Connection openConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "borgese");

	}

	public static List <Contatto>  loadRubricaDB(){
		List<Contatto> rubrica = new ArrayList<Contatto>();

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			connection = openConnection();

			System.out.println("connection open ? " + !connection.isClosed());

			statement = connection.createStatement();

			rs = statement.getResultSet();
			rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				Contatto c = new Contatto();
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setTelefono(rs.getString("telefono"));
				c.setNote(rs.getString("note"));
				rubrica.add(c);
			}

		}catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return rubrica;
	}

	public static void writeRubricaDB(List<Contatto> rubrica) {

		Connection connection = null;
		Statement statement = null;
		try {
			connection = openConnection();

			System.out.println("connection open ? " + !connection.isClosed());

			statement = connection.createStatement();

			for(int i = 0; i<rubrica.size(); i++) {
				Contatto c = rubrica.get(i);
				String nome = c.getNome();
				String cognome = c.getCognome();
				String email = c.getEmail();
				String telefono = c.getTelefono();
				String note = c.getNote();

				StringBuffer sb = new StringBuffer("").append(nome).append("','").append(cognome).append("','").append(telefono).append("','")
						.append(telefono).append("','").append(note).append("')");
				String s = sb.toString();
				statement.executeUpdate("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES ('" + s);

				//			statement.executeUpdate("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES ('" + nome + "','"  + cognome +
				//					 											"','" + telefono +  "','" + email + "','" + note + "')" ); 
			}

		}catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Contatto> importDB(){
		List <Contatto> rubricaDB = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedQuery = null;
		ResultSet rs = null;

		try {
			connection = openConnection();

			System.out.println("connection open ? " +!connection.isClosed());
			preparedQuery = connection.prepareStatement("SELECT * FROM rubrica");
			rs = preparedQuery.executeQuery();

			while (rs.next()) {
				Contatto c = new Contatto();
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setTelefono(rs.getString("telefono"));
				c.setNote(rs.getString("note"));
				rubricaDB.add(c);
			}

		}catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				rs.close();
				preparedQuery.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}



		}
		return rubricaDB;  	
	}

	public static void exportDB(List<Contatto> rubricaDB) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = openConnection();

			System.out.println("connection open ? " + !connection.isClosed());

			for(int i = 0; i<rubricaDB.size(); i++) {
				Contatto c = rubricaDB.get(i);
				String nome = c.getNome();
				String cognome = c.getCognome();
				String email = c.getEmail();
				String telefono = c.getTelefono();
				String note = c.getNote();
				preparedStatement  = connection.prepareStatement("INSERT INTO rubrica (nome, cognome, email, telefono, note) VALUES (?,?,?,?,?)");
				preparedStatement.setString(1, nome);
				preparedStatement.setString(2, cognome);
				preparedStatement.setString(3, email);
				preparedStatement.setString(4, telefono);
				preparedStatement.setString(5, note);
				preparedStatement.executeUpdate();

			}
		}catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}		
	
	
	public static List<Contatto> exportHBM() {
		
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		System.out.println("session is open ? " + session.isOpen());
		
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		
		List<Contatto> contatti = query.getResultList();
		
		session.close();
		
		return contatti;
	}
	
	
	public static void importHB(List<Contatto> contatti) {

		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		System.out.println("session is open ? " + session.isOpen());
		
		Transaction transaction = session.beginTransaction();
		
		for(Contatto c : contatti) {
			Contatto newContatto = new Contatto();
			newContatto.setCognome(c.getCognome());
			newContatto.setNome(c.getNome());
			newContatto.setEmail(c.getEmail());
			newContatto.setNote(c.getNote());
			System.out.println("contatto PRE : " + newContatto);
			session.save(newContatto);
			System.out.println("contatto POST : " + newContatto);

		}
		
		transaction.commit();
		session.close();
		
	}


	public static void main(String[] args)  {
		


	}

}
