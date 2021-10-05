package it.beije.herse.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.herse.file.Contatto;

public class RubricaH {
	
	public static Session openSession() {
		
		Configuration config = new Configuration().configure().addAnnotatedClass(Contatto.class);

		SessionFactory factory = config.buildSessionFactory();

		return factory.openSession();
	}
	
	public static boolean closeSession(Session session) {
		
		session.close();
		
		return !(session.isOpen());
		
	}

	public static List<Contatto> getContatti(Session session) {

		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");

		return query.getResultList();

	}
	
	public static void main(String[] args) {
		
		Session session = openSession();

		List<Contatto> contatti = getContatti(session);
		
		for(Contatto c : contatti)
			System.out.println(c);
		
	}

}
