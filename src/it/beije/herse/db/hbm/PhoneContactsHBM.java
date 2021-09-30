package it.beije.herse.db.hbm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.herse.file.Contatto;

public class PhoneContactsHBM {
	
	private static final Configuration configuration = new Configuration()
			.configure("it/beije/herse/db/hbm/hibernateVecchia.cfg.xml")
			.addAnnotatedClass(Contatto.class);
	private static final SessionFactory sessionFactory = configuration.buildSessionFactory();
	private static Session session;
	private static Transaction transaction;
	
	private static final List<String> contatto = Arrays.asList("ID", "NOME", "COGNOME", "TELEFONO", "EMAIL", "NOTE");

	public static List<Contatto> readRubricaHBM(){
		List<Contatto> rubrica = new ArrayList<>();
		
		session = sessionFactory.openSession();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		
		rubrica = query.list(); 
		session.close();
		
		return rubrica;
	}
	
	public static List<Contatto> readRubricaHBM(String where, String val){
		List<Contatto> rubrica = new ArrayList<>();
		
		session = sessionFactory.openSession();
		Query<Contatto> query = null;
		
		try {
			Integer id=null;
			if(!contatto.contains(where.toUpperCase())) throw new Exception();
			where = where.toLowerCase();
			if(where.equalsIgnoreCase("id")) {
				id = Integer.parseInt(val);
				query = session.createQuery("SELECT c FROM Contatto as c WHERE "+where+"="+"'"+id+"'");
			}
			else query = session.createQuery("SELECT c FROM Contatto as c WHERE "+where+"="+"'"+val+"'");
			
			rubrica = query.list(); 
		}
		catch(Exception ex){
			System.out.println("NESSUNA COLONNA VALIDA");
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		}
		finally {
			session.close();
		}
		
		return rubrica;
	}
	
	public static List<Contatto> readRubricaHBM(String orderBy){
		List<Contatto> rubrica = new ArrayList<>();
		
		session = sessionFactory.openSession();
		Query<Contatto> query = null;
		
		try {
			if(!contatto.contains(orderBy.toUpperCase())) throw new Exception();
			orderBy = orderBy.toLowerCase();
			query = session.createQuery("SELECT c FROM Contatto as c ORDER BY "+orderBy);
			rubrica = query.list(); 
			
		} 
		catch (Exception e) {
			System.out.println("NESSUNA COLONNA VALIDA");
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		}
		finally {
			session.close();
		}
		
		return rubrica;
	}
	
	public static void printRubrica(List<Contatto> rubrica) {
		for (Contatto c : rubrica) {
			System.out.println(c);
		}
		System.out.println();
	}
	
	public static void insertRubricaHBM(List<Contatto> rubrica) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		for (Contatto c : rubrica) session.save(c);
		
		transaction.commit();
		session.close();
	}
	
	public static void deleteRubricaHBM(String where, String val) {
		for(Contatto c : readRubricaHBM(where, val)) {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			session.remove(c);
			
			transaction.commit();
			session.close();
		}
	}
	
	public static void modifyRubricaHBM(String attr, String attrVal, String where, String whereVal) {
		List<Contatto> rubrica = new ArrayList<>();
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		Query<Contatto> query = null;
		
		try {
			Integer id=null;
			if(!contatto.contains(where.toUpperCase())) throw new Exception();
			where = where.toLowerCase();
			if(where.equalsIgnoreCase("id")) {
				id = Integer.parseInt(whereVal);
				query = session.createQuery("SELECT c FROM Contatto as c WHERE "+where+"="+"'"+id+"'");
			}
			else query = session.createQuery("SELECT c FROM Contatto as c WHERE "+where+"="+"'"+whereVal+"'");
			
			rubrica = query.list();
			
			for(Contatto c : rubrica) {
				switch(attr.toUpperCase()) {
				case "NOME":
					c.setNome(attrVal);
					break;
				case "COGNOME":
					c.setCognome(attrVal);
					break;
				case "EMAIL":
					c.setEmail(attrVal);
					break;
				case "TELEFONO":
					c.setTelefono(attrVal);
					break;
				case "NOTE":
					c.setNote(attrVal);
					break;
				}
				session.save(c);
			}
		}
		catch(Exception ex){
			System.out.println("NESSUNA COLONNA VALIDA");
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		}
		finally {
			transaction.commit();
			session.close();
		}
	}
	
	public static void main(String[] args) {
//		// INSERT
//		List<Contatto> rubrica = new ArrayList<>();
//		rubrica.add(new Contatto("Mario", "Rossi", "333444555", "m.red@gmail.com", ""));
//		rubrica.add(new Contatto("Lucia", "Verdi", "333666888", "", ""));
//		insertRubricaHBM(rubrica);
		
//		// DELETE
//		deleteRubricaHBM("nome", "Federico");
		
//		//UPDATE
//		modifyRubricaHBM("nome", "Federico", "id", "5");
		
		// SELECT *
		printRubrica(readRubricaHBM());
//		// SELECT ... WHERE
//		printRubrica(readRubricaHBM("cognome", "Marrone"));
//		// SELECT * ... ORDER BY
//		printRubrica(readRubricaHBM("cognome"));
	}

}
