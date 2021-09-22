package it.beije.herse.oca.DBPrimo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBPrimo {
	public static Connection openConnection()throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "Attaccante1!");
	}

	public static void main(String[] args) {
		Connection connection=null;
		Statement statement=null;
		ResultSet rs=null;
		try {
			connection=openConnection();
			System.out.println("connection open ? " + !connection.isClosed());
			statement=connection.createStatement();	
			rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("note : " + rs.getString("note"));
				System.out.println();
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}

}
