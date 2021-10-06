package it.beije.herse.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.herse.file.Contatto;

public class RubricaJPA {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-rubrica");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
//		Contatto contatto = entityManager.find(Contatto.class, 12);
		//System.out.println(contatto);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
//		//INSERT
//		Contatto newContatto = new Contatto();
//		//newContatto.setId(30);
//		newContatto.setCognome("Brambilla");
//		newContatto.setNome("Mario");
//		newContatto.setEmail("m.brambilla@beije.it");
//		System.out.println("contatto PRE : " + newContatto);
//		entityManager.persist(newContatto);
//		System.out.println("contatto POST : " + newContatto);
		
//		//UPDATE
//		contatto.setCognome("Fumagalli");
//		contatto.setTelefono("43214342");
//		entityManager.persist(contatto); 
		
//		//DELETE
//		entityManager.remove(contatto);
		
		transaction.commit();
//		transaction.rollback();
		
		//Query JPQL
		String jpqlSelect = "SELECT c FROM Contatto as c";
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contatto> contatti = query.getResultList();

		for (Contatto c : contatti) {
			System.out.println(c);
		}
		
		entityManager.close();
	}

}
