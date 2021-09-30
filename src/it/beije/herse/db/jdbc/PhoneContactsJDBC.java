package it.beije.herse.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.beije.herse.file.Contatto;
import it.beije.herse.file.xml.PhoneContactsXML;

public class PhoneContactsJDBC {
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;
	private static final List<String> contatto = Arrays.asList("ID", "NOME", "COGNOME", "TELEFONO", "EMAIL", "NOTE");
	
	private static List<Connection> openedConnection = new ArrayList<>();
	
	public static Connection openConnection() throws ClassNotFoundException, SQLException {	
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Vecchia");
	}
	
	public static List<Contatto> readRubricaJDBC(){
		List<Contatto> rubrica = new ArrayList<>();
		try {
			connection = openConnection();
			statement = connection.createStatement();
			
//			Delete in caso di errori
//			statement.executeUpdate("DELETE FROM rubrica WHERE id = 8 OR id = 9");
			
			rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				Contatto c = new Contatto();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setTelefono(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				c.setNote(rs.getString("note"));
				rubrica.add(c);
			}
			
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
			}
		}
		return rubrica;
	}

	public static List<Contatto> readRubricaJDBC(String where, String val){
		List<Contatto> rubrica = new ArrayList<>();
		try {
			connection = openConnection();
			statement = connection.createStatement();
			
			Integer id=null;
			if(!contatto.contains(where.toUpperCase())) throw new Exception();
			where = where.toLowerCase();
			if(where.equalsIgnoreCase("id")) {
				id = Integer.parseInt(val);
				rs = statement.executeQuery("SELECT * FROM rubrica WHERE "+where+"="+"'"+id+"'");
			}
			else rs = statement.executeQuery("SELECT * FROM rubrica WHERE "+where+"="+"'"+val+"'");
			
//			Delete in caso di errori
//			statement.executeUpdate("DELETE FROM rubrica WHERE id = 8 OR id = 9");
			
			while (rs.next()) {
				Contatto c = new Contatto();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setTelefono(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				c.setNote(rs.getString("note"));
				rubrica.add(c);
			}
			
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		} catch (Exception e) {
			System.out.println("NESSUNA COLONNA VALIDA");
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
			}
		}
		return rubrica;
	}
	
	public static List<Contatto> readRubricaJDBC(String orderBy){
		List<Contatto> rubrica = new ArrayList<>();
		try {
			connection = openConnection();
			statement = connection.createStatement();
			
			if(!contatto.contains(orderBy.toUpperCase())) throw new Exception();
			orderBy = orderBy.toLowerCase();
			rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY "+orderBy);
			
//			Delete in caso di errori
//			statement.executeUpdate("DELETE FROM rubrica WHERE id = 8 OR id = 9");
			
			while (rs.next()) {
				Contatto c = new Contatto();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setTelefono(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				c.setNote(rs.getString("note"));
				rubrica.add(c);
			}
			
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		} catch (Exception e) {
			System.out.println("NESSUNA COLONNA VALIDA");
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
			}
		}
		return rubrica;
	}
	
	public static void printRubrica(List<Contatto> rubrica) {
		for(Contatto c : rubrica) {
			System.out.println("ID: "+c.getId());
			System.out.println("NOME: "+c.getNome());
			System.out.println("COGNOME: "+c.getCognome());
			System.out.println("TELEFONO: "+c.getTelefono());
			System.out.println("EMAIL: "+c.getEmail());
			System.out.println("NOTE: "+c.getNote());
			System.out.println();
		}
	}
	
	// NON SERVE IN PHONE CONTACTS MANAGER
	public static void readRubricaJDBC(String[] select){
		try {
			connection = openConnection();
			statement = connection.createStatement();
			
			String validSelect = "";
			List<String> validCols = new ArrayList<>();
			for(int i=0;i<select.length;i++) {
				if(contatto.contains(select[i].toUpperCase())) {
					validCols.add(select[i]);
					validSelect += select[i]+", ";
				}
			}
			
//			Delete in caso di errori
//			statement.executeUpdate("DELETE FROM rubrica WHERE id = 8 OR id = 9");
			
			validSelect = validSelect.substring(0, validSelect.length()-2);
			rs = statement.executeQuery("SELECT "+validSelect+" FROM rubrica");
			while (rs.next()) {
				for(String s : validCols) {
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
	
	// NON SERVE IN PHONE CONTACTS MANAGER
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

	public static void preparedInsertRubricaJDBC(List<Contatto> rubrica) {

		
		try {
			connection = openConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO rubrica VALUES (null, ?, ?, ?, ?, ?)");
			
			for(Contatto c : rubrica) {
				preparedStatement.setString(1, c.getNome());
				preparedStatement.setString(2, c.getCognome());
				preparedStatement.setString(3, c.getTelefono());
				preparedStatement.setString(4, c.getEmail());
				preparedStatement.setString(5, c.getNote());
				preparedStatement.executeUpdate();
			}
			
		} catch (ClassNotFoundException cnfEx) {
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
	
	public static void deleteRubricaJDBC(String where, String val) {
		try {
			connection = openConnection();
			
			Integer id=null;
			if(!contatto.contains(where.toUpperCase())) throw new Exception();
			where = where.toLowerCase();
			switch(where) {
			case "id":
				id = Integer.parseInt(val);
				preparedStatement = connection.prepareStatement("DELETE FROM rubrica WHERE id = ?");
				preparedStatement.setInt(1, id);
				break;
			case "nome":
				preparedStatement = connection.prepareStatement("DELETE FROM rubrica WHERE nome = ?");
				preparedStatement.setString(1, val);
				break;
			case "cognome":
				preparedStatement = connection.prepareStatement("DELETE FROM rubrica WHERE cognome = ?");
				preparedStatement.setString(1, val);
				break;
			case "telefono":
				preparedStatement = connection.prepareStatement("DELETE FROM rubrica WHERE telefono = ?");
				preparedStatement.setString(1, val);
				break;
			case "email":
				preparedStatement = connection.prepareStatement("DELETE FROM rubrica WHERE email = ?");
				preparedStatement.setString(1, val);
				break;
			case "note":
				preparedStatement = connection.prepareStatement("DELETE FROM rubrica WHERE note = ?");
				preparedStatement.setString(1, val);
				break;
			}
			
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (Exception e) {
			System.out.println("NESSUNA COLONNA VALIDA");
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void modifyRubricaJDBC(String attr, String attrVal, String where, String whereVal) {
		try {
			connection = openConnection();
			statement = connection.createStatement();
			
			if(!contatto.contains(where.toUpperCase()) || !contatto.contains(attr.toUpperCase())) throw new Exception();
			
			where = where.toLowerCase();
			Integer whereId=null;
			
			attr = attr.toLowerCase();
			Integer attrId=null;
			if(where.equalsIgnoreCase("id")) {
				whereId = Integer.parseInt(whereVal);
				if(attr.equalsIgnoreCase("id")) {
					attrId = Integer.parseInt(attrVal);
					statement.executeUpdate("UPDATE rubrica SET "+attr+"="+"'"+attrId+"' WHERE "+where+"="+"'"+whereId+"'");
				}
				else statement.executeUpdate("UPDATE rubrica SET "+attr+"="+"'"+attrVal+"' WHERE "+where+"="+"'"+whereId+"'");
			}
			else statement.executeUpdate("UPDATE rubrica SET "+attr+"="+"'"+attrVal+"' WHERE "+where+"="+"'"+whereVal+"'");
			
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (Exception e) {
			System.out.println("NESSUNA COLONNA VALIDA");
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// NON SERVE IN PHONE CONTACTS MANAGER
	public static void breakConnectionPool(int i) {
		try {
			Connection breakableConnection = openConnection();
			openedConnection.add(breakableConnection);
			
			System.out.println("Connection number: "+i);
			
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Throwable{
//		List<Contatto> rubrica = new ArrayList<>();
//		rubrica.add(new Contatto("Francesco", "Gialli", "312345678", "francesco.gialli@gmail.com", "Cugino"));
//		rubrica.add(new Contatto("Carolina", "Marrone", "321654987", "", ""));
//		insertRubricaJDBC(rubrica);
//		
//		rubrica.clear();
//		rubrica.add(new Contatto("Lorenzo", "Rossi", "0372999777555", "lorenzo.rossi@gmail.com", ""));
//		rubrica.add(new Contatto("Elisa", "Indaco", "398765421", "", "Sorella"));
//		preparedInsertRubricaJDBC(rubrica);
//		
//		deleteRubricaJDBC("nome", "AAA");
		
//		readRubricaJDBC();
//		readRubricaJDBC(new String[] {"iod", "Cognom", "None"}); 
//		readRubricaJDBC(new String[] {"id", "Cognome", "NoMe"});
//		readRubricaJDBC("cognome", "Gialli");
//		readRubricaJDBC("cognome");
		
//		modifyRubricaJDBC("nome", "Cristina", "id", "6");
//		modifyRubricaJDBC("cognome", "Bianchi", "note", "Cugino");
//		modifyRubricaJDBC("Età", "32", "Età", "Rossi");
		
//		for(int i=1;i<=151;i++) breakConnectionPool(i);
	}
}
