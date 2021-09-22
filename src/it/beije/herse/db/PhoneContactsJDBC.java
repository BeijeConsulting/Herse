package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.beije.herse.file.Contatto;

public class PhoneContactsJDBC {
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet rs = null;
	
	public static Connection openConnection() throws ClassNotFoundException, SQLException {	
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Vecchia");
	}
	
	public static void readRubricaJDBC(){
		try {
			connection = openConnection();
			statement = connection.createStatement();
			
//			Delete in caso di errori
//			statement.executeUpdate("DELETE FROM rubrica WHERE id = 8 OR id = 9");
			
			rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("note : " + rs.getString("note"));
				System.out.println();
			}
			
		} catch (ClassNotFoundException cnfEx) {
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
	}
	
	public static void readRubricaJDBC(String[] allCols){
		try {
			connection = openConnection();
			statement = connection.createStatement();
			
			List<String> contatto = Arrays.asList("ID", "NOME", "COGNOME", "TELEFONO", "EMAIL", "NOTE");
			String select = "";
			List<String> selectCols = new ArrayList<>();
			for(int i=0;i<allCols.length;i++) {
				if(contatto.contains(allCols[i].toUpperCase())) {
					selectCols.add(allCols[i]);
					select += allCols[i]+", ";
				}
			}
			
//			Delete in caso di errori
//			statement.executeUpdate("DELETE FROM rubrica WHERE id = 8 OR id = 9");
			
			select = select.substring(0, select.length()-2);
			rs = statement.executeQuery("SELECT "+select+" FROM rubrica");
			while (rs.next()) {
				for(String s : selectCols) {
					switch(s.toUpperCase()) {
					case "ID":
						System.out.println("ID: " + rs.getInt("id"));
						break;
					case "NOME":
						System.out.println("NOME: " + rs.getString("nome"));
						break;
					case "COGNOME":
						System.out.println("COGNOME: " + rs.getString("cognome"));
						break;
					case "TELEFONO":
						System.out.println("TELEFONO: " + rs.getString("telefono"));
						break;
					case "EMAIL":
						System.out.println("EMAIL: " + rs.getString("email"));
						break;
					case "NOTE":
						System.out.println("NOTE: " + rs.getString("note"));
						break;
					}
				}
				System.out.println();
			}
			
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (StringIndexOutOfBoundsException siEx) {
			System.out.println("NESSUNA COLONNA VALIDA");
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void insertRubricaJDBC(List<Contatto> rubrica) {
		try {
			connection = openConnection();
			statement = connection.createStatement();
			
			for(Contatto c : rubrica) {
				statement.executeUpdate("INSERT INTO rubrica VALUES (null, '"+c.getNome()+"', '"+c.getCognome()+"'"
						+ ", '"+c.getTelefono()+"', '"+c.getEmail()+"', '"+c.getNote()+"')");
			}
			
		} catch (ClassNotFoundException cnfEx) {
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
	
	public static void main(String[] args) {
		List<Contatto> rubrica = new ArrayList<>();
		rubrica.add(new Contatto("Francesco", "Gialli", "312345678", "francesco.gialli@gmail.com", "Cugino"));
		rubrica.add(new Contatto("Carolina", "Marrone", "321654987", "", ""));
		insertRubricaJDBC(rubrica);
		
		readRubricaJDBC();
//		readRubricaJDBC(new String[] {"iod", "Cognom", "None"});
		readRubricaJDBC(new String[] {"id", "Cognome", "NoMe"});
	}
}
