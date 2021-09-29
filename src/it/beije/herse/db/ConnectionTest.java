package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionTest {

	public static Connection openConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "savino");

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ConnectionManager conn;

		do {

			conn = ConnectionManager.newInstance();
			
			if(conn != null) {
				
				ResultSet rs = conn.executeQuery("SELECT * FROM rubrica");
				while(rs.next()) {
					System.out.println(conn + " = " + rs.getString("nome") + " " + rs.getString("cognome"));
				}

			} else {
				System.out.println("Non puoi aprire altre connessioni");
			}

		} while(conn != null);

	}

}
