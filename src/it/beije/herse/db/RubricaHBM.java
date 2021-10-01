package it.beije.herse.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.herse.file.Contatto;


public class RubricaHBM {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration().configure();
				//.addAnnotatedClass(Contatto.class);
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		System.out.println("session is open ? " + session.isOpen());
		
		//HQL
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");//SELECT * FROM rubrica
//		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c WHERE cognome = 'Rossi'");//SELECT nome FROM rubrica WHERE cognome = 'Rossi'
		List<Contatto> contatti = query.list();
//		Contatto c = new Contatto();
//		c.setId(rs.getInt("id"));
//		c.setNome(rs.getString("nome"));
//		c.setCognome(rs.getString("cognome"));
//		c.setTelefono(rs.getString("telefono"));
//		c.setEmail(rs.getString("email"));
//		c.setNote(rs.getString("note"));
		
		for (Contatto c : contatti) {
			System.out.println(c);
		}

//		Transaction transaction = session.beginTransaction();
//		//INSERT
//		Contatto newContatto = new Contatto();
////	newContatto.setId(30);
//		newContatto.setCognome("Savino");
//		newContatto.setNome("Vecchia");
//		newContatto.setEmail("s.vecchia@beije.it");
//		System.out.println("contatto PRE : " + newContatto);
//		session.save(newContatto);
//		System.out.println("contatto POST : " + newContatto);

//		transaction.commit();
//		transaction.rollback();
		
		session.close();
	}

}
