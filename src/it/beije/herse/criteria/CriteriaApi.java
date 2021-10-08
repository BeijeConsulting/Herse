package it.beije.herse.criteria;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.beije.herse.file.Contatto;


public class CriteriaApi {

	public static void main(String[] args) {
		   
		   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "herse-rubrica" );
		   EntityManager entitymanager = emfactory.createEntityManager( );
		   CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
		   CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		   Root<Contatto> from = criteriaQuery.from(Contatto.class);

		   //select all records
//		   System.out.println("Select all records");
//		   CriteriaQuery<Object> select = criteriaQuery.select(from); 	//chiamo diversi metodi come query sql
//		  
//		   TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
//		   List<Object> resultlist = typedQuery.getResultList();
//
//		   for(Object o:resultlist) {
//		      Contatto e = (Contatto)o;
//		      System.out.println("ID : " + e.getId() + ", \t Name : " + e.getNome());
//		   }
		   
		   
		   //select with jpql
//		   String jpqlSelect = "SELECT c FROM Contatto as c";
//			Query query = entityManager.createQuery(jpqlSelect);
//			List<Contatto> contatti = query.getResultList();
//
//			for (Contatto c : contatti) {
//				System.out.println(c);
//			}
		   
		   

		   //Ordering the records 
		   System.out.println("Select all records by follow ordering");
		   // CriteriaQuery<Object> select1 = criteriaQuery.select(from).orderBy(criteriaBuilder.asc(from.get("nome")));
		   CriteriaQuery<Object> select1 = criteriaQuery.select(from);
		   select1.orderBy(criteriaBuilder.asc(from.get("nome")));
		   TypedQuery<Object> typedQuery1 = entitymanager.createQuery(select1);
		   List<Object> resultlist1 = typedQuery1.getResultList();

		   for(Object o:resultlist1){
		      Contatto e=(Contatto)o;
		      System.out.println("ID : " + e.getId() + ", \t Name : " + e.getNome());
		   }

		   entitymanager.close( );
		   emfactory.close( );
		   }
}
