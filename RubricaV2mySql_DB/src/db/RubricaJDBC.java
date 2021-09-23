package db;

import java.sql.*;
import java.util.Scanner;

public class RubricaJDBC {

	public static Connection openConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "tappocubico");
	}

	public static void main(String[] args)  {

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedQuery = null;

		
		try {
			connection = openConnection();
			
			System.out.println("connection open ? " + !connection.isClosed());

			statement = connection.createStatement();

			int r = 0;

			Scanner scanner = new Scanner(System.in);
			/*
			System.out.println("Nome: ");
			String nome = scanner.nextLine();
			System.out.println("Congnome: ");
			String cognome = scanner.nextLine();
			System.out.println("Telefono: ");
			String telefono = scanner.nextLine();
			System.out.println("Email: ");
			String email = scanner.nextLine();
			System.out.println("Note: ");
			String note = scanner.nextLine();
			*/

			//r = statement.executeUpdate("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES ('"+ nome + "', '" + cognome + "', '" + telefono + "', '" + email + "', '" + note + "')");

			preparedStatement  = connection.prepareStatement("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES (?,?,?,?,?)");
			for (int i = 0; i < args.length; i++){
				preparedStatement.setString(i+1, args[i]);
			}
			preparedStatement.executeUpdate();

			System.out.println("r : " + r);

			statement.execute("SELECT * FROM rubrica");
			ResultSet rs1 = statement.getResultSet();
			rs1 = statement.executeQuery("SELECT * FROM rubrica");
			while (rs1.next()) {
				System.out.println("id : " + rs1.getInt("id"));
				System.out.println("nome : " + rs1.getString("nome"));
				System.out.println("cognome : " + rs1.getString("cognome"));
				System.out.println("telefono : " + rs1.getString("telefono"));
				System.out.println("email : " + rs1.getString("email"));
				System.out.println("note : " + rs1.getString("note"));
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
}
