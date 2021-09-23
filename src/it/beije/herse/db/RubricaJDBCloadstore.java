package it.beije.herse.db;

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

import it.beije.herse.file.Contatto;



public class RubricaJDBCloadstore {



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

	public static List<Contatto> exportDB(){
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

	public static void importDB(List<Contatto> rubricaDB) {
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


	public static void main(String[] args) throws IOException {
		List <Contatto> rubricaDB = null;

		try {
			rubricaDB = RubricaFile.loadRubricaFromCSV("/temp/prova.csv", ";");
		}catch(IOException ioe){
			ioe.printStackTrace();
		}


		importDB(rubricaDB);
		RubricaFile.writeRubricaCSV(rubricaDB, "/temp/RubricaCSV.csv", ";");
		rubricaDB = exportDB();


	}

}
