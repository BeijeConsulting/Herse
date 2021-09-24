package it.beije.herse.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.beije.herse.mioDb.GestioneRubrica;

import it.beije.herse.file.*;

public class GestoreMioDb {

	public static Connection openConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "abcabcde");

	}



	public static void caricaDb(List<Contatto> contatti) {

		Connection connection = null;
		//	Statement statement = null;
		PreparedStatement prepStat = null;
		int verifica = 0;


		try {
			connection = openConnection();

			System.out.println("connection open ? " + !connection.isClosed());

			// statement = connection.createStatement();

			int ok = 0;
			for(int i=0;i<contatti.size();i++) {

				Contatto contatto = contatti.get(i);


				prepStat=connection.prepareStatement("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES (?,?,?,?,?)");
				prepStat.setString(1, contatto.getNome());
				prepStat.setString(2, contatto.getCognome());
				prepStat.setString(3, contatto.getTelefono());
				prepStat.setString(4, contatto.getEmail());
				prepStat.setString(5, contatto.getNote());

				ok = prepStat.executeUpdate();
				/*	ok = statement.executeUpdate("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES ('"
						+ contatto.getNome() + "','" + contatto.getCognome() + "','" + contatto.getTelefono() + "','" + 
						contatto.getEmail() + "', '" + contatto.getNome() + "')"); */

				if(ok==1) { verifica++;}

			}

		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {

				// statement.close();
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


	public static List<Contatto> newListFromDb() {

		List<Contatto> contatti = new ArrayList<Contatto>();

		Connection connection = null;
		PreparedStatement prepStat = null;
		ResultSet rs = null;
		int verifica = 0;


		try {

			connection = openConnection();

			System.out.println("connection open ? " + !connection.isClosed());



			prepStat = connection.prepareStatement("SELECT * FROM rubrica");
			rs = prepStat.executeQuery();
			// prepStat.setString(1, "Verdi");
			//ok = prepStat.executeQuery();
			while (rs.next()) {

				Contatto c = new Contatto();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setTelefono(rs.getString("telefono"));
				c.setNote(rs.getString("note"));
				contatti.add(c);
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				rs.close();
				prepStat.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contatti;
	}




	public static void main(String[] args) {

		try {
			// DA FILE CSV A DB
			//	caricaDb(GestioneRubrica.loadRubricaFromCSV("/Users/Dinamite/Desktop/rubrica.txt", ";"));

			// DA DB A ARRAYLIST
			List<Contatto> contatti = new ArrayList<>(newListFromDb());

			for(int i=0; i< contatti.size();i++) {

				Contatto c = contatti.get(i);

				System.out.println(c.getNome());
			}

			//} catch (IOException e){

			//	e.printStackTrace();

			//}



		} finally {
			System.out.println("done");
		}

	}
}
