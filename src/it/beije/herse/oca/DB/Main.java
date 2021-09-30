package it.beije.herse.oca.DB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner scanner=new Scanner(System.in);
		MetodiHBN hbn=new MetodiHBN();
		int scelta;
		final Connection connection=Connect.openConnection();
		System.out.println("Benvenuto");
		do {
			System.out.println("Scegli dal menù:\n1:Leggi tutti i contatti.\n2:Inserisci un nuovo contatto.\n3:Per aggiornare un nuovo contatto.\n4:Per eliminare un contatto.\n0:Esci.");
			scelta=Integer.parseInt(scanner.next());
			switch(scelta) {
			case 1:
				//MetodiJDBC.LeggiContatti(connection);
						hbn.leggiContattiHBN();
				break;
			case 2:
				//MetodiJDBC.CaricaContatti(connection);
				hbn.inserisciContatti();
				break;
			case 3:
				hbn.updateContatti();
				break;
			case 4:
				hbn.deleteContatti();
				break;
			case 0:
			default:
			}
		}while(scelta!=0);
		scanner.close();
		connection.close();
	}

}
