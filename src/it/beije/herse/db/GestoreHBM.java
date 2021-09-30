package it.beije.herse.db;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.herse.file.Contatto;

public class GestoreHBM {


	public static Session connessione() {

		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		System.out.println("session is open ? " + session.isOpen());

		return session;

	}

	public static void cerca(String query) {

		Session sessione = connessione();


		Query<Contatto> richiesta = sessione.createQuery(query);
		List<Contatto> contatti = richiesta.getResultList();

		for(Contatto c : contatti) {
			System.out.println(c);
		}

		sessione.close();


	}

	public static void inserisci(String nome, String cognome, String telefono, String email, String note) {

		Session sessione = connessione();

		Contatto u = new Contatto(nome,cognome,telefono, email,note);
		Transaction transaction = sessione.beginTransaction();
		sessione.save(u);
		transaction.commit();

		sessione.close();
	}

	public static void aggiorna(String parametro, String nuovo, int id) {

		Session sessione = connessione();
		Transaction transaction = sessione.beginTransaction();

		Query<Contatto> query4update = sessione.createQuery("SELECT c FROM Contatto as c WHERE id ="+ id);

		Contatto contatto = query4update.getResultList().get(0);

		if(query4update.getResultList().size()>1) System.out.println("NON DOVREBBE ESSERE POSSIBILE");

		switch(parametro) {
		case "nome":
			contatto.setNome(nuovo);
			break;
		case "cognome":
			contatto.setCognome(nuovo);
			break;
		case "telefono":
			contatto.setTelefono(nuovo);
			break;
		case "email":
			contatto.setEmail(nuovo);
			break;
		case "note":
			contatto.setNote(nuovo);
			break;
		}

		sessione.save(contatto);
		transaction.commit();
		sessione.close();

	}

	public static void aggiorna(String parametro, String nuovo, String query) {

		Session sessione = connessione();
		Transaction transaction = sessione.beginTransaction();

		Query<Contatto> query4update = sessione.createQuery("SELECT c FROM Contatto as c WHERE " + query);

		Contatto contatto = query4update.getResultList().get(0);

		if(query4update.getResultList().size()>1) System.out.println("NON DOVREBBE ESSERE POSSIBILE");

		switch(parametro) {
		case "nome":
			contatto.setNome(nuovo);
			break;
		case "cognome":
			contatto.setCognome(nuovo);
			break;
		case "telefono":
			contatto.setTelefono(nuovo);
			break;
		case "email":
			contatto.setEmail(nuovo);
			break;
		case "note":
			contatto.setNote(nuovo);
			break;
		}

		sessione.save(contatto);
		transaction.commit();
		sessione.close();

	}
	
	
	public static void elimina(String parametro, String valore) {
		
		Session sessione = connessione();
		Transaction transaction = sessione.beginTransaction();
		
		Query<Contatto> richiesta = sessione.createQuery("SELECT c FROM Contatto as c WHERE "+ parametro + "='"+ valore +"'");
		List<Contatto> contatti = richiesta.getResultList();

		for(Contatto c : contatti) {
			
			sessione.remove(c);
		}
		transaction.commit();
		sessione.close();
	}


	public static void main(String[] args) {


		// cerca("SELECT c FROM Contatto as c WHERE cognome LIKE 'Sowlo'");

		// inserisci("Samuele","Fraioli","3347937725", "samuele.fraioli@studenti.unimi.it","nulla da dichiarare");

		// aggiorna("telefono","3347937726",9);
		// aggiorna("note", "contatto duplicato", "telefono = '3347937725' and cognome = 'Fraioli'");

		elimina("telefono", "3347937725");
		cerca("SELECT c FROM Contatto as c");

	}

}
