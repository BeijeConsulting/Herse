package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DbTooManyRequest {

	public static Connection openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Davi.de123");
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection connection = null;

		int count = 0;

		LocalTime start = LocalTime.now();

		LocalTime end = null;
		
		boolean continueNO = true;

		while(continueNO) {
			try {
				connection = openConnection();
				count ++;

			} catch(SQLNonTransientConnectionException e) {
				end = LocalTime.now();
				continueNO = false;
				System.out.println("Too many connection");
				System.out.println("Number of connections: " + count );
				System.out.println("Time first connection: " + start);
				System.out.println("Time last connection: " + end);
				long time = ChronoUnit.SECONDS.between(start, end);
				System.out.println(count + " connections in " + time + " seconds");
				break;
			}
				
		}
		
		
	}

}
