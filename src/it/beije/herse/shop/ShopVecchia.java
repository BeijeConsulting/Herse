package it.beije.herse.shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.herse.db.jpa.PhoneContactsJPA;
import it.beije.herse.shop.manager.OrderManager;
import it.beije.herse.shop.manager.ProductManager;
import it.beije.herse.shop.manager.UserManager;

public class ShopVecchia {
	
	private static Integer select;

	public static void login() {
		System.out.println("INSERT EMAIL AND PASSWORD");
		System.out.print("EMAIL: ");
		String email = new Scanner(System.in).nextLine();
		System.out.print("PASSWORD: ");
		String password = new Scanner(System.in).nextLine();
		
		if(UserManager.loginUser(email, password)) menu();
		else {
			SELECT: do {
				System.out.println();
				System.out.println("EMAIL AND PASSWORD NOT FOUND: ");
				System.out.println("1 - RETRY");
				System.out.println("2 - SIGN IN");
				System.out.println("3 - EXIT");
				
				select = Integer.valueOf(new Scanner(System.in).nextLine());
				switch(select) {
				case 1: 
					login();
					break SELECT;
				case 2: 
					User u = new User();
					u.setEmail(email);
					u.setPassword(password);
					List<User> uList = new ArrayList<>();
					UserManager.insertUsers(uList);
					System.out.println("SIGNED IN");
					menu();
					break SELECT;
				case 3: 
					System.exit(0);
					break;
				default:
					System.out.println("SELECT VALID ACTION");
					break;
				}
			}while(true);
		}
	}
	
	public static void menu() {
		// JPA log at the start
		UserManager.selectUsers();
		
		do {
			System.out.println();
			System.out.println("SELECT AN ACTION: ");
			System.out.println("1 - PRINT USERS");
			System.out.println("2 - PRINT PRODUCTS");
			System.out.println("3 - PRINT ORDERS");
			System.out.println("4 - EXIT");
			
			select = Integer.valueOf(new Scanner(System.in).nextLine());
			switch(select) {
			case 1: 
				printUsers();
				break;
			case 2: 
				printProducts();
				break;
			case 3: 
				printOrders();
				break;
			case 4: 
				System.exit(0);
				break;
			default:
				System.out.println("SELECT VALID ACTION");
				break;
			}
		}while(true);
	}
	
	private static void printOrders() {
		do {
			System.out.println();
			System.out.println("SELECT AN ACTION: ");
			System.out.println("0 - BACK");
			System.out.println("1 - PRINT ALL ORDERS");
			System.out.println("2 - PRINT ONE ORDER");
			System.out.println("3 - EXIT");
			
			select = Integer.valueOf(new Scanner(System.in).nextLine());
			switch(select) {
			case 0: break;
			case 1: 
				System.out.println("ORDERS: ");
				OrderManager.printOrders(OrderManager.selectOrders());
				break;
			case 2: 
				System.out.println("INSERT ORDER'S ID: ");
				select = Integer.valueOf(new Scanner(System.in).nextLine());
				System.out.println("ORDER: ");
				OrderManager.printOrders(OrderManager.selectOrders(select));
				break;
			case 3: 
				System.exit(0);
				break;
			default:
				System.out.println("SELECT VALID ACTION");
				break;
			}
		}while(select!=0);
	}

	private static void printProducts() {
		do {
			System.out.println();
			System.out.println("SELECT AN ACTION: ");
			System.out.println("0 - BACK");
			System.out.println("1 - PRINT ALL PRODUCTS");
			System.out.println("2 - PRINT ONE PRODUCT");
			System.out.println("3 - EXIT");
			
			select = Integer.valueOf(new Scanner(System.in).nextLine());
			switch(select) {
			case 0: break;
			case 1: 
				System.out.println("PRODUCTS: ");
				ProductManager.printProducts(ProductManager.selectProducts());
				break;
			case 2: 
				System.out.println("INSERT PRODUCT'S ID: ");
				select = Integer.valueOf(new Scanner(System.in).nextLine());
				System.out.print("PRODUCT: ");
				ProductManager.printProducts(ProductManager.selectProducts(select));
				break;
			case 3: 
				System.exit(0);
				break;
			default:
				System.out.println("SELECT VALID ACTION");
				break;
			}
		}while(select!=0);
	}

	private static void printUsers() {
		do {
			System.out.println();
			System.out.println("SELECT AN ACTION: ");
			System.out.println("0 - BACK");
			System.out.println("1 - PRINT ALL USERS");
			System.out.println("2 - PRINT ONE USER");
			System.out.println("3 - PRINT USER'S ORDER HISTORY");
			System.out.println("4 - EXIT");
			
			select = Integer.valueOf(new Scanner(System.in).nextLine());
			switch(select) {
			case 0: break;
			case 1: 
				System.out.println("USERS: ");
				UserManager.printUsers(UserManager.selectUsers());
				break;
			case 2: 
				System.out.println("INSERT USER'S ID: ");
				select = Integer.valueOf(new Scanner(System.in).nextLine());
				System.out.print("USER: ");
				UserManager.printUsers(UserManager.selectUsers(select));
				break;
			case 3: 
				System.out.println("INSERT USER'S ID: ");
				select = Integer.valueOf(new Scanner(System.in).nextLine());
				UserManager.printOrderHistory(select);
				break;
			case 4: 
				System.exit(0);
				break;
			default:
				System.out.println("SELECT VALID ACTION");
				break;
			}
		}while(select!=0);
	}

	public static void main(String[] args) {
		login();
		
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
		
//		System.out.println("USERS: ");
//		UserManager.printUsers(UserManager.selectUsers());
//		System.out.println();
		
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
		
//		System.out.println("PRODUCTS: ");
//		ProductManager.printProducts(ProductManager.selectProducts());
//		System.out.println();
		
		// OrderManager
//		List<OrderItem> items = new ArrayList<>();
//		OrderItem oi = new OrderItem();
//		oi.setOrderId(17);
//		oi.setProductId(1);
//		oi.setSellPrice(9.99);
//		oi.setQuantity(3);
//		items.add(oi);
//		oi = new OrderItem();
//		oi.setOrderId(17);
//		oi.setProductId(1);
//		oi.setSellPrice(9.99);
//		items.add(oi);
		
//		List<Order> orders = new ArrayList<>();
//		Order o = new Order();
//		o.setUserId(2);
//		o.setDateTime(LocalDateTime.now());
//		orders.add(o);
//		OrderManager.insertOrders(orders, items);
		
//		OrderManager.updateOrders("datetime", ""+LocalDateTime.now(), 16);
		
//		System.out.println("ORDERS: ");
//		OrderManager.printOrders(OrderManager.selectOrders());
//		System.out.println();
		
//		System.out.println("INSERT USER ID: ");
//		UserManager.printOrderHistory(Integer.valueOf(new Scanner(System.in).nextLine()));
	}
}
