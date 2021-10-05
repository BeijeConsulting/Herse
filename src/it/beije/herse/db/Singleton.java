package it.beije.herse.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Singleton {

	  private static EntityManager istance=null; 
	  
	  static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Herse");
	  static EntityManager entityManager = entityManagerFactory.createEntityManager();
	  
	  private Singleton() {}

	  public static EntityManager getIstance() {
	    if(istance==null) {
	      istance =  entityManager;
	      System.out.println("Aperto");
	    }
	    return istance;
	  }
	  
	  
	  
	  public static EntityManager closeIstance() {
		  entityManager.close();
		  System.out.println("Chiuso");
		  return istance = null;
	  }

	  
}