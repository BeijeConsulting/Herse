package it.beije.herse.database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;


public class RubricaJdbcManager {

	public static Connection openConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Beije1");

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {

		Connection connection = null;
				
		connection = openConnection();
		int numberConnection = 0;
		long start = System.currentTimeMillis();
		long timeElapsed = 0;
		
//		while (!connection.isClosed()) {
//			connection = openConnection();
//			numberConnection++;
//			System.out.println(numberConnection);
//			long finish = System.currentTimeMillis();
//			long timeElapsed = finish - start;
//			System.out.println("time"+timeElapsed);
//		}
		
		try {
			
			while (!connection.isClosed()) {
				connection = openConnection();
				numberConnection++;
				long finish = System.currentTimeMillis();
				timeElapsed = finish - start;
				
			}
		} catch(SQLNonTransientConnectionException e) {
			e.getMessage();
		} finally {
			System.out.println("milliseconds: "+timeElapsed);
			System.out.println("number of connections: "+numberConnection);
		}

	}
}
