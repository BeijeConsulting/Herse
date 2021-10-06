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
	private static Scanner scanner = new Scanner(System.in);
	private static User loggedUser;

	public static void login() {
		System.out.println("INSERT EMAIL AND PASSWORD");
		System.out.print("EMAIL: ");
		String email = scanner.nextLine();
		System.out.print("PASSWORD: ");
		String password = scanner.nextLine();
		
		if(UserManager.loginUser(email, password)) userMenu();
		else {
			SELECT: do {
				System.out.println();
				System.out.println("EMAIL AND PASSWORD NOT FOUND: ");
				System.out.println("1 - RETRY");
				System.out.println("2 - SIGN IN");
				System.out.println("3 - EXIT");
				
				select = scanner.nextInt();
				switch(select) {
				case 1: 
					break SELECT;
				case 2: 
					User u = new User();
					u.setEmail(email);
					u.setPassword(password);
					List<User> uList = new ArrayList<>();
					uList.add(u);
					UserManager.insertUsers(uList);
					System.out.println("SIGNED IN");
					break SELECT;
				case 3: 
					System.exit(0);
					break;
				default:
					System.out.println("SELECT VALID ACTION");
					break;
				}
			}while(true);
			login();
		}
	}
	
	public static void userMenu() {
		do {
			System.out.println();
			System.out.println("SELECT AN ACTION: ");
			System.out.println("1 - PRINT MY DATA");
			System.out.println("2 - PRINT MY ORDER HISTORY");
			System.out.println("3 - NEW ORDER");
			System.out.println("4 - EXIT");
			
			select = scanner.nextInt();
			switch(select) {
			case 1: 
				System.out.print("USER: ");
				UserManager.printUsers(UserManager.selectUsers(loggedUser.getId()));
				break;
			case 2: 
				UserManager.printOrderHistory(loggedUser.getId());
				break;
			case 3: 
				newOrder();
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
	
	private static void newOrder() {
		List<OrderItem> items = new ArrayList<>();
		Boolean addNewItem = true;
		while(addNewItem) {
			OrderItem item = new OrderItem();
			System.out.println("SELECT PRODUCT TO ADD (INSERT ID): ");
			ProductManager.printProducts(ProductManager.selectProducts());
			select = scanner.nextInt();
			item.setProductId(select);
			
			System.out.println("INSERT QUANTITY: ");
			select = scanner.nextInt();
			item.setQuantity(select);

			items.add(item);
			
			System.out.println("ADD ANOTHER PRODUCT? (y/n)");
			String addNewItemAnswer = scanner.nextLine();
			if(addNewItemAnswer.equalsIgnoreCase("n")) addNewItem = false;
			else if(addNewItemAnswer.equalsIgnoreCase("y")) addNewItem = true;
			else throw new IllegalArgumentException();
		}
		System.out.println();
		
		Order order = new Order();
		order.setUserId(loggedUser.getId());
		order.setDateTime(LocalDateTime.now());
		
		OrderManager.insertOrder(order, items);
		
		System.out.println("ORDER ADDED");
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
			
			select = scanner.nextInt();
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
			
			select = scanner.nextInt();
			switch(select) {
			case 0: break;
			case 1: 
				System.out.println("ORDERS: ");
				OrderManager.printOrders(OrderManager.selectOrders());
				break;
			case 2: 
				System.out.println("INSERT ORDER'S ID: ");
				select = scanner.nextInt();
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
			
			select = scanner.nextInt();
			switch(select) {
			case 0: break;
			case 1: 
				System.out.println("PRODUCTS: ");
				ProductManager.printProducts(ProductManager.selectProducts());
				break;
			case 2: 
				System.out.println("INSERT PRODUCT'S ID: ");
				select = scanner.nextInt();
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
			
			select = scanner.nextInt();
			switch(select) {
			case 0: break;
			case 1: 
				System.out.println("USERS: ");
				UserManager.printUsers(UserManager.selectUsers());
				break;
			case 2: 
				System.out.println("INSERT USER'S ID: ");
				select = scanner.nextInt();
				System.out.print("USER: ");
				UserManager.printUsers(UserManager.selectUsers(select));
				break;
			case 3: 
				System.out.println("INSERT USER'S ID: ");
				select = scanner.nextInt();
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

	public static void setLoggedUser(User loggedUser) {
		ShopVecchia.loggedUser = loggedUser;
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
//		oi.setProductId(1);
//		oi.setSellPrice(9.99);
//		oi.setQuantity(3);
//		items.add(oi);
//		oi = new OrderItem();
//		oi.setProductId(2);
//		oi.setSellPrice(699.99);
//		oi.setQuantity(1);
//		items.add(oi);
		
//		Order o = new Order();
//		o.setUserId(2);
//		o.setDateTime(LocalDateTime.now());
//		OrderManager.insertOrder(o, items);
		
//		OrderManager.updateOrders("datetime", ""+LocalDateTime.now(), 16);
		
//		System.out.println("ORDERS: ");
//		OrderManager.printOrders(OrderManager.selectOrders());
//		System.out.println();
		
//		System.out.println("INSERT USER ID: ");
//		UserManager.printOrderHistory(Integer.valueOf(new Scanner(System.in).nextLine()));
	}
}
