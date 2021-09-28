package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static int count;
	private static final int maxIstance=5;
	private static Connection connection;
	
	private ConnectionManager() {
		count++;
	}
	
	
		public static Connection getConnection() throws ClassNotFoundException, SQLException {
			if(count<maxIstance) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET\", \"root\", \"caba");
				
			}
			return connection;
		}//fine getConnection
		
		public static boolean closeConnection(ConnectionManager conn) throws SQLException {
			if(!conn.connection.isClosed()) {
				conn.connection.close();
				count--;
			}
			return false;
		}
	}
	
	

