package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyRubricaJDBC {

	public static void main(String[] args) {
		Connection connection;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/herse?serverTimezone=CET&" + "user=root&password=nManfredi");
			System.out.println("connected? " + connection.isValid(1000));

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM rubrica");

			while (resultSet.next()) {
				System.out.println("id : " + resultSet.getInt("id"));
				System.out.println("nome : " + resultSet.getString("nome"));
				System.out.println("cognome : " + resultSet.getString("cognome"));
				System.out.println("telefono : " + resultSet.getString("telefono"));
				System.out.println("email : " + resultSet.getString("email"));
				System.out.println("note : " + resultSet.getString("note"));
				System.out.println();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}
