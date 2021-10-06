package it.beije.herse.shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public class ShopBorgese {

	static List<User> selectUser(EntityManager entityManager) {
		String jpqlSelect =  "SELECT u FROM User AS u";
		Query query = entityManager.createQuery(jpqlSelect);
		return query.getResultList();
	}

	static List<Order> selectOrder(EntityManager entityManager) {
		String jpqlSelect = "SELECT o FROM Order AS o";
		Query query = entityManager.createQuery(jpqlSelect);
		return query.getResultList();
	}

	static List<Product> selectProduct(EntityManager entityManager) {
		String jpqlSelect =  "SELECT p FROM Product AS p";
		Query query = entityManager.createQuery(jpqlSelect);
		return query.getResultList();
	}

	static List<OrderItem> selectOrderitem(EntityManager entityManager) {
		String jpqlSelect =  "SELECT oi FROM OrderItem AS oi";
		Query query = entityManager.createQuery(jpqlSelect);
		return query.getResultList();
	}

	//QUESTO METODO è STATO FATTO IL 5 OTTOBRE, LO LASCIO QUI MA NON MI PIACE, NON LO USO E NE CREERO' UNO PER OGNI BIN
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

	public static void insertUser(User u, EntityManager entityManager) {


		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(u);

		transaction.commit();

	}


	public static void insertProduct(Product p, EntityManager entityManager) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(p);

		transaction.commit();


	}

	public static void insertOrder(Order o, EntityManager entityManager) {

		boolean esiste = false;
		EntityTransaction transaction = entityManager.getTransaction();


		List<User> listUsers = selectUser(entityManager);

		for(User u : listUsers) {
			if(u.getId()==o.getUserId())
				esiste  = true;
		}
		if(esiste) {
			transaction.begin();
			entityManager.persist(o);
			transaction.commit();
		}
		else System.out.println("L'userId inserito non esiste");
	}

	public static void insertOrderItem(OrderItem oI, EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.persist(oI);
		transaction.commit();
	}



	public static void dettaglioOrdine(Integer orderId, EntityManager entityManager) {
		Order o = entityManager.find(Order.class, orderId);

		List<OrderItem> orderItemList = selectOrderitem(entityManager);
		System.out.println("Dettaglio OrderItem e prodotti");
		for(OrderItem orderItem : orderItemList) {
			if(orderItem.getOrderId() == orderId) {
				System.out.println(orderItem);
				Product p = entityManager.find(Product.class, orderItem.getProductId());
				System.out.println("nome prodotto: " + p.getName() + " --- descrizione: " + p.getDescription());
			}
		}
	}

	public static void storicoUser(Integer userId, EntityManager entityManager) {
		List<Order> orders = selectOrder(entityManager);
		User u = entityManager.find(User.class, userId);
		System.out.println("Elenco ordini dell'user " +  u.getName() + " " + u.getSurname());
		for(Order o : orders) {
			if(o.getUserId() == userId) {
				dettaglioOrdine(o.getId(), entityManager);
			}
		}
	}

	public static void checkAmount(Integer orderId, EntityManager entityManager) {
		Order o = entityManager.find(Order.class, orderId);
		Double amount = o.getAmount();
		Double totSellPrice = new Double(0.0);
		List<OrderItem> orderItems = selectOrderitem(entityManager);
		for(OrderItem i : orderItems) {
			if(i.getOrderId() == orderId)
				totSellPrice += i.getSellPrice();
		}
		if(amount != totSellPrice)
			System.out.println("amount incorretto per l'ordine " + orderId);
		else System.out.println("amount corretto per l'ordine " + orderId);

	}

	public static void creaOrdine(Integer userId,  EntityManager entityManager, Integer...idProdotti) {
		Double amount = new Double(0.0);
		boolean existProduct = true;


		for(int i = 0; i<idProdotti.length; i++) {
			Product p = entityManager.find(Product.class, idProdotti[i]);
			amount += p.getPrice();
		}
		Order o = new Order();
		o.setAmount(amount);
		o.setDateTime(LocalDateTime.now());
		o.setUserId(userId);
		insertOrder(o, entityManager);
		
		//		System.out.println(o.getId());
		creaOrderItem(o.getId(), entityManager, idProdotti);

	}

	private static void creaOrderItem(Integer orderId, EntityManager entityManager, Integer...idProdotti) {
		List<OrderItem> lista = new ArrayList<>();
		for(int i = 0; i<idProdotti.length; i++) {
			Product p = entityManager.find(Product.class, idProdotti[i]);
			OrderItem item = new OrderItem();
			item.setProductId(idProdotti[i]);
			item.setOrderId(orderId);
			item.setSellPrice(p.getPrice());
			item.setQuantity(1);
			lista.add(item);
		}
		for(int i =0; i<lista.size(); i++) {
			for(int j=i+1; j<lista.size(); j++)
				if(lista.get(i).getProductId() == lista.get(j).getProductId()) {
					int quantity = lista.get(i).getQuantity();
					lista.get(i).setQuantity(++quantity);
					lista.remove(j);
				}

		}
		for(int i = 0; i<lista.size(); i++) {
			insertOrderItem(lista.get(i), entityManager);
		}


	}



	public static void main(String[] args) {

		EntityManager entityManager = ShopEntityManager.newEntityManager();
		
		creaOrdine(1, entityManager, 1,1,3);

		entityManager.close();
	}

}
