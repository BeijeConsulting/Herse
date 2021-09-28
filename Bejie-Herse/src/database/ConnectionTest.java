package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	
	public static Connection openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "caba");
	}//fine metodo openConnection
	
	public static void main(String... args) throws ClassNotFoundException, SQLException {
		Connection connection;
		
		for(;;) {
			connection=ConnectionTest.openConnection();
			System.out.println(connection.toString()+"is open= "+!connection.isClosed());
		}
	}

}
