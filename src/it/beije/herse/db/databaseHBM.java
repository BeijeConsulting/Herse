package it.beije.herse.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.herse.file.Contatto;

public class databaseHBM {
	
	public static Configuration startConfiguration() {
		
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);
		return configuration;
	}
	
	public static Session startSession() {
		
		Configuration configuration = startConfiguration();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		return session;
	}
	
	public static void dbInsert() throws IOException {
		
		Session session = startSession();
		
		System.out.println("DB prima del nuovo inserimento");
		
		dbReader(session);
		
		session.close();
		
		while(true) {
			System.out.println("Vuoi inserire un nuovo utente in rubrica?");
			Scanner input = new Scanner(System.in);
			String answer = input.next();
			
			Session sessionInsert = startSession();
			
			if(answer.equalsIgnoreCase("si")) {
				sessionInsert.close();
				sessionInsert = startSession();
				Transaction transaction = sessionInsert.beginTransaction();
				Contatto contatto = new Contatto();
				contatto.setCognome();
				contatto.setNome();
				contatto.setTelefono();
				contatto.setEmail();
				contatto.setNote();
				System.out.println("contatto PRE : " + contatto);
				sessionInsert.save(contatto);
				System.out.println("contatto POST : " + contatto);
				transaction.commit();
			} else {
				sessionInsert.close();
				break;
			}
		}
		
		System.out.println("DB dopo l'inserimento");
		
		Session sessionReader = startSession();
		
		dbReader(sessionReader);
		
		sessionReader.close();
		
		Session sessionWrite = startSession();
		
		dbSave(sessionWrite);
		
		sessionWrite.close();
	}
	
	public static void dbReader(Session session) {

		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");

		List <Contatto> contatti = query.getResultList();

		for(Contatto c: contatti) {
			System.out.println(c);
		}
	}
	
	public static void dbSave(Session session) throws IOException {
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");

		List <Contatto> contatti = query.getResultList();
		
		File writeDB = new File("/temp/DatabseSaved.txt");
		
		FileWriter writer = null;
		
		if(writeDB.exists()) {
			writer = new FileWriter(writeDB, true);  //Se esiste non lo sovrascrive.
			writer.write( "\n" + "\n" + "Database Salvato il: " + LocalTime.now() + '\n' + '\n');
		} else {
			writer = new FileWriter(writeDB); //Altrimenti ne crea uno nuovo
			writer.write("Database Salvato il: " + LocalTime.now() + '\n' + '\n');
		}
		
		for(Contatto c: contatti) {
			System.out.println(c);
			writer.write(c.toString());
			writer.write('\n');
		}
		
		writer.flush();
		writer.close();
		
	}

	public static void main(String[] args) throws IOException {
		dbInsert();
	}

}
