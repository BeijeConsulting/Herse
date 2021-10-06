package it.beije.herse.shop.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.herse.shop.ShopEntityManager;
import it.beije.herse.shop.Order;
import it.beije.herse.shop.OrderItem;
import it.beije.herse.shop.Product;

public class OrderManager {

	public static void printOrders(List<Order> orders) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		for(Order o : orders) {
			System.out.println(o);
			System.out.println("ITEMS: ");
			
			Query itemsQuery = manager.createQuery("Select o From OrderItem as o Where orderId = "+o.getId());
			List<OrderItem> items = itemsQuery.getResultList();
			for(OrderItem i : items) {
				Product p = manager.find(Product.class, i.getProductId());
				System.out.println("--> "+i+
						" PRODUCT: {name: "+p.getName()+", description: "+p.getDescription()+"}");
			}
			
			System.out.println();
		}
		
		manager.close();
	}
	
	public static List<Order> selectOrders(){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<Order> orders = new ArrayList<>();
		
		
		// Non funziona: id passa da 1 a 3
//		int id=1;
//		Order o = manager.find(Order.class, id);
//		while(o!=null) {
//			orders.add(o);
//			id++;
//			o = manager.find(Order.class, id);
//		}
		
		Query selectAllQuery = manager.createQuery("SELECT u FROM Order as u");
		orders = selectAllQuery.getResultList();
		manager.close();
		
		return orders;
	}
	
	public static List<Order> selectOrders(Integer id){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<Order> order = new ArrayList<>();
		
		Order o = manager.find(Order.class, id);
		order.add(o);
		
		manager.close();
		
		return order;
	}
	
	public static void insertOrder(Order order, List<OrderItem> items) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		if(order.getAmount()==null) order.setAmount(0.0);
		manager.persist(order);
		
		for(OrderItem oi : items) {
			
			if(oi.getQuantity()<1) break;
			
			oi.setOrderId(order.getId());
				
			Product p = manager.find(Product.class, oi.getProductId());
			if(p.getQuantity()>0 && p.getQuantity()>oi.getQuantity()) 
				p.setQuantity(p.getQuantity()-oi.getQuantity());
			else throw new IllegalArgumentException();
			oi.setSellPrice(p.getPrice());
			manager.persist(p);
				
			order.setAmount(order.getAmount()+oi.getSellPrice()*oi.getQuantity());
			manager.persist(oi);
			
		}
		
		transaction.commit();
		manager.close();
	}
	
	public static void updateOrders(String col, String colVal, Integer id) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		Order u = manager.find(Order.class, id);
		switch(col.toUpperCase()) {
		case "USERID":
			u.setUserId(Integer.valueOf(colVal));
			break;
		case "AMOUNT":
			u.setAmount(Double.valueOf(colVal));
			break;
		case "DATETIME":
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
	
	public static void updateOrderItems(String col, String colVal, Integer id) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		OrderItem i = manager.find(OrderItem.class, id);
		switch(col.toUpperCase()) {
		case "ORDERID":
			i.setOrderId(Integer.valueOf(colVal));
			break;
		case "PRODUCTID":
			i.setProductId(Integer.valueOf(colVal));
			break;
		case "SELLPRICE":
			i.setSellPrice(Double.valueOf(colVal));
			break;
		case "QUANTITY":
			i.setQuantity(Integer.valueOf(colVal));
			break;
		default:
			System.out.println("No columns found");
			break;
		}
		manager.persist(i);
		
		transaction.commit();
		manager.close();
	}
}
