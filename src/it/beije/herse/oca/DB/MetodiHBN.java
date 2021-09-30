package it.beije.herse.oca.DB;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
public class MetodiHBN {
	protected void leggiContattiHBN() {
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList(); // <--- dentro lista	
		for (Contatto c : contatti) 
			System.out.println(c);
		session.close();
	}

	protected void inserisciContatti() {
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Contatto newContatto = new Contatto();
		newContatto.setCognome("Chiara");
		newContatto.setNome("Collura");
		newContatto.setEmail("c.collura@beije.it");
		System.out.println("contatto PRE : " + newContatto);
		session.save(newContatto);
		System.out.println("contatto POST : " + newContatto);
		transaction.commit();
		session.close();
	}

	protected void updateContatti() {
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Query<Contatto> query4update = session.createQuery("SELECT c FROM Contatto as c WHERE cognome = 'Savino'");
		List<Contatto> contatti4update = query4update.getResultList();
		for (Contatto c : contatti4update) {
			c.setNote("");
			session.save(c);
			System.out.println("c : " + c);
		}
		session.close();
	}


	protected void deleteContatti() {
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Contatto> query4update = session.createQuery("SELECT c FROM Contatto as c WHERE cognome = 'Savino'");
		List<Contatto> contatti4update = query4update.getResultList();
		for (Contatto c : contatti4update) {
			session.remove(c);
			transaction.commit();
		}
		session.close();
	}

}

