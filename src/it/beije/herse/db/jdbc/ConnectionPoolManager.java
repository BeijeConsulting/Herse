package it.beije.herse.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoolManager {

	private ConnectionPoolManager() {}
	
	private static Connection[] connections = new Connection[151];
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		int firstClosedConnection = getFirstClosedConnection();
		int firstNullConnection = getFirstNullConnection();
		
		if(firstClosedConnection!=-1) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connections[firstClosedConnection] = DriverManager.
					getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Vecchia");
			return connections[firstClosedConnection];
		}
		else if(firstNullConnection!=-1) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connections[firstNullConnection] = DriverManager.
					getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Vecchia");
			return connections[firstNullConnection];
		}
		
		return connections[(int) (Math.random()*150)];
		// Altre opzioni:
		// -> connections[0]
		// -> 
	}
	
	private static int getFirstNullConnection() {
		for(int i=0;i<connections.length;i++) if(connections[i]==null) return i;
		return -1;
	}
	
	private static int getFirstClosedConnection() throws SQLException {
		for(int i=0;i<connections.length;i++) if(connections[i].isClosed()) return i;
		return -1;
	}
}
