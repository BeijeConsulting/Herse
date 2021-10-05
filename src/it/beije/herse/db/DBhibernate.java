package it.beije.herse.db;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import it.beije.herse.file.Contatto;

public class DBhibernate {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		System.out.println("session is open ? " + session.isOpen() );
		
		Transaction transaction = session.beginTransaction();
		
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto AS c");
		
		List<Contatto> contatti = query.getResultList();
		
		for(Contatto c : contatti) System.out.println(c);
//		
//		contatti.get(0).setCognome("FASULLO");
//		contatti.get(0).setNome("FASULLO");
//		contatti.get(0).setEmail("FASULLO");
//		contatti.get(0).setNote("FASULLO");
//		System.out.println("\n\n\n");
//		System.out.println(contatti.get(0));
//		
//		
//		session.save(contatti.get(0));

		

		
		
	}

}
