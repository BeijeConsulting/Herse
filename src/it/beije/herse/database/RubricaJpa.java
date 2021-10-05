package it.beije.herse.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.herse.file.Contatto;

public class RubricaJpa {

	public static void main(String[] args) {
		//Contatto con = new Contatto("Mario", "Casalini", "1890", "mc@gmail.it", "nuovo contatto");
	}

	public static EntityManager openEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Herse");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
	
	public static void insertContattoJpa(Contatto c) {
		EntityManager entityManager = openEntityManager();
	
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(c);
		
		transaction.commit();
		System.out.println("Contatto has been added");
		entityManager.close();
	}
	
	public static void updateContattoJpa(int id, Contatto nuovo) {
		EntityManager entityManager = openEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Contatto contatto = entityManager.find(Contatto.class, id);
		contatto.setNome(nuovo.getNome());
		contatto.setCognome(nuovo.getCognome());
		contatto.setTelefono(nuovo.getTelefono());
		contatto.setEmail(nuovo.getEmail());
		contatto.setNote(nuovo.getNote());
		
		entityManager.persist(contatto);
		
		transaction.commit();
		System.out.println("Contatto has been updated");
		entityManager.close();
	}
	
	public static void deleteContattoJpa(int id) {
		EntityManager entityManager = openEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Contatto contatto = entityManager.find(Contatto.class, id);
		if (contatto != null) {
			entityManager.remove(contatto);
		}
		
		transaction.commit();
		System.out.println("Contatto is removed");
		entityManager.close();
	}
	
	public static List<Contatto> readContattiJpa() {
		EntityManager entityManager = openEntityManager();
		
		String jpqlSelect = "SELECT c FROM Contatto as c";
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contatto> contatti = query.getResultList();

		for (Contatto c : contatti) {
			System.out.println(c);
		}
		
		entityManager.close();
		return contatti;
	}
}
