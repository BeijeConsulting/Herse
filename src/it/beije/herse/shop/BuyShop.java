package it.beije.herse.shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class BuyShop{

	final static EntityManager manager = ShopEntityManager.newEntityManager();

	public static void main(String[] args) {
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
		
//		System.out.println(utente);
		
		String selectProduct = "SELECT p FROM Product as p";
		System.out.println("Ecco a te il nostro catalogo");
		List<Object> products = Shop2.select(selectProduct);
	
		System.out.println("Vuoi acquistare qualcosa?");
		Scanner input2 = new Scanner(System.in);
		String answer = input2.next();
		
		boolean wantToBuy = true;
		
		Order order0 = (Order) Shop2.insert("order");
		
		int orderId = order0.getId();
		
		OrderItem orderItem0 = (OrderItem) Shop2.insert("orderitem");
		
		int orderItemId = orderItem0.getId();
		
		if(answer.equalsIgnoreCase("si")) {
			while(wantToBuy) {
				System.out.println("Dimmi l'id del prodotto che vuoi acquistare");
				Scanner input3 = new Scanner(System.in);
				int productid = input3.nextInt();
				System.out.println("Dimmi la quantità che vuoi acquistare");
				Scanner input4 = new Scanner(System.in);
				int quantityP = input4.nextInt();
				String selectP = "SELECT p FROM Product as p WHERE id=" + productid;
				List<Object> objectToBuy =  Shop2.select(selectP);
				
				List<Product> productToBuy = new ArrayList();
				
				for(Object o: objectToBuy) {
					productToBuy.add((Product) o);
				}
				
				for(Product p: productToBuy) {
					if(p.getQuantity() < quantityP) {
						System.out.println("Quantità maggiore rispetto a quella disponibile");
						wantToBuy = false;
					} else {
						
						EntityTransaction transactionOrder = manager.getTransaction();
						transactionOrder.begin();
						
					//	Order order = new Order();
						
						Order order = manager.find(Order.class, orderId);
						
						System.out.println(order);
						
						order.setUserId(utente);
						manager.persist(order);
						System.out.println(order);
						transactionOrder.commit();
						
//						OrderItem orderItem= (OrderItem) Shop2.insert("orderitem");
						
						EntityTransaction transactionOrderItem = manager.getTransaction();
						transactionOrderItem.begin();
						
						OrderItem orderItem = manager.find(OrderItem.class, orderItemId);
						
						orderItem.setOrderId(orderId);
						orderItem.setProductId(productid);
						manager.persist(orderItem);
						System.out.println(orderItem);
						transactionOrderItem.commit();
						
						Shop2.updateProduct(productid, quantityP);
						
						String selectOI = "SELECT oi FROM OrderItem as oi WHERE productId=" + productid + "AND orderId=" +orderId;
						Query queryOrderItem = manager.createQuery(selectOI);
						List<Object> orderItem1 = Shop2.select(queryOrderItem);
						
						List<OrderItem> orderItemToChange = new ArrayList();
						
						for(Object o: orderItem1) {
							orderItemToChange.add((OrderItem) o);
						}
						int idOrderITem;
						for(OrderItem o: orderItemToChange) {
							Product product = manager.find(Product.class, productid);
							Shop2.orderItemSetPrice(orderItemToChange, product);
							
							Shop2.orderSetAmount(orderItemToChange, orderId);
						}
						
						
						
						
					}
				}
				
				System.out.println("Vuoi comprare altri articoli?");
				Scanner input5 = new Scanner(System.in);
				String risposta = input5.next();
				
				if(!risposta.equalsIgnoreCase("si")) {
					wantToBuy = false;
				} else {
					wantToBuy = true;
				}
			}
		}
	}
}
