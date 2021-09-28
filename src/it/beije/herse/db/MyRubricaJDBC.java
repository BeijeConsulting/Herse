package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.DateTimeAtCompleted;

import it.beije.herse.file.Contatto;

public class MyRubricaJDBC {

	public static void main(String[] args) {
		List<Contatto> contattiLoad = new ArrayList<Contatto>();
		List<Contatto> contattiWrite = new ArrayList<Contatto>();
		/*numero di connection che puo creare il mio server*/
		int count = 0;
		boolean infiniteloop = true;
		LocalTime start = LocalTime.now();
		
		while (infiniteloop) {
			try {
			getContatti();
			count++;
			}catch (Throwable e) {
				//fixare il tempo
				LocalTime end = LocalTime.now();
				LocalTime delta = LocalTime.now();
				System.out.println(count);
				System.out.println();
				infiniteloop = false;
			}
		}

		// contattiLoad = getContatti();
		// stampaContatti(contattiLoad);

		// contattiWrite = createContatto();

		// System.out.println("Added ? "+addContatti(contattiWrite));
		// System.out.println(updateContatto(13));
		// getContatto(contattiLoad);
	}

	private static void getContatto(List<Contatto> contattiLoad) {
		Scanner input = new Scanner(System.in);
		List<Contatto> contatti = new ArrayList<Contatto>();
		System.out.print("inserire nome: ");
		String nome = input.nextLine();
		System.out.print("inserire cognome: ");
		String cognome = input.nextLine();

		for (Contatto contatto : contattiLoad) {
			if (contatto.getNome().equalsIgnoreCase(nome) || contatto.getCognome().equalsIgnoreCase(cognome)) {
				contatti.add(contatto);
			}
		}
		System.out.println();
		stampaContatti(contatti);
	}

	private static boolean updateContatto(int id) {
		boolean returnvalue = true;
		String dbLength = "SELECT * FROM rubrica;";
		String query = "UPDATE rubrica SET nome = ?, cognome = ?, telefono = ?, email = ?, note = ? WHERE (id = ?);";
		Scanner input = new Scanner(System.in);
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/herse?serverTimezone=CET&" + "user=root&password=nManfredi");
			System.out.println("connected? " + connection.isValid(1000));

			preparedStatement = connection.prepareStatement(dbLength);
			resultSet = preparedStatement.executeQuery();

			int count = 0;
			while (resultSet.next())
				count++;
			if (id <= count) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(6, id);
				System.out.println("Inserire nuovo nome (non scrivere nulla per mantenerlo uguale)");
				String nome = input.nextLine();
				preparedStatement.setString(1, nome);
				System.out.println("Inserire nuovo cognome (non scrivere nulla per mantenerlo uguale)");
				String cognome = input.nextLine();
				preparedStatement.setString(2, cognome);
				System.out.println("Inserire nuovo telefono (non scrivere nulla per mantenerlo uguale)");
				String telefono = input.nextLine();
				preparedStatement.setString(3, telefono);
				System.out.println("Inserire nuovo email (non scrivere nulla per mantenerlo uguale)");
				String email = input.nextLine();
				preparedStatement.setString(4, email);
				System.out.println("Inserire nuovo note (non scrivere nulla per mantenerlo uguale)");
				String note = input.nextLine();
				preparedStatement.setString(5, note);
				preparedStatement.executeUpdate();
			} else {
				System.out.println("id out of bound");
				returnvalue = false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					preparedStatement.close();
				}
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnvalue;

	}

	private static List<Contatto> createContatto() {
		boolean goNext = true;
		List<Contatto> contatti = new ArrayList<Contatto>();
		Scanner input = new Scanner(System.in);

		do {
			Contatto contatto = new Contatto();
			System.out.print("Inserire nome: ");
			contatto.setNome(input.nextLine());
			System.out.print("Inserire Cognome: ");
			contatto.setCognome(input.nextLine());
			System.out.print("Inserire Telefono: ");
			contatto.setTelefono(input.nextLine());
			System.out.print("Inserire Email: ");
			contatto.setEmail(input.nextLine());
			System.out.print("Inserire Note: ");
			contatto.setNote(input.nextLine());
			contatti.add(contatto);
			System.out.print("Continue (0 -> yes | 1 -> no) ?");
			if (input.nextInt() == 1) {
				goNext = false;
			}
			input.nextLine();
		} while (goNext);

		return contatti;

	}

	public static void stampaContatti(List<Contatto> contatti) {
		for (Contatto contatto : contatti) {
			System.out.println("ID : " + contatto.getId());
			System.out.println("NOME : " + contatto.getNome());
			System.out.println("COGNOME : " + contatto.getCognome());
			System.out.println("TELEFONO : " + contatto.getTelefono());
			System.out.println("EMAIL : " + contatto.getEmail());
			System.out.println("NOTE : " + contatto.getNote());
			System.out.println();
		}
	}

	public static List<Contatto> getContatti() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		List<Contatto> contatti = new ArrayList<Contatto>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/herse?serverTimezone=CET&" + "user=root&password=nManfredi");
			System.out.println("connected? " + connection.isValid(1000));

			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM rubrica");

			while (resultSet.next()) {
				Contatto contatto = new Contatto();
				contatto.setId(resultSet.getInt("id"));
				contatto.setNome(resultSet.getString("nome"));
				contatto.setCognome(resultSet.getString("cognome"));
				contatto.setTelefono(resultSet.getString("telefono"));
				contatto.setEmail(resultSet.getString("email"));
				contatto.setNote(resultSet.getString("note"));
				contatti.add(contatto);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
				resultSet.close();
				statement.close();
				// connection.close();
			
				
			
		}
		return contatti;
	}

	public static boolean addContatti(List<Contatto> contatti) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/herse?serverTimezone=CET&" + "user=root&password=nManfredi");
			System.out.println("connected? " + connection.isValid(1000));

			preparedStatement = connection.prepareStatement("insert into rubrica values(null,?,?,?,?,?);");
			for (int i = 0; i < contatti.size(); i++) {
				preparedStatement.setString(1, contatti.get(i).getNome());
				preparedStatement.setString(2, contatti.get(i).getCognome());
				preparedStatement.setString(3, contatti.get(i).getTelefono());
				preparedStatement.setString(4, contatti.get(i).getEmail());
				preparedStatement.setString(5, contatti.get(i).getNote());
				preparedStatement.executeUpdate();
			}
//			statement.execute("insert into rubrica\n" + "values(" + null + ", '" + contatto.getNome() + "' , '"
//					+ contatto.getCognome() + "' , '" + contatto.getTelefono() + "' , '" + contatto.getEmail() + "' , '"
//					+ contatto.getNote() + "');");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					preparedStatement.close();
				}
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}


