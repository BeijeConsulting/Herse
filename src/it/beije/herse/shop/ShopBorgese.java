package it.beije.herse.shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public class ShopBorgese {

	static List<User> selectUser() {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		String jpqlSelect =  "SELECT u FROM User AS u";
		Query query = entityManager.createQuery(jpqlSelect);
		return query.getResultList();
	}

	static List<Order> selectOrder() {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		String jpqlSelect = "SELECT o FROM Order AS o";
		Query query = entityManager.createQuery(jpqlSelect);
		return query.getResultList();
	}

	static List<Product> selectProduct() {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		String jpqlSelect =  "SELECT p FROM Product AS p";
		Query query = entityManager.createQuery(jpqlSelect);
		return query.getResultList();
	}

	static List<OrderItem> selectOrderitem() {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		String jpqlSelect =  "SELECT oi FROM OrderItem AS oi";
		Query query = entityManager.createQuery(jpqlSelect);
		return query.getResultList();
	}

	
	static Object selectId(Integer id, String s) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
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

	static List<User> selectUserCriteria(){
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> from = criteriaQuery.from(User.class);
		
		CriteriaQuery<User> select = criteriaQuery.select(from);
		TypedQuery<User> typedQuery = entityManager.createQuery(select);
		return typedQuery.getResultList();
	}
	
	static List<Product> selectProductCriteria(){
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> from = criteriaQuery.from(Product.class);
		
		CriteriaQuery<Product> select = criteriaQuery.select(from);
		TypedQuery<Product> typedQuery = entityManager.createQuery(select);
		return typedQuery.getResultList();
	}
	
	static List<Order> selectOrderCriteria(){
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
		Root<Order> from = criteriaQuery.from(Order.class);
		
		CriteriaQuery<Order> select = criteriaQuery.select(from);
		TypedQuery<Order> typedQuery = entityManager.createQuery(select);
		return typedQuery.getResultList();
		
	}
	
	static List<OrderItem> selectOrderItemCriteria(){
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder.createQuery(OrderItem.class);
		Root<OrderItem> from = criteriaQuery.from(OrderItem.class);
		
		CriteriaQuery<OrderItem> select = criteriaQuery.select(from);
		TypedQuery<OrderItem> typedQuery = entityManager.createQuery(select);
		return typedQuery.getResultList();
		
	}
	
	public static void insertUser(User u) {

		EntityManager entityManager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(u);

		transaction.commit();

	}


	public static void insertProduct(Product p) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(p);

		transaction.commit();


	}

	public static void insertOrder(Order o) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		boolean esiste = false;
		EntityTransaction transaction = entityManager.getTransaction();


		List<User> listUsers = selectUser();

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

	public static void insertOrderItem(OrderItem oI) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		entityManager.persist(oI);
		transaction.commit();
	}



	public static void dettaglioOrdine(Integer orderId) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		Order o = entityManager.find(Order.class, orderId);

		List<OrderItem> orderItemList = selectOrderitem();
		System.out.println("Dettaglio OrderItem e prodotti");
		for(OrderItem orderItem : orderItemList) {
			if(orderItem.getOrderId() == orderId) {
				System.out.println(orderItem);
				Product p = entityManager.find(Product.class, orderItem.getProductId());
				System.out.println("nome prodotto: " + p.getName() + " --- descrizione: " + p.getDescription());
			}
		}
	}

	public static void storicoUser(Integer userId) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		List<Order> orders = selectOrder();
		User u = entityManager.find(User.class, userId);
		System.out.println("Elenco ordini dell'user " +  u.getName() + " " + u.getSurname());
		for(Order o : orders) {
			if(o.getUserId() == userId) {
				dettaglioOrdine(o.getId());
			}
		}
	}

	public static void checkAmount(Integer orderId) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		Order o = entityManager.find(Order.class, orderId);
		Double amount = o.getAmount();
		Double totSellPrice = new Double(0.0);
		List<OrderItem> orderItems = selectOrderitem();
		for(OrderItem i : orderItems) {
			if(i.getOrderId() == orderId)
				totSellPrice += i.getSellPrice();
		}
		if(amount != totSellPrice)
			System.out.println("amount incorretto per l'ordine " + orderId);
		else System.out.println("amount corretto per l'ordine " + orderId);

	}

	public static void creaOrdine(Integer userId, Integer...idProdotti) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
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
		insertOrder(o);
		//		System.out.println(o.getId());
		creaOrderItem(o.getId(), idProdotti);

	}

	private static void creaOrderItem(Integer orderId, Integer...idProdotti) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
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
			insertOrderItem(lista.get(i));
		}
	}

	static void aggiornaQuantityProduct(Integer...idProdotti) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		for(int i =0; i<idProdotti.length; i++) {
			Product p = entityManager.find(Product.class, idProdotti[i]);
			int quantity = p.getQuantity();
			quantity--;
			insertProduct(p);
		}
	}

	static User findUser(Integer idUser) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		return entityManager.find(User.class, idUser);
	}

	static Product findPoduct(Integer idProduct) {
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		return entityManager.find(Product.class, idProduct);
	}

	static void menu() {
		List<Product> prodotti = selectProduct();
		System.out.println("Benvenuto nello Shop Herse");
		System.out.println("Inserisci il tuo id user");
		boolean idUserCorretto = false;
		boolean carrelloCompleto = false;
		Scanner scanner = new Scanner(System.in);
		String st = null;
		Integer idUser = new Integer(0);
		User u = new User();

		while(!idUserCorretto) {
			idUserCorretto = true;
			try {
				st = scanner.next();
				idUser = Integer.valueOf(st);
				u = findUser(idUser);
			}catch(NullPointerException npe) {
				System.out.println("Id inserito non corretto, riprovare");
				idUserCorretto = false;
			}catch(NumberFormatException nfe) {
				System.out.println("Id inserito non corretto, riprovare");
				idUserCorretto = false;
			}

		}
		System.out.println("Benvenuto " + u.getName() + " " + u.getSurname() );
		System.out.println("Ecco una lista di prodotti che puoi acquistare");

		for(Product p : prodotti) 
			System.out.println(p.getId() + "\t" + p.getName() + "\t" + p.getDescription()+ "\t" +  p.getPrice() + p.getQuantity());
		while(!carrelloCompleto) {
			System.out.println("Inserisci l'id dei prodotti che vuoi acquistare ");
			int count = 0;
			Double totale = 0.0;
			st = scanner.next();
			List<Integer> carrello = new ArrayList<Integer>();

			while(!st.equalsIgnoreCase("no")) {
				try {
					Integer idProdotto = Integer.valueOf(st);
					carrello.add(idProdotto);
					Product p = findPoduct(idProdotto);
					totale +=p.getPrice();
					count++;
					System.out.println("Hai selezionato " + count + " prodotti per un totale di " + totale);
					System.out.println("Vuoi inserire un altro prodotto nel carrello?");
				}catch(NullPointerException npe) {
					System.out.println("l'id del prodotto inserito è sbagliato");
				}
				st = scanner.next();
			}
			System.out.println("Hai selezionato " + count + " prodotti per un totale di " + totale);
			System.out.println("Vuoi proseguire con l'ordine?" );
			st = scanner.next();
			if(st.equalsIgnoreCase("si")) {
				Integer [] carrelloIntegers = new Integer[carrello.size()];
				carrelloIntegers = carrello.toArray(carrelloIntegers);
				creaOrdine(idUser, carrelloIntegers);
				carrelloCompleto = true;
			}
			else carrelloCompleto = false;
		}
		scanner.close();
	}



	public static void main(String[] args) {
		
	
		List<User> utenti = selectUserCriteria();
		System.out.println(utenti);
		
		List<Product> prodotti = selectProductCriteria();
		System.out.println(prodotti);
		
		List<Order> ordini = selectOrderCriteria();
		System.out.println(ordini);
		
		List<OrderItem> orderItem = selectOrderItemCriteria();
		System.out.println(orderItem);
		
	}	
}

