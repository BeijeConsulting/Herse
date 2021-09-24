package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RubricaJDBC {
	
	public static Connection openConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Beije1");
		 
	}

	public static void mainStatement(String[] args)  {
		
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet rs = null;
//		
//		try {
//			connection = openConnection();
//			
//			System.out.println("connection open ? " + !connection.isClosed());
//
//			statement = connection.createStatement();
//
//			int r = 0;

//			String nome = "Gertrude";
//			r = statement.executeUpdate("INSERT INTO rubrica (cognome, nome) VALUES ('Bianchi', '" + nome + "')");

//			r = statement.executeUpdate("UPDATE rubrica SET telefono = '98767544', email = 'ger.white@tin.it' WHERE id = 14");

//			r = statement.executeUpdate("DELETE FROM rubrica WHERE id = 13");

//			System.out.println("r : " + r);

			
//			statement.execute("SELECT * FROM rubrica");
//			ResultSet rs = statement.getResultSet();
//			rs = statement.executeQuery("SELECT * FROM rubrica");
//			while (rs.next()) {
//				System.out.println("id : " + rs.getInt("id"));
//				System.out.println("nome : " + rs.getString("nome"));
//				System.out.println("cognome : " + rs.getString("cognome"));
//				System.out.println("telefono : " + rs.getString("telefono"));
//				System.out.println("email : " + rs.getString("email"));
//				System.out.println("note : " + rs.getString("note"));
//				System.out.println();
//			}
//		} catch (ClassNotFoundException cnfEx) {
//			cnfEx.printStackTrace();
//		} catch (SQLException sqlEx) {
//			sqlEx.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				statement.close();
//				connection.close();
//			} catch (Exception e) {
//				e.getMessage();
//			}
//		}

		//SELECT * FROM USERS WHERE USERNAME='XXX'
		// ' or username <> '
		//SELECT * FROM USERS WHERE USERNAME='' or username <> '' 
	}


	public static void main(String[] args)  {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedQuery = null;
		ResultSet rs = null;
		
		Scanner scanner = new Scanner(System.in);
		
		
		try {
			connection = openConnection();
			
			System.out.println("connection open ? " + !connection.isClosed());
			
			System.out.println("Enter name");
			String name = scanner.nextLine();
			System.out.println("Enter surname");
			String surname = scanner.nextLine();
			System.out.println("Enter phone numer");
			String phone = scanner.nextLine();
			System.out.println("Enter email");
			String email = scanner.nextLine();
			System.out.println("Enter notes");
			String notes = scanner.nextLine();

			int r = 0;
			
			//String nome = "Gertrude";
			preparedStatement  = connection.prepareStatement("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES (?,?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, notes);
//			
//			preparedStatement  = connection.prepareStatement("UPDATE rubrica SET nome = ?, cognome = ? , telefono = ? WHERE id = ?");
//			preparedStatement.setString(1, "$Gianantonio");
//			preparedStatement.setDouble(2, 2.0);
//			preparedStatement.setString(3, "894894");
//			preparedStatement.setInt(4, 5);
		
			
			//preparedStatement = connection.prepareStatement("DELETE FROM herse.rubrica WHERE id = 6");
			
			
			r = preparedStatement.executeUpdate();
			System.out.println("r : " + r);

//			preparedQuery = connection.prepareStatement("SELECT * FROM rubrica where cognome = ?");
//			preparedQuery.setString(1, "Verdi");
//			rs = preparedQuery.executeQuery();
//			while (rs.next()) {
//				System.out.println("id : " + rs.getInt("id"));
//				System.out.println("nome : " + rs.getString("nome"));
//				System.out.println("cognome : " + rs.getString("cognome"));
//				System.out.println("telefono : " + rs.getString("telefono"));
//				System.out.println("email : " + rs.getString("email"));
//				System.out.println("note : " + rs.getString("note"));
//				System.out.println();
//			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				//rs.close();
				preparedStatement.close();
				//preparedQuery.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

		//SELECT * FROM USERS WHERE USERNAME='XXX'
		// ' or username <> '
		//SELECT * FROM USERS WHERE USERNAME='' or username <> '' 
	}

}
