package it.beije.herse.db.jpa.criteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import it.beije.herse.db.jpa.PhoneContactsJPA;
import it.beije.herse.file.Contatto;

public class PhoneContactsCriteriaQuery{
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-rubrica");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static EntityTransaction transaction;

	private static final List<String> contatto = Arrays.asList("ID", "NOME", "COGNOME", "TELEFONO", "EMAIL", "NOTE");

	public static List<Contatto> readRubricaCQ(){
		List<Contatto> rubrica = new ArrayList<>();
		
		entityManager = entityManagerFactory.createEntityManager();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Contatto> cq = cb.createQuery(Contatto.class);
		Root<Contatto> root = cq.from(Contatto.class);
		
		cq.select(root);
		
		Query query = entityManager.createQuery(cq);
		rubrica = query.getResultList(); 
		entityManager.close();
		
		return rubrica;
	}
	
	public static List<Contatto> readRubricaCQ(String where, String val){
		List<Contatto> rubrica = new ArrayList<>();
		
		entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Contatto> cq = cb.createQuery(Contatto.class);
		Root<Contatto> root = cq.from(Contatto.class);
		
		try {
			Integer id=null;
			if(!contatto.contains(where.toUpperCase())) throw new Exception();
			where = where.toLowerCase();
			if(where.equalsIgnoreCase("id")) {
				id = Integer.parseInt(val);
				cq.select(root).where(root.get(where).in(id));
			}
			else cq.select(root).where(root.get(where).in(val));
			
			Query query = entityManager.createQuery(cq);
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
	
	public static List<Contatto> readRubricaCQ(String orderBy){
		List<Contatto> rubrica = new ArrayList<>();
		
		entityManager = entityManagerFactory.createEntityManager();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Contatto> cq = cb.createQuery(Contatto.class);
		Root<Contatto> root = cq.from(Contatto.class);
		
		cq.select(root);
		
		try {
			if(!contatto.contains(orderBy.toUpperCase())) throw new Exception();
			orderBy = orderBy.toLowerCase();
			cq.orderBy(cb.asc(root.get(orderBy)));
			
			Query query = entityManager.createQuery(cq);
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
	
	public static List<Contatto> findDuplicateCQ(){
		List<Contatto> duplicates = new ArrayList<>();
		
		entityManager = entityManagerFactory.createEntityManager();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Contatto> cq = cb.createQuery(Contatto.class);
		Root<Contatto> root = cq.from(Contatto.class);
		
		// SQL
		String duplicateQuery = "select c from Contatto as c group by nome, cognome, telefono, email, note "
			+"having (count(nome) > 1) and (count(cognome) > 1) and (count(telefono) > 1) and "
				+ "(count(email) > 1) and (count(note) > 1)";
		
		// Criteria Query
		cq.select(root);
		
		Expression<?>[] groupByExpression = {root.get("nome"), root.get("cognome"), root.get("telefono"), 
				root.get("email"),  root.get("note")};
		cq.groupBy(groupByExpression);
		
		Predicate[] havingPredicates = {cb.gt(cb.count(root.get("nome")), 1), 
				cb.gt(cb.count(root.get("cognome")), 1), cb.gt(cb.count(root.get("telefono")), 1),
				cb.gt(cb.count(root.get("email")), 1), cb.gt(cb.count(root.get("note")), 1)};
		cq.having(havingPredicates);
		
		Query query = entityManager.createQuery(cq);
		duplicates = query.getResultList(); 
		entityManager.close();
		
		return duplicates;
	}
	
	public static void main(String[] args) {
		PhoneContactsJPA.printRubrica(readRubricaCQ());
		PhoneContactsJPA.printRubrica(readRubricaCQ("nome", "Pippo"));
		PhoneContactsJPA.printRubrica(readRubricaCQ("cognome"));
		PhoneContactsJPA.printRubrica(findDuplicateCQ());
	}
}
