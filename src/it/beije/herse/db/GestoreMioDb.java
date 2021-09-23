package it.beije.herse.db;
import it.beije.herse.mioDb.GestioneRubrica;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import it.beije.herse.file.*;

public class GestoreMioDb {

	public static Connection openConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "abcabcde");

	}



	public static void caricaDb(List<Contatto> contatti) {

		Connection connection = null;
		Statement statement = null;
		int verifica = 0;


		try {
			connection = openConnection();

			System.out.println("connection open ? " + !connection.isClosed());

			statement = connection.createStatement();

			int ok = 0;
			for(int i=0;i<contatti.size();i++) {

				Contatto contatto = contatti.get(i);



				ok = statement.executeUpdate("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES ('"
						+ contatto.getNome() + "','" + contatto.getCognome() + "','" + contatto.getTelefono() + "','" + 
						contatto.getEmail() + "', '" + contatto.getNome() + "')");

				if(ok==1) { verifica++;}

			}

		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				
				statement.close();
				connection.close();

				// Verifico che siano stati aggiunti tutti i contatti e restituisco feedback
				if(verifica==contatti.size()) {
					System.out.println("Sono stati correttamente aggiunti " + verifica + " contatti");
				} else {

					System.out.println("Errore: stati correttamente aggiunti correttamente" + verifica + " contatti su " + contatti.size());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}







	public static void main(String[] args) {
		
		try {

		caricaDb(GestioneRubrica.loadRubricaFromCSV("/Users/Dinamite/Desktop/rubrica.txt", ";"));
		
		} catch (IOException e){
			
			e.printStackTrace();
			
		}
		


	}

}
