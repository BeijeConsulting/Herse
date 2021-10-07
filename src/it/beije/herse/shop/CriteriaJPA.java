package it.beije.herse.shop;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

public class CriteriaJPA {
	
	final static EntityManager manager = ShopEntityManager.newEntityManager();
	
	public List<User> getUserWithCriteriaBuilder(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class); //Use to create a query object
		
		Root<User> userRoot = criteriaQuery.from(User.class); // imposta la radice della query
		
		criteriaQuery.select(userRoot.get("id")); // imposta il tipo di elenco dei risultati
		
		CriteriaQuery<User> select = criteriaQuery.select(userRoot); 
		TypedQuery<User> query = manager.createQuery(select); // crea la query e specifica il risultato della query
		
		return query.getResultList();
	}
	
	public List<User> getUserOrderBy(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class); //Use to create a query object
		
		Root<User> userRoot = criteriaQuery.from(User.class); 
		
		criteriaQuery.distinct(true);
		criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get("name")));
		
		criteriaQuery.select(userRoot.get("id")); //select in base all'id dell'utente.
		
		CriteriaQuery<User> select = criteriaQuery.select(userRoot); 
		TypedQuery<User> query = manager.createQuery(select); //
		
		return query.getResultList();
	}
	
	public List<User> getUserWhereClause(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class); //Use to create a query object
		
		Root<User> userRoot = criteriaQuery.from(User.class); 
		
		List name = Arrays.asList(new String[] {"Davide"});
		
		Expression<String> exp = userRoot.get("name");
		Predicate in = exp.in(name);
		criteriaQuery.where(in);
		
		CriteriaQuery<User> select = criteriaQuery.select(userRoot); 
		TypedQuery<User> query = manager.createQuery(select); //
		
		return query.getResultList();
	}
	
	public List<User> getUserGroupBy(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class); //Use to create a query object
		
		Root<User> userRoot = criteriaQuery.from(User.class); 
		
		List name = Arrays.asList(new String[] {"Davide"});
		
		Expression<String> exp = userRoot.get("name");
		Predicate in = exp.in(name);
		criteriaQuery.where(in);
		
		criteriaQuery.groupBy(userRoot.get("surname"));
		
		CriteriaQuery<User> select = criteriaQuery.select(userRoot); 
		TypedQuery<User> query = manager.createQuery(select); //
		
		return query.getResultList();
	}

	public static void main(String[] args) {
		
		System.out.println("Select");
		
		List <User> userList = new CriteriaJPA().getUserWithCriteriaBuilder();
		
		for(User u: userList) {
			System.out.println(u);
		}
		
		System.out.println("Order By");
		
		List <User> userOrderByName = new CriteriaJPA().getUserOrderBy();

		for(User u: userOrderByName) {
			System.out.println(u);
		}
		
		System.out.println("Where");
		
		List <User> userWhere = new CriteriaJPA().getUserWhereClause();

		for(User u: userWhere) {
			System.out.println(u);
		}
		
		System.out.println("Group by");

		List <User> userGroupBy = new CriteriaJPA().getUserWhereClause();

		for(User u: userGroupBy) {
			System.out.println(u);
		}
	}

}
