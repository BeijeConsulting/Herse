package it.beije.herse.db.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.herse.file.Contatto;

public class PhoneContactsJPA {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-rubrica");
	private static EntityManager entityManager;
	private static EntityTransaction transaction;

	private static final List<String> contatto = Arrays.asList("ID", "NOME", "COGNOME", "TELEFONO", "EMAIL", "NOTE");

	public static List<Contatto> readRubricaJPA(){
		List<Contatto> rubrica = new ArrayList<>();
		
		entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c");
		
		rubrica = query.getResultList(); 
		entityManager.close();
		
		return rubrica;
	}
	
	public static List<Contatto> readRubricaJPA(String where, String val){
		List<Contatto> rubrica = new ArrayList<>();
		
		entityManager = entityManagerFactory.createEntityManager();
		Query query = null;
		
		try {
			Integer id=null;
			if(!contatto.contains(where.toUpperCase())) throw new Exception();
			where = where.toLowerCase();
			if(where.equalsIgnoreCase("id")) {
				id = Integer.parseInt(val);
				query = entityManager.createQuery("SELECT c FROM Contatto as c WHERE "+where+"="+"'"+id+"'");
			}
			else query = entityManager.createQuery("SELECT c FROM Contatto as c WHERE "+where+"="+"'"+val+"'");
			
			rubrica = query.getResultList(); 
		}
		catch(Exception ex){
			System.out.println("NESSUNA COLONNA VALIDA");
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		}
		finally {
			entityManager.close();
		}
		
		return rubrica;
	}
	
	public static List<Contatto> readRubricaJPA(String orderBy){
		List<Contatto> rubrica = new ArrayList<>();
		
		entityManager = entityManagerFactory.createEntityManager();
		Query query = null;
		
		try {
			if(!contatto.contains(orderBy.toUpperCase())) throw new Exception();
			orderBy = orderBy.toLowerCase();
			query = entityManager.createQuery("SELECT c FROM Contatto as c ORDER BY "+orderBy);
			rubrica = query.getResultList(); 
			
		} 
		catch (Exception e) {
			System.out.println("NESSUNA COLONNA VALIDA");
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		}
		finally {
			entityManager.close();
		}
		
		return rubrica;
	}
	
	public static void printRubrica(List<Contatto> rubrica) {
		for (Contatto c : rubrica) {
			System.out.println(c);
		}
		System.out.println();
	}
	
	public static void insertRubricaJPA(List<Contatto> rubrica) {
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
		
		for (Contatto c : rubrica) entityManager.persist(c);
		
		transaction.commit();
		entityManager.close();
	}
	
	public static void deleteRubricaJPA(String where, String val) {
		for(Contatto c : readRubricaJPA(where, val)) {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			entityManager.remove(c);
			
			transaction.commit();
			entityManager.close();
		}
	}
	
	public static void modifyRubricaJPA(String attr, String attrVal, String where, String whereVal) {
		List<Contatto> rubrica = new ArrayList<>();
		
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = null;
		
		try {
			Integer id=null;
			if(!contatto.contains(where.toUpperCase())) throw new Exception();
			where = where.toLowerCase();
			if(where.equalsIgnoreCase("id")) {
				id = Integer.parseInt(whereVal);
				query = entityManager.createQuery("SELECT c FROM Contatto as c WHERE "+where+"="+"'"+id+"'");
			}
			else query = entityManager.createQuery("SELECT c FROM Contatto as c WHERE "+where+"="+"'"+whereVal+"'");
			
			rubrica = query.getResultList();
			
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
				entityManager.persist(c);
			}
		}
		catch(Exception ex){
			System.out.println("NESSUNA COLONNA VALIDA");
			rubrica.add(new Contatto("ERRORE", "ERRORE", "ERRORE", "ERRORE", "ERRORE"));
		}
		finally {
			transaction.commit();
			entityManager.close();
		}
	}
	
	public static void removeDuplicateJPA() {
		for(Contatto c : findDuplicateJPA()) {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			entityManager.remove(c);
			
			transaction.commit();
			entityManager.close();
		}
	}
	
	public static List<Contatto> findDuplicateJPA(){
		List<Contatto> duplicates = new ArrayList<>();
		
		entityManager = entityManagerFactory.createEntityManager();
		
		String duplicateQuery = "select c from Contatto as c group by nome, cognome, telefono, email, note "
			+"having (count(nome) > 1) and (count(cognome) > 1) and (count(telefono) > 1) and "
				+ "(count(email) > 1) and (count(note) > 1)";
		Query query = entityManager.createQuery(duplicateQuery);
		
		duplicates = query.getResultList(); 
		entityManager.close();
		
		return duplicates;
	}
	
	public static void main(String[] args) {
		printRubrica(readRubricaJPA());
	}

}
