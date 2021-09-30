package it.beije.herse.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.herse.file.Contatto;

public class Rubrica {
	
	Configuration config = new Configuration().configure().addAnnotatedClass(Contatto.class);
	
	SessionFactory factory = config.buildSessionFactory();
	
	Session session = factory.openSession();

}
