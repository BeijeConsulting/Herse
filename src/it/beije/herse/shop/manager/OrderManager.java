package it.beije.herse.shop.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.beije.herse.shop.ShopEntityManager;
import it.beije.herse.shop.Order;

public class OrderManager {

	public static void printOrders(List<Order> orders) {
		for(Order o : orders) System.out.println(o);
	}
	
	public static void printOrders(Order o) {
		System.out.println(o);
	}
	
	public static List<Order> selectOrders(){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<Order> orders = new ArrayList<>();
		
		int id=1;
		Order o = manager.find(Order.class, id);
		while(o!=null) {
			orders.add(o);
			o = manager.find(Order.class, ++id);
		}
		
//		Query selectAllQuery = manager.createQuery("SELECT u FROM Order as u");
//		Orders = selectAllQuery.getResultList();
		manager.close();
		
		return orders;
	}
	
	public static Order selectOrders(Integer id){
		EntityManager manager = ShopEntityManager.newEntityManager();

		Order o = manager.find(Order.class, id);
		
		manager.close();
		
		return o;
	}
	
	public static void insertOrders(List<Order> orders) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		for (Order o : orders) manager.persist(o);
		
		transaction.commit();
		manager.close();
	}
	
	public static void updateOrders(String col, String colVal, Integer id) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		Order u = manager.find(Order.class, id);
		switch(col.toUpperCase()) {
		case "USER_ID":
			u.setUserId(Integer.valueOf(colVal));
			break;
		case "AMOUNT":
			u.setAmount(Double.valueOf(colVal));
			break;
		case "CREATION_DATETIME":
			u.setDateTime(LocalDateTime.parse(colVal));
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
