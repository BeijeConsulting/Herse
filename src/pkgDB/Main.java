package pkgDB;

import java.sql.*;
import java.util.*;

public class Main {
	
	public static boolean inserimento(Contatto contatto) throws ClassNotFoundException {
		ArrayList<Contatto> rubr = new ArrayList<Contatto>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		String urlDB = "jdbc:mysql://localhost:3306/herse?serverTimezone=CET";
		String sql = "SELECT * FROM herse.rubrica";
		try {
			Statement stat;
			Connection con = DriverManager.getConnection(urlDB, "root", "");
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String telefono = rs.getString("telefono");
				String email = rs.getString("email");
				String note = rs.getString("note");
				rubr.add(new Contatto(nome, cognome, telefono, email, note));
			}
			for(int i = 0; i < rubr.size(); i++) {
				if(contatto == rubr.get(i)) {
					return false;
				}
			}
			String sqli = "INSERT INTO herse.rubrica (nome, cognome, telefono, email, note) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps;
			ps = con.prepareStatement(sqli);
			ps.setString(1, contatto.getNome());
			ps.setString(2, contatto.getCognome());
			ps.setString(3, contatto.getTelefono());
			ps.setString(4, contatto.getEmail());
			ps.setString(5, contatto.getNote());
			ps.executeUpdate();
			ps.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static ArrayList<Contatto> visualizza() throws ClassNotFoundException{
		ArrayList<Contatto> rubr = new ArrayList<Contatto>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		String urlDB = "jdbc:mysql://localhost:3306/herse?serverTimezone=CET";
		String sql = "SELECT * FROM herse.rubrica";
		try {
			Statement stat;
			Connection con = DriverManager.getConnection(urlDB, "root", "");
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String telefono = rs.getString("telefono");
				String email = rs.getString("email");
				String note = rs.getString("note");
				rubr.add(new Contatto(nome, cognome, telefono, email, note));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rubr;
	}
	
	public static Contatto visualizzaContatto(String nom, String cogno) throws ClassNotFoundException {
		Contatto c;
		ArrayList<Contatto> rubr = new ArrayList<Contatto>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		String urlDB = "jdbc:mysql://localhost:3306/herse?serverTimezone=CET";
		String sql = "SELECT * FROM herse.rubrica";
		try {
			Statement stat;
			Connection con = DriverManager.getConnection(urlDB, "root", "");
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String telefono = rs.getString("telefono");
				String email = rs.getString("email");
				String note = rs.getString("note");
				rubr.add(new Contatto(nome, cognome, telefono, email, note));
			}
			for(int i = 0; i < rubr.size(); i++) {
				if(rubr.get(i).getNome().equalsIgnoreCase(nom) && rubr.get(i).getCognome().equalsIgnoreCase(cogno)) {
					c = rubr.get(i);
					return c;
				}
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean modifica(String campo, String modifica) {
		return false;
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int scelta = -1;
		do {
			System.out.println("1 - Inserisci nuovo contatto");
			System.out.println("2 - Visualizza rubrica");
			System.out.println("3 - Visualizza contatto");
//			System.out.println("4 - Modifica contatto");
			System.out.println("0 - Esci");
			scelta = sc.nextInt();
			System.out.println("");
			switch(scelta) {
				case 1:
					System.out.println("Inserisci nome: ");
					String nome = sc.next();
					System.out.println();
					System.out.println("Inserisci cognome: ");
					String cognome = sc.next();
					System.out.println();
					System.out.println("Inserisci telefono: ");
					String telefono = sc.next();
					System.out.println();
					System.out.println("Inserisci email: ");
					String email = sc.next();
					System.out.println();
					System.out.println("Inserisci note: ");
					String note = sc.next();
					System.out.println();
					Contatto c = new Contatto(nome, cognome, telefono, email, note);
					try {
						if(inserimento(c)) {
							System.out.println("Contatto inserito");
						}else {
							System.out.println("Contatto non inserito");
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						ArrayList<Contatto> rubr = visualizza();
						for(int i = 0; i < rubr.size(); i++) {
							System.out.println("Nome: " + rubr.get(i).getNome());
							System.out.println("Cognome: " + rubr.get(i).getCognome());
							System.out.println("Telefono: " + rubr.get(i).getTelefono());
							System.out.println("Email: " + rubr.get(i).getEmail());
							System.out.println("Note: " + rubr.get(i).getNote());
							System.out.println("");
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.println("Inserisci nome: ");
					String name = sc.next();
					System.out.println();
					System.out.println("Inserisci cognome: ");
					String surname = sc.next();
					System.out.println();
					try {
						Contatto contatto = visualizzaContatto(name, surname);
						if(contatto == null) {
							System.out.println("Contatto non presente nella rubrica");
							System.out.println("");
						}else {
							System.out.println("Nome: " + contatto.getNome());
							System.out.println("Cognome: " + contatto.getCognome());
							System.out.println("Telefono: " + contatto.getTelefono());
							System.out.println("Email: " + contatto.getEmail());
							System.out.println("Note: " + contatto.getNote());
							System.out.println("");
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					break;
/*				case 4:
*					
*					break;
*/
				case 0:
					System.out.println("Arrivederci!!!");
					break;
			}
		}while(scelta != 0);
		
	}

}
