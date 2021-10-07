package it.beije.herse.shop;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class ShoBuy2 {
	
	final static EntityManager manager = ShopEntityManager.newEntityManager();
	
	
	
	public static void buyProducts() {
		
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
		
		String selectProduct = "SELECT p FROM Product as p";
		System.out.println("Ecco a te il nostro catalogo");
		List<Product> productList = Shop3.selectProduct();
		
		for(Product p: productList) {
			System.out.println(p);
		}
		
		boolean wantToBuy = true;
		
		int orderId = Shop3.insertOrder(utente);
		
		while(wantToBuy) {
			System.out.println("Dimmi l'id del prodotto che vuoi acquistare");
			Scanner input3 = new Scanner(System.in);
			int productid = input3.nextInt();
			System.out.println("Dimmi la quantità che vuoi acquistare");
			Scanner input4 = new Scanner(System.in);
			int quantityP = input4.nextInt();
			
			Product product = manager.find(Product.class, productid);
			
			if(product.getQuantity() < quantityP) {
				System.out.println("Quantità maggiore rispetto a quella disponibile");
			} else {
				
				System.out.println(product);
				
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();
				product.setQuantity(product.getQuantity()-quantityP); //settatto la quantità nuova di product
				transaction.commit();
				
				Shop3.insertOrderItem(orderId, productid, product.getPrice(), quantityP);
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
		
		String selectP = "SELECT p FROM OrderItem as p WHERE orderId=" + orderId;
		Query query = manager.createQuery(selectP);
		List<OrderItem> orders =  query.getResultList();
		
		Double total = 0.0;
		
		for(OrderItem o: orders) {
			System.out.println(o);
			total += o.getSellPrice() * o.getQuantity();
		}
		
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.find(Order.class, orderId).setAmount(total);
		transaction.commit();
		
		Order order = manager.find(Order.class, orderId);
		
		System.out.println(order);
	}

}
