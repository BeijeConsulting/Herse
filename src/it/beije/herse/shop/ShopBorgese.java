package it.beije.herse.shop;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class ShopBorgese {



	static List<Object> select(String s, EntityManager entityManager) {
		//		EntityManager entityManager = ShopEntityManager.newEntityManager();
		String jpqlSelect = null;
		if(s.equalsIgnoreCase("user"))
			jpqlSelect = "SELECT u FROM User AS u";
		else if(s.equalsIgnoreCase("order"))
			jpqlSelect = "SELECT o FROM Order AS o";
		else if(s.equalsIgnoreCase("orderitem"))
			jpqlSelect = "SELECT oi FROM OrderItem AS oi";
		else if(s.equalsIgnoreCase("product"))
			jpqlSelect = "SELECT p FROM Product AS p";

		Query query = entityManager.createQuery(jpqlSelect);
		return query.getResultList();

	}

	static Object selectId(Integer id, String s, EntityManager entityManager ) {
		Object o = new Object();
		if(s.equalsIgnoreCase("user"))
			o = entityManager.find(User.class, id);
		else if(s.equalsIgnoreCase("order"))
			o = entityManager.find(Order.class, id);
		else if(s.equalsIgnoreCase("orderitem"))
			o = entityManager.find(OrderItem.class, id);
		else if(s.equalsIgnoreCase("product"))
			o = entityManager.find(Product.class, id);
		return o;
	}
	
	static void insertUser(User u, EntityManager entityManager) {


		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(u);

		transaction.commit();

	}
	
	
	static void insertProduct(Product p, EntityManager entityManager) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(p);
		
		transaction.commit();
		
		
	}
	
	static void insertOrder(Order o, EntityManager entityManager) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(o);
		transaction.commit();
	}
	
	static void insertOrderItem(OrderItem oI, EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(oI);
		transaction.commit();
		
	}
	
	public static void main(String[] args) {

		EntityManager entityManager = ShopEntityManager.newEntityManager();
		
		System.out.println(selectId(1, "user", entityManager));
		
		entityManager.close();
	}

}
