package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Attacco {
	
	
	public static Connection connection;
	

	public static void main(String[] args) {
		
		  int supp=0;
	
		try {
			
			do {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "abcabcde");
				//System.out.println("connection open ? " + !connection.isClosed());
				supp++;
			} while (!connection.isClosed()) ;
			
		} catch (Exception e) {
			
			System.out.println(supp);
			
		}


	}

}
