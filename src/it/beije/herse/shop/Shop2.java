package it.beije.herse.shop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class Shop2 {
	
	final static EntityManager manager = ShopEntityManager.newEntityManager();
	
	
	public static List<Object> select(String strQuery) {
		
		Query query = manager.createQuery(strQuery);
		List<Object> objs = query.getResultList();
		
		for(Object ob : objs) {
			System.out.println(ob);
		}
		
		System.out.println();
		return objs;
	}
	
	public static List<Object> select(Query query) {
		List<Object> objs = query.getResultList();
		
		for(Object ob : objs) {
			System.out.println(ob);
		}
		
		System.out.println();
		return objs;
	}
	
	public static Object insert(String str) {
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
			return user;
		}
		if(str.equalsIgnoreCase("order")){
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Order order = new Order();
			order.setUserId(1);
			order.setAmount(40.21);
			order.setDateTime(LocalDateTime.now());
//			System.out.println("PRE " +order);
			manager.persist(order);
//			System.out.println("POST " +order);
			transaction.commit();
			return order;
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
			return product;
		}
		if(str.equalsIgnoreCase("orderitem")){
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(1);
			orderItem.setProductId(1);
			orderItem.setSellPrice(0.0);
			orderItem.setQuantity(0);
			System.out.println("PRE " +orderItem);
			manager.persist(orderItem);
			System.out.println("POST " +orderItem);
			transaction.commit();
			return orderItem;
		}
		return null;
	}
	
	public static void insert(EntityTransaction transaction, Object obj) {
		System.out.println(obj);
		manager.persist(obj);
		System.out.println("classe" + obj.getClass());
		System.out.println("POST " +obj);
		transaction.commit();
	}
	
	
	public static void readOrder(Order order, int idUtente) {
		String selectFromOrder = "SELECT o FROM Order as o WHERE userId = " + idUtente;
		Query query = manager.createQuery(selectFromOrder);
		List<Order> objs = query.getResultList();
		
		int orderId = 0;
		
		int cont = 1;

		for(Order o : objs) {
			orderId= o.getId();
			System.out.println("Ordine "+ cont +" dell'utente " + idUtente + ": " +o);
			System.out.println();
			readOrderItem(orderId);
			cont++;
		}
		
	}
	
	public static void readOrderItem(int orderId) {
		OrderItem orderItem = new OrderItem();
		String selectFromOrderItem = "SELECT oi FROM OrderItem as oi WHERE orderId = " + orderId;
		Query query = manager.createQuery(selectFromOrderItem);
		List<OrderItem> objs = query.getResultList();
		
		for(OrderItem o: objs) {
			System.out.println("OrderItem collegato all'ordine " + orderId + ": " + o);
		}
		for(OrderItem o: objs) {
			int productid = o.getProductId();
			Product product = manager.find(Product.class, productid);
			System.out.println("Prodotto associato all'ordine " + orderId + ": " + product);
			orderItemSetPrice(objs, product);
			orderSetAmount(objs, orderId);
		}
		System.out.println();
		
		
	}
	
	public static void orderSetAmount(List<OrderItem> orderItem, int orderId) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		double total= 0;
		int quantity = 0;
		
		int productid = 0;
		
		for(OrderItem oi: orderItem) {
			total += oi.getSellPrice();
			quantity = oi.getQuantity();
			total = total * quantity;
			
			//updateProduct(oi.getProductId(), quantity); 
			//	Bisogna capire dove metterlo, percè se no ogni volta che si guarda quest'orine 
			//  si toglie la quantità.
		}
		
		String selectFromOrder = "SELECT o FROM Order as o WHERE id = " + orderId;
		Query query = manager.createQuery(selectFromOrder);
		List<Order> objs = query.getResultList();
		
		for(Order o: objs) {
			o.setAmount(total);
			manager.persist(o);
		}

		transaction.commit();
	}
	
	public static void orderItemSetPrice(List<OrderItem> orderItem, Product product) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		double price = 0;
		
		price = product.getPrice();
		
		
		for(OrderItem oi: orderItem) {
			oi.setSellPrice(price);
			manager.persist(oi);
		}

		transaction.commit();
	}
	
	public static void updateProduct(Integer productId, int quantity) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		String selectProduct = "SELECT p FROM Product as WHERE id = " + productId;
		Query query = manager.createQuery(selectProduct);
		List<Product> product = query.getResultList();
		
		for(Product p: product) {
			int q = p.getQuantity() - quantity;
			p.setQuantity(q);
			manager.persist(product);
			transaction.commit();
			System.out.println("product con quantity cambiata: " + p);
		}
		
	}

	public static void main(String[] args) {
		
//		String selectUser = "SELECT u FROM User as u";
//		select(selectUser);
//		
//		String selectProduct = "SELECT p FROM Product as p";
//		select(selectProduct);
//		
//		String selectOrder = "SELECT o FROM Order as o";
//		select(selectOrder);
//
//		String selectOrderItem = "SELECT oi FROM OrderItem as oi";
//		select(selectOrderItem);
		
		insert("product");
		
		String selectU = "SELECT u FROM User as u";
		Query queryUser = manager.createQuery(selectU);
		select(queryUser);
		
		String selectP = "SELECT p FROM Product as p";
		Query queryProduct = manager.createQuery(selectP);
		select(queryProduct);
		
		String selectO = "SELECT o FROM Order as o";
		Query queryOrder = manager.createQuery(selectO);
		select(queryOrder);
		
		String selectOI = "SELECT oi FROM OrderItem as oi";
		Query queryOrderItem = manager.createQuery(selectOI);
		select(queryOrderItem);
		
//		EntityTransaction transactionUser = manager.getTransaction();
//		transactionUser.begin();
//		User user = new User();
//		user.setEmail("email@gmail.com");
//		user.setName("Pippo");
//		user.setPassword("password");
//		user.setSurname("pluto");
//		System.out.println("PRE " +user);
//		insert(transactionUser, user);
//		
//		EntityTransaction transactionOrder = manager.getTransaction();
//		transactionOrder.begin();
//		Order order = new Order();
//		order.setUserId(1);
//		order.setAmount(40.21);
//		order.setDateTime(LocalDateTime.of(2021, 12, 05, 0, 0));
//		System.out.println("PRE " +order);
//		insert(transactionOrder, order);
//		
//		EntityTransaction transactionProduct = manager.getTransaction();
//		transactionProduct.begin();
//		Product product = new Product();
//		product.setName("Tavolo");
//		product.setDescription("Legno");
//		product.setPrice(125.34);
//		product.setQuantity(7);
//		System.out.println("PRE " +product);
//		insert(transactionProduct, product);
		
//		OrderItem orderItem = new OrderItem();
//		EntityTransaction transactionOrderItem = manager.getTransaction();
//		transactionOrderItem.begin();
//		orderItem.setOrderId(2);
//		orderItem.setProductId(2);
//		orderItem.setSellPrice(100.01);
//		orderItem.setQuantity(2);
//		System.out.println("PRE " +orderItem);
//		insert(transactionOrderItem, orderItem);
//		
//		
		System.out.println("Inserisci un id utente");
		Scanner input = new Scanner(System.in);
		int idUtente = input.nextInt();
		
		Order o = new Order();
		
		readOrder(o, idUtente);
		
		manager.close();
	}

	

}
