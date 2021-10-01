package it.beije.herse.database;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.herse.file.Contatto;

public class RubricaHibernate {

	public static void main(String[] args) {
		//menu();
		
	}

	public static void menu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1: leggi tutti i contatti");
		System.out.println("2: inserisci un contatto");
		System.out.println("3: modifica un contatto");
		System.out.println("4: elimina un contatto");
		System.out.println("0: esci dal programma");

		int opzione = scanner.nextInt();
		
		switch (opzione) {
		case 1:
			readContattoFromDb();
			break;
		case 2:
			System.out.println("Inserisci il contatto che vuoi inserire");
			
			System.out.println("Inserisci il nome");
			String nome = scanner.nextLine();
			System.out.println("Inserisci il cognome");
			String cognome = scanner.nextLine();
			System.out.println("Inserisci il telefono");
			String telefono = scanner.nextLine();
			System.out.println("Inserisci l' email");
			String email = scanner.nextLine();
			System.out.println("Inserisci le note");
			String note = scanner.nextLine();
			Contatto c = new Contatto(nome, cognome, telefono, email, note);
			
			insertContattoInDb(c);
			break;
		case 3:
			System.out.println("Inserisci l'id del contatto che vuoi modificare");
			int id = scanner.nextInt();
			
			System.out.println("Inserisci i dati che vuoi modificare del contatto");
			
			System.out.println("Inserisci il nome");
			String name = scanner.nextLine();
			System.out.println("Inserisci il cognome");
			String surname = scanner.nextLine();
			System.out.println("Inserisci il telefono");
			String phone = scanner.nextLine();
			System.out.println("Inserisci l' email");
			String emails = scanner.nextLine();
			System.out.println("Inserisci le note");
			String notes = scanner.nextLine();
			
			Contatto cUpdate = new Contatto(name, surname, phone, emails, notes);
			updateContattoInDb(id, cUpdate);
			break;
		case 4:
			System.out.println("Inserisci l'id del contatto che vuoi eliminare");
			int idElimina = scanner.nextInt();
			deleteContattoInDb(idElimina);
			
			break;
		case 0:
			scanner.close();
		}
	}

	public static Session openSession() {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		return session;
	}

	public static void insertContattoInDb(Contatto c) {
		Session session = openSession();

		Transaction transaction = session.beginTransaction();
		session.save(c);
		//transaction.commit();
		session.close();
	}

	public static List<Contatto> readContattoFromDb() {
		Session session = openSession();

		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList();

		for (Contatto c : contatti) {
			System.out.println(c);
		}

		session.close();
		return contatti;
	}

	public static void deleteContattoInDb(int id) {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		Contatto c1 = session.get(Contatto.class, id);
		if (c1 != null) {
			session.remove(c1);
		}

		transaction.commit();
		System.out.println("Contatto is removed");
		session.close();
	}

	public static void updateContattoInDb(int id, Contatto nuovoContatto) {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		Query<Contatto> queryUpdate = session.createQuery("SELECT c FROM Contatto as c WHERE id = " + id);
		Contatto contatto = queryUpdate.getResultList().get(0);
		contatto.setNome(nuovoContatto.getNome());
		contatto.setCognome(nuovoContatto.getCognome());
		contatto.setTelefono(nuovoContatto.getTelefono());
		contatto.setEmail(nuovoContatto.getEmail());
		contatto.setNote(nuovoContatto.getNote());

		session.save(contatto);

		transaction.commit();
		session.close();
	}

}
