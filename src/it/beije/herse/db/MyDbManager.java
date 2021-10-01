package it.beije.herse.db;

import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.herse.file.Contatto;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MyDbManager {

	// Istaura una connessione
	private Session startSession() {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}

	// Chiude una connessione
	private void closeSession(Session session) {
		session.close();
	}

	// Mostra tutti i contatti in rubrica
	public void getTable() {
		Session session = startSession();
		Query<Contatto> allContacts = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = allContacts.list();

		for (Contatto c : contatti) {
			System.out.println(c);
		}

		closeSession(session);
	}

	public void addContact() {
		Session session = startSession();
		// finire di implementare il controllo su id
		if (true) {
			Scanner scanner = new Scanner(System.in);
			Contatto newContatto = new Contatto();
			Transaction transaction = session.beginTransaction();

			System.out.println("Inserire nome: ");
			newContatto.setNome(scanner.nextLine());
			System.out.println("Inserire Cognome: ");
			newContatto.setCognome(scanner.nextLine());
			System.out.println("Inserire Telefono: ");
			newContatto.setTelefono(scanner.nextLine());
			System.out.println("Inserire Email: ");
			newContatto.setEmail(scanner.nextLine());
			System.out.println("Inserire Note: ");
			newContatto.setNote(scanner.nextLine());

			session.save(newContatto);
			transaction.commit();
		} else {

		}
		closeSession(session);
	}

	// Per aggiornare un record della table
	public void updateClause() {
		Session session = startSession();
		String tableName = "Contatto";
		String hql = "UPDATE " + tableName + " SET nome = :nome, cognome = :cognome,"
				+ " telefono = :telefono, email = :email, note = :note WHERE (id = :id)";
		Query query = session.createQuery(hql);
		Scanner scanner = new Scanner(System.in);
		Transaction transaction = session.beginTransaction();

		System.out.println("Inserire id: ");
		query.setParameter("id", scanner.nextInt());
		scanner.nextLine();
		System.out.println("Inserire nome: ");
		query.setParameter("nome", scanner.nextLine());
		System.out.println("Inserire Cognome: ");
		query.setParameter("cognome", scanner.nextLine());
		System.out.println("Inserire Telefono: ");
		query.setParameter("telefono", scanner.nextLine());
		System.out.println("Inserire Email: ");
		query.setParameter("email", scanner.nextLine());
		System.out.println("Inserire Note: ");
		query.setParameter("note", scanner.nextLine());
		System.out.println();

		int result = query.executeUpdate();
		transaction.commit();
		System.out.println("Rows affected: " + result);
		closeSession(session);
	}
	
	public void deleteClause() {
		Session session = startSession();
		String tableName = "Contatto";
		String hql = "DELETE FROM " + tableName + " WHERE (id = :id)";
		Query query = session.createQuery(hql);
		Scanner scanner = new Scanner(System.in);
		Transaction transaction = session.beginTransaction();

		System.out.println("Inserire id da eliminare: ");
		query.setParameter("id", scanner.nextInt());

		int result = query.executeUpdate();
		transaction.commit();
		System.out.println("Rows affected: " + result);
		closeSession(session);
	}

//Serve a resettare l'id se per caso dovesse andare qualcosa fuori posto a causa di un delate
//	public void resetToId() {
//		Session session = startSession();
//		Transaction transaction = session.beginTransaction();
//		String tableName = "Contatto";
//		int newId;
//		
//		Query<Contatto> allContacts = session.createQuery("SELECT c FROM Contatto as c");
//		List<Contatto> contatti = allContacts.list();
//
//		newId = contatti.size() + 1;
//		String hqlSetId = "ALTER TABLE " + tableName + " AUTO_INCREMENT = :newId";
//		Query querySet = session.createQuery(hqlSetId);
//		querySet.setParameter("newId", newId);
//		
//		newId = querySet.executeUpdate();
//		transaction.commit();
//		System.out.println(newId);
//
//		closeSession(session);
//	}
}