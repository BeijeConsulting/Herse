package it.beije.herse.borgese.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.herse.file.Contatto;

public class SingletonJPA {


	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Herse");
	private static EntityManager instance;
	//	private static EntityManager entityManager = entityManagerFactory.createEntityManager();


	public static EntityManager getInstance() {
		if (!instance.isOpen()) {
			System.out.println("apro l'istanza");
			instance  = entityManagerFactory.createEntityManager();
		}
		else 
			System.out.println("istanza già aperta");
		return instance;

	}

	//	public static void closeInstance() {
	//		instance.close();
	//		instance = null;
	//	}


	public static void main(String [] args) {
		EntityManager entityManager = SingletonJPA.getInstance();
		String jpqlSelect = "SELECT c FROM Contatto as c";
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contatto> contatti = query.getResultList();

		for (Contatto c : contatti) {
			System.out.println(c);
		}

		entityManager.close();
	}
}
