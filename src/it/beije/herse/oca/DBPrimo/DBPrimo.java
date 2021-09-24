package it.beije.herse.oca.DBPrimo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class DBPrimo {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner scanner=new Scanner(System.in);
		int scelta;
		final Connection connection=Connect.openConnection();
		System.out.println("Benvenuto");
		do {
			System.out.println("Scegli dal menù:\n1:Leggi tutti i contatti.\n2:Inserisci un nuovo contatto.\n0:Esci.");
			scelta=Integer.parseInt(scanner.next());
			switch(scelta) {
			case 1:
				LeggiContatti.LeggiContatti(connection);
				break;
			case 2:
				InsericiContatti.CaricaContatti(connection);
				break;
			case 0:
			default:
			}
		}while(scelta!=0);
		scanner.close();
		connection.close();
	}

}
