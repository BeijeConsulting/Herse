/*
 * Fare una specie di connection pool che deve gestire un massimo 5 connessioni a una stessa cosa.
 */


package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	
	private ConnectionPool() {}
	
	private static Connection connection;

	public static Connection getConnection(int numOfConnection) throws SQLException, ClassNotFoundException {
		
		if(connection == null || numOfConnection <= 5 || connection.isClosed()) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Davi.de123");
		} else if (numOfConnection > 5) {
			System.out.println("Tutte e 5 le connessioni disponibili sono occupate");
		}
		
		return connection;
		
	}
	

		

}
