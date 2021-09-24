package it.beije.herse.oca.DBPrimo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class InsericiContatti {
	public static void CaricaContatti(Connection connection) {
		int i,n;
		String[] s=new String[5];
		Scanner scanner=new Scanner(System.in);
		PreparedStatement preparedQuery=null;
		try {
			preparedQuery=connection.prepareStatement("INSERT INTO rubrica (nome, cognome, telefono, email, note) VALUES (?,?,?,?,?)");
			System.out.println("Quanti contatti vuoi inserire?");
			n=Integer.parseInt(scanner.next());
			for(i=0;i<n;i++) {
				System.out.println((i+1)+"°Contatto\nNome: ");
				s[0]=scanner.next();
				System.out.println("Cognome: ");
				s[1]=scanner.next();
				System.out.println("Numero: ");
				s[2]=scanner.next();
				System.out.println("E-Mail: ");
				s[3]=scanner.next();
				System.out.println("Note: ");
				s[4]=scanner.next();
				preparedQuery.setString(1, s[0]);
				preparedQuery.setString(2, s[1]);
				preparedQuery.setInt(3, Integer.parseInt(s[2]));
				preparedQuery.setString(4, s[3]);
				preparedQuery.setString(5, s[4]);
				preparedQuery.executeUpdate();
			}
//		}catch(ClassNotFoundException cnfEx) {
//			cnfEx.printStackTrace();
		}catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				preparedQuery.close();
				scanner.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
