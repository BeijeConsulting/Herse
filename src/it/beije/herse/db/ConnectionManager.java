package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static int count = 0;
	private static final int maxInstance = 5;
	private Connection connection;
	
	private ConnectionManager() {
		count++;
	}

	public static ConnectionManager newInstance() throws ClassNotFoundException, SQLException {
		
		ConnectionManager c = new ConnectionManager();
		
		if (count <= maxInstance) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "savino");
		} else {
			return null;
		}
		
		return c;
		
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return connection.createStatement().executeQuery(query);		
	}
	
	public boolean execute(String query) throws SQLException {		
		return connection.createStatement().execute(query);
	}
	
	public boolean closeConnection() throws SQLException {
		
		boolean closed;
		
		if(!(closed = connection.isClosed())) {
			connection.close();
			count--;
		}
		
		return closed;
		
	}
	
}
