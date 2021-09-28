package it.beije.herse.db;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Saturare {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		for(int i =0;;i++) {
			System.out.println(i);
			Connection connection = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "borgese");
			Statement statement = null;
			ResultSet rs = null;

			statement = connection.createStatement();

			rs = statement.getResultSet();
			rs = statement.executeQuery("SELECT * FROM rubrica");
		}
	}
}
