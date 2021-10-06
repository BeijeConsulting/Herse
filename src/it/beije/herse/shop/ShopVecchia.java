package it.beije.herse.shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.beije.herse.db.jpa.PhoneContactsJPA;
import it.beije.herse.shop.manager.OrderManager;
import it.beije.herse.shop.manager.ProductManager;
import it.beije.herse.shop.manager.UserManager;

public class ShopVecchia {
	
	public static void main(String[] args) {
		// JPA log at the start
		UserManager.selectUsers();
		
		// UserManager
//		List<User> users = new ArrayList<>();
//		User u = new User();
//		u.setName("Giorgio");
//		u.setSurname("Smith");
//		u.setEmail("g.smith@gmail.com");
//		u.setPassword("grgsmth");
//		users.add(u);
//		UserManager.insertUsers(users);
		
//		UserManager.updateUsers("name", "Paul", 2);
		
		System.out.println("USERS: ");
		UserManager.printUsers(UserManager.selectUsers());
		System.out.println();
		
		// ProductManager
//		List<Product> products = new ArrayList<>();
//		Product p = new Product();
//		p.setName("BBB");
//		p.setDescription("Laptop");
//		p.setPrice(499.99);
//		p.setQuantity(9);
//		products.add(p);
//		ProductManager.insertProducts(products);
		
//		ProductManager.updateProducts("price", "9.99", 1);
		
		System.out.println("PRODUCTS: ");
		ProductManager.printProducts(ProductManager.selectProducts());
		System.out.println();
		
		// OrderManager
//		List<OrderItem> items = new ArrayList<>();
//		OrderItem oi = new OrderItem();
//		oi.setOrderId(16);
//		oi.setProductId(2);
//		oi.setSellPrice(699.99);
//		items.add(oi);
//		oi = new OrderItem();
//		oi.setOrderId(16);
//		oi.setProductId(1);
//		oi.setSellPrice(9.99);
//		items.add(oi);
		
//		List<Order> orders = new ArrayList<>();
//		Order o = new Order();
//		o.setUserId(1);
//		o.setDateTime(LocalDateTime.now());
//		orders.add(o);
//		OrderManager.insertOrders(orders, items);
		
//		OrderManager.updateOrders("datetime", ""+LocalDateTime.now(), 16);
		
		System.out.println("ORDERS: ");
		OrderManager.printOrders(OrderManager.selectOrders(16));
		System.out.println();
	}
}
