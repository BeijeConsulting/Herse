package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


	

public class RubricaJDBC {
	
public static Connection openConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "borgese");
		 
	}
	
	public static List <Contatto>  loadRubricaDB(){
		List<Contatto> rubrica = new ArrayList<Contatto>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = openConnection();
			
			System.out.println("connection open ? " + !connection.isClosed());

			statement = connection.createStatement();
			
			 rs = statement.getResultSet();
			 rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				Contatto c = new Contatto();
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setTelefono(rs.getString("telefono"));
				c.setNote(rs.getString("note"));
				rubrica.add(c);
				}
			
		}catch (ClassNotFoundException cnfEx) {
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
		
		
		return rubrica;
	}
	
	public static void writeRubricaDB(List<Contatto> rubrica) {

		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = openConnection();
			
			System.out.println("connection open ? " + !connection.isClosed());

			statement = connection.createStatement();
			
			for(int i = 0; i<rubrica.size(); i++) {
			Contatto c = rubrica.get(i);
			String nome = c.getNome();
			String cognome = c.getCognome();
			String email = c.getEmail();
			String telefono = c.getTelefono();
			String note = c.getNote();
			 statement.executeUpdate("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES ('" + nome + "','"  + cognome +
					 											"','" + telefono +  "','" + email + "','" + note + "')" ); 
			}
			
		}catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		List <Contatto> rubricaDB = RubricaFile.loadRubricaFromCSV("/temp/prova.csv", ";");
//		System.out.println(rubricaList.get(0).getNome());
	
//		System.out.println(rubricaDB.get(0).getNome());
		writeRubricaDB(rubricaDB);
		List <Contatto> rubrica = loadRubricaDB();
		
		
	}

}
