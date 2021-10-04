package it.beije.herse.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestoreConnessione {



	private static int nome=0;
	private static List<Connessione> connessioni = new ArrayList<Connessione>();


	public static void newConn() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connessione conn = Connessione.nuovaConnessione(nome);

		if(connessioni.get(nome)==null) {
			connessioni.add(conn);
			nome++;
		} else {
			for(Connessione s: connessioni) {
				if(s==null) {
					nome= connessioni.indexOf(s);
					connessioni.add(conn);
					nome++;
				} else {
					System.out.println("ERRORE AGGIUNTA LISTA");
				}
			}
		}

	}

	public static Connessione cerca(int nome) {

		for (Connessione s : connessioni) {
			if(s.getNome()==nome) {
				return s;
			}
		}
		return null;
	}

	public static boolean closeConn(int nome) throws SQLException {

		Connessione s = cerca(nome);
		boolean r=false;
		if(!s.getConn().isClosed()) {

			s.getConn().close();
			connessioni.remove(s);
			r = true;
			GestoreConnessione.nome=nome;

		}
		return r;
	}



	/*  DA CONTINUARE ----> BOZZA PER QUERY DB 
	public static void sottoMenu(int scelta) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Menù Query:\n1:Leggi tutti i contatti.\n2:Inserisci un nuovo contatto.\n0:Esci.");
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
}

}
}
	 */
	private static void stampaLista() {
		for (Connessione conn : connessioni) {
			System.out.println(conn.getNome());
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		boolean esci = false;
		Scanner scanner=new Scanner(System.in);
		// int scelta;
		System.out.println("Benvenuto");
		do {
			if(connessioni.size()==0) {
				System.out.println("Nessuna connessione esistente.\nCreazione di una nuova connessione in corso.\n.\n..\n...\n.... ");
				newConn();
			} else {
				System.out.println("Premi 0 per uscire");
				System.out.println("Premi 1 per eliminare una connessione");
				System.out.println("Premi 2 per utilizzare una connessione già esistente");
				if(connessioni.size()<5) {
					System.out.println("Premi 3 per creare una connessione");
				}
				int i = scanner.nextInt();

				switch(i) {
				case 0: 
					esci=true;
					break;
				case 1:
					break;
				case 2:
					stampaLista();
					break;
				case 3:
					if(connessioni.size()<5) { newConn(); } else {System.out.println("Non è possibile creare una nuova connessione");}
					break;

				default:
					System.out.println("ERRORE, riprova");
					break;

				}

			}
		} while(!esci);
		scanner.close();
	}
}









