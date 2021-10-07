package it.beije.herse.shop;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Shop3 {
	
	final static EntityManager manager = ShopEntityManager.newEntityManager();
	
	public static List<User> selectUser(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class); 
		
		Root<User> userRoot = criteriaQuery.from(User.class); 
		
		criteriaQuery.select(userRoot.get("id")); 
		
		CriteriaQuery<User> select = criteriaQuery.select(userRoot); 
		TypedQuery<User> query = manager.createQuery(select); 
		
		return query.getResultList();
		
	}
	
	public static List<Product> selectProduct() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class); 
		
		Root<Product> userRoot = criteriaQuery.from(Product.class); 
		
		criteriaQuery.select(userRoot.get("id")); 
		
		CriteriaQuery<Product> select = criteriaQuery.select(userRoot); 
		TypedQuery<Product> query = manager.createQuery(select); 
		
		return query.getResultList();
		
	}
	
	public static List<Order> selectOrder(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class); 
		
		Root<Order> userRoot = criteriaQuery.from(Order.class); 
		
		criteriaQuery.select(userRoot.get("id")); 
		
		CriteriaQuery<Order> select = criteriaQuery.select(userRoot); 
		TypedQuery<Order> query = manager.createQuery(select); 
		
		return query.getResultList();
		
	}
	
	public static List<OrderItem> selectOrderItem(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder.createQuery(OrderItem.class); 
		
		Root<OrderItem> userRoot = criteriaQuery.from(OrderItem.class); 
		
		criteriaQuery.select(userRoot.get("id")); 
		
		CriteriaQuery<OrderItem> select = criteriaQuery.select(userRoot); 
		TypedQuery<OrderItem> query = manager.createQuery(select); 
		
		return query.getResultList();
		
	}
	
	public static int insertOrder(int utente) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Order order = new Order();
		order.setUserId(utente);
		order.setAmount(0.0);
		order.setDateTime(LocalDateTime.now());
		manager.persist(order);
		transaction.commit();
		return order.getId();
	}
	
	public static void insertOrderItem(int order, int product, double price, int quantity) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(order);
		orderItem.setProductId(product);
		orderItem.setSellPrice(price);
		orderItem.setQuantity(quantity);
		manager.persist(orderItem);
		transaction.commit();
	}
	
	
	
	public static void readOrderItem(int orderId){
		String selectFromOrderItem = "SELECT oi FROM OrderItem as oi WHERE orderId = " + orderId;
		Query query = manager.createQuery(selectFromOrderItem);
		
		List<OrderItem> orderItems = query.getResultList();
		
		for(OrderItem o: orderItems) {
			System.out.println("OrderItem collegato all'ordine " + orderId + ": " + o);
		}
		
		System.out.println();
		
		for(OrderItem o: orderItems) {
			int productid = o.getProductId();
			Product product = manager.find(Product.class, productid);
			System.out.println("Prodotto associato all'ordine " + orderId + ": " + product);
		}
		System.out.println();
		
	}
	public static void readOrder(int utente) {
		
		String selectFromOrder = "SELECT o FROM Order as o WHERE userId = " + utente;
		Query query = manager.createQuery(selectFromOrder);
		
		List<Order> orders= query.getResultList();
		
		int cont = 1;
		
		for(Order o: orders) {
			System.out.println("Ordine "+ cont +" dell'utente " + utente + ": " +o);
			System.out.println();
			readOrderItem(o.getId());
			cont++;
		}
	}
	
	public static void main(String[] args) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		List <User> userList = selectUser();

		for(User u: userList) {
			System.out.println(u);
		}

		List <Product> productList = selectProduct();

		for(Product p: productList) {
			System.out.println(p);
		}

		List <Order> orderList = selectOrder();

		for(Order o: orderList) {
			System.out.println(o);
		}

		List <OrderItem> orderItemlList = selectOrderItem();

		for(OrderItem u: orderItemlList) {
			System.out.println(u);
		}
		
		int utente = 0;
		
		while(true) {
			System.out.println("Che utente sei? (Inserisci ilt uo id)");
			Scanner input1 = new Scanner(System.in);
			 utente = input1.nextInt();
			
			User user = manager.find(User.class, utente);
			if(user != null) {
				break;
			} else {
				System.out.println("L'untente non esiste, reinserisci un utente valido");
			}
		}
		
		readOrder(utente); // per vedere lo storico di un utente
		
		ShoBuy2.buyProducts(); // per comprare prodotti
	}

}
