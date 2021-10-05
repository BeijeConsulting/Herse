package it.beije.herse.shop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class Shop2 {
	
	final static EntityManager manager = ShopEntityManager.newEntityManager();
	
	
	public static void select(String strQuery) {
		
		Query query = manager.createQuery(strQuery);
		List<Object> objs = query.getResultList();
		
		for(Object ob : objs) {
			System.out.println(ob);
		}
		
		System.out.println();
	}
	
	public static void insert(String str) {
		if(str.equalsIgnoreCase("user")){
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			User user = new User();
			user.setEmail("email@gmail.com");
			user.setName("Pippo");
			user.setPassword("password");
			user.setSurname("pluto");
			System.out.println("PRE " +user);
			manager.persist(user);
			System.out.println("POST " +user);
			transaction.commit();
		}
		if(str.equalsIgnoreCase("order")){
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Order order = new Order();
			order.setUserId(1);
			order.setAmount(40.21);
			order.setDateTime(LocalDateTime.of(2021, 12, 05, 0, 0));
			System.out.println("PRE " +order);
			manager.persist(order);
			System.out.println("POST " +order);
			transaction.commit();
		}
		if(str.equalsIgnoreCase("product")){
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Product product = new Product();
			product.setName("Tovaglia");
			product.setDescription("Lino");
			product.setPrice(12.24);
			product.setQuantity(5);
			System.out.println("PRE " +product);
			manager.persist(product);
			System.out.println("POST " +product);
			transaction.commit();
		}
		if(str.equalsIgnoreCase("orderitem")){
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(1);
			orderItem.setProductId(1);
			orderItem.setSellPrice(12.01);
			System.out.println("PRE " +orderItem);
			manager.persist(orderItem);
			System.out.println("POST " +orderItem);
			transaction.commit();
		}
	}
	
	public static void update() {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		
		String selectUser = "SELECT u FROM User as u";
		
		select(selectUser);
		
		Product product = new Product();
		
		String selectProduct = "SELECT p FROM Product as p";
		
		select(selectProduct);
		
		Order order = new Order();
		
		String selectOrder = "SELECT o FROM Order as o";
		
		select(selectOrder);
		
		OrderItem orderItem = new OrderItem();
		
		String selectOrderItem = "SELECT oi FROM OrderItem as oi";
		
		select(selectOrderItem);
		
//		insert("product");
		
		manager.close();
	}

}
