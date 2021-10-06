package it.beije.herse.shop.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.herse.file.Contatto;
import it.beije.herse.shop.Order;
import it.beije.herse.shop.Product;
import it.beije.herse.shop.ShopEntityManager;
import it.beije.herse.shop.ShopVecchia;
import it.beije.herse.shop.User;

public class UserManager {
	
	public static void printUsers(List<User> users) {
		for(User u : users) System.out.println(u);
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
	
	public static List<User> selectUsers(Integer id){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<User> users = new ArrayList<>();
		
		User u = manager.find(User.class, id);
		users.add(u);
		
		manager.close();
		
		return users;
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
	
	public static void printOrderHistory(Integer id) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		User u = manager.find(User.class, id);
		String orderHistoryQuery = "SELECT o FROM Order as o WHERE userId= "+id;
		List<Order> orderHistory = manager.createQuery(orderHistoryQuery).getResultList();
		
		System.out.println("USER: "+u);
		System.out.println("ORDER: ");
		OrderManager.printOrders(orderHistory);
		
		manager.close();
	}
	
	public static Boolean loginUser(String email, String password) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		String loginQuery = "SELECT u FROM User as u WHERE email= '"+email+"' AND password= '"+password+"'";
		List<User> userList = manager.createQuery(loginQuery).getResultList();;
		
//		System.out.println("TEST PRINT");
//		for(User u : userList) System.out.println(u);
		
		manager.close();
		
		if(userList.size()==0) return false;
		else if(userList.size()>1) {
			System.out.println("ERROR");
			System.exit(0);
		}
		User u = userList.get(0);
		if(u.getName()!=null && u.getSurname()!=null) System.out.println("WELCOME "+u.getName()+" "+u.getSurname());
		else System.out.println("WELCOME "+u.getEmail());
		ShopVecchia.setLoggedUser(u);
		return true;
	}
}
