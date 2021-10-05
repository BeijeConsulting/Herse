package it.beije.herse.shop.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.herse.file.Contatto;
import it.beije.herse.shop.Product;
import it.beije.herse.shop.ShopEntityManager;
import it.beije.herse.shop.User;

public class UserManager {
	
	public static void printUsers(List<User> users) {
		for(User u : users) System.out.println(u);
	}
	
	public static void printUsers(User u) {
		System.out.println(u);
	}
	
	public static List<User> selectUsers(){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<User> users = new ArrayList<>();
		
		int id=1;
		User u = manager.find(User.class, id);
		while(u!=null) {
			users.add(u);
			u = manager.find(User.class, ++id);
		}
		
//		Query selectAllQuery = manager.createQuery("SELECT u FROM User as u");
//		users = selectAllQuery.getResultList();
		manager.close();
		
		return users;
	}
	
	public static User selectUsers(Integer id){
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		User u = manager.find(User.class, id);
		
		manager.close();
		
		return u;
	}
	
	public static void insertUsers(List<User> users) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		for (User u : users) manager.persist(u);
		
		transaction.commit();
		manager.close();
	}
	
	public static void updateUsers(String col, String colVal, Integer id) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		User u = manager.find(User.class, id);
		switch(col.toUpperCase()) {
		case "NAME":
			u.setName(colVal);
			break;
		case "SURNAME":
			u.setSurname(colVal);
			break;
		case "EMAIL":
			u.setEmail(colVal);
			break;
		case "PASSWORD":
			u.setPassword(colVal);
			break;
		default:
			System.out.println("No columns found");
			break;
		}
		manager.persist(u);
		
		transaction.commit();
		manager.close();
	}
	
}
