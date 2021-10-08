package it.beije.herse.shop;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ShopApi {

	public static void main(String[] args) {
		selectProducts();

	}
	
	public static void selectUsers() {
		EntityManager manager = ShopEntityManager.newEntityManager();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		
		Root<User> fromUser = criteriaQuery.from(User.class);
		//select records user
		System.out.println("Select all records user");
		CriteriaQuery<User> selectUser = criteriaQuery.select(fromUser);
		TypedQuery<User> typedQueryUser = manager.createQuery(selectUser);
		List<User> resultListUser = typedQueryUser.getResultList();
		
		for (User u: resultListUser) {
			System.out.println(u);
		}
		
		manager.close();
	}
	
	public static void selectProducts() {
		EntityManager manager = ShopEntityManager.newEntityManager();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		
		Root<Product> fromProduct = criteriaQuery.from(Product.class);
		//select records Product
		System.out.println("Select all records Product");
		CriteriaQuery<Product> selectProduct = criteriaQuery.select(fromProduct);
		TypedQuery<Product> typedQueryProduct = manager.createQuery(selectProduct);
		List<Product> resultListProduct = typedQueryProduct.getResultList();
		
		for (Product p: resultListProduct) {
			System.out.println(p);
		}
		
		manager.close();
	}
	
	public static void selectOrders() {
		EntityManager manager = ShopEntityManager.newEntityManager();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
		
		Root<Order> fromOrder = criteriaQuery.from(Order.class);
		//select records Product
		System.out.println("Select all records from Order");
		CriteriaQuery<Order> selectOrder = criteriaQuery.select(fromOrder);
		TypedQuery<Order> typedQueryOrder = manager.createQuery(selectOrder);
		List<Order> resultListOrder = typedQueryOrder.getResultList();
		
		for (Order o: resultListOrder) {
			System.out.println(o);
		}
		
		manager.close();
	}
	
	public static void selectOrderItems() {
		EntityManager manager = ShopEntityManager.newEntityManager();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder.createQuery(OrderItem.class);
		
		Root<OrderItem> fromOrderItem = criteriaQuery.from(OrderItem.class);
		//select records Product
		System.out.println("Select all records from OrderItem");
		CriteriaQuery<OrderItem> selectOrderItem = criteriaQuery.select(fromOrderItem);
		TypedQuery<OrderItem> typedQueryOrderItem = manager.createQuery(selectOrderItem);
		List<OrderItem> resultListOrderItem = typedQueryOrderItem.getResultList();
		
		for (OrderItem oi: resultListOrderItem) {
			System.out.println(oi);
		}
		
		manager.close();
	}
	
	
}
