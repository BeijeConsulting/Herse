package it.beije.herse.shop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyShop {

	private static ManagerCRUD manager = new ManagerCRUD();

	private static Scanner scan = new Scanner(System.in);

	private static User getUser(String email, String password) {

		User user = null;
		String query = "SELECT U FROM User as U WHERE email = '" + email + "' AND password = '" + password + "'";
		try {
			user = (User)manager.select(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Email o password errate");
		}

		return user;
	}

	public static List<Product> getProduct() {

		String query = "SELECT P FROM Product as P";

		List<Object> orders = manager.select(query);
		List<Product> list = new ArrayList<>();

		for(Object o : orders)
			list.add((Product)o);

		return list;

	}

	public static User singin() {

		System.out.print("Email: ");
		String email = scan.nextLine();
		System.out.print("Password: ");
		String psw = scan.nextLine();

		return getUser(email, psw);
	}

	private static LocalDateTime getDateTime() {
		DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		return LocalDateTime.parse(LocalDateTime.now().format(f), f);
	}

	public static void setOrder(Integer userId, Order ord) {
		ord.setUserId(userId);
		ord.setDateTime(getDateTime());
	}

	public static OrderItem setOrderItem(String prodId, String qta, Double sellPrice, Integer orderId) {

		OrderItem item = new OrderItem();
		item.setProductId(Integer.valueOf(prodId));
		item.setQuantity(Integer.valueOf(qta));
		item.setSellPrice(sellPrice);
		item.setOrderId(orderId);

		return item;

	}

	private static Product controlOrderId(String prodId, List<Product> prod) {

		for(Product p : prod)
			if(p.getId().equals(Integer.valueOf(prodId)))
				return p;

		return null;
	}

	private static boolean controlQta(String qta, Product p) {
		
		boolean flag = false;
		
		try {
			if(Double.valueOf(qta) > 0 && Double.valueOf(qta) <= p.getQuantity())
				flag = true;
		}catch(NumberFormatException e) {
			System.out.println("Valore non valido");
		}
		
		return flag;
	}
	
	public static void printDetails(Order o) {
		
		String query = "SELECT O FROM OrderItem AS O WHERE order_id = " + o.getId();
		List<Object> items = manager.select(query);
		List<Object> products = new ArrayList<>();
		
		for(Object item : items)
			products.add(manager.selectByID(Product.class,((OrderItem)item).getProductId()));

		for(Object prod : products)
			System.out.println("Nome: " + ((Product)prod).getName() + ", Descrizione: " + ((Product)prod).getDescription());
		
		System.out.println("Totale: " + o.getAmount());
		
	}
	
	public static void main(String[] args) {

		if(manager.isOpen()) {

			try {

				Double amount = 0.0;
				String prodId = null;
				String qta = null;
				User user = null;
				Order userOrder = new Order();
				List<Product> listProd = getProduct();
				List<OrderItem> orderItems = new ArrayList<>();

				do {
					user = singin();
					System.out.println();
				}while(user == null);

				setOrder(user.getId(),userOrder);
				
				manager.begin();

				do {

					try {
						
						for(Product p : listProd)
							System.out.println(p.toString());
						System.out.println();
						
						System.out.print("Inserisci l'id del prodotto da aggiungere all'ordine: ");
						prodId = scan.nextLine();
						Product select = controlOrderId(prodId, listProd);
						if(select != null) {
							System.out.print("Inserisci la quantita': ");
							qta = scan.nextLine();
							if(controlQta(qta,select)) {
								select.setQuantity(select.getQuantity() - Integer.valueOf(qta));
								manager.insert(select);
								listProd = getProduct();
								orderItems.add(setOrderItem(prodId, qta, select.getPrice(), userOrder.getId()));
								amount += select.getPrice();
							}else {
								System.out.println("Quantità selezionata non valida");
							}
						}
						
					} catch (NumberFormatException e) {
						prodId = null;
					}

				}while(prodId != null);

				userOrder.setAmount(amount);

				manager.insert(userOrder);

				System.out.println("Vuoi confermare l'ordine?");
				if(scan.nextLine().equalsIgnoreCase("si")) {					
					for(OrderItem o : orderItems) {
						o.setOrderId(userOrder.getId());
						manager.insert(o);
					}
					manager.insert(userOrder);
					manager.commit();
					System.out.println("\nOrdine avvenuto con successo\n");
					printDetails(userOrder);
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		}

	}

}
