package it.beije.herse.shop;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import it.beije.herse.db.ShopJPA;

public class Shop {

	public static void main(String[] args) {
		
		Random rand = new Random();
		
		ShopJPA shop = new ShopJPA();
		
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		List<Object> allProducts = shop.getAll(manager, "Product");
		
		/*
		 * Qui si potrebbe implementare un'interfaccia utente,
		 * dove l'utente vede una lista di prodotti disponibili
		 * e seleziona quelli da agiunere al suo ordine
		 * 
		 * Per adesso se ne occupa il metodo randomPicker.
		 * */
		
		System.out.println("\n ************* CREATING ORDER *************\n");
		int nItems = rand.nextInt(9); //variabile per settare il numero di prodotti da selezionare
		System.out.println(nItems + " PRODUCTS TO SELECT");
		User user = manager.find(User.class, rand.nextInt(2)+1); //il 2 messo apposta in crudo, perché so che nel mio db ci sono 3 user
		List<Object> itemsOrdered = shop.randomPicker(manager, allProducts, nItems);
		shop.insertOrder(manager, itemsOrdered, user.getId());
		System.out.println("\n **************** ENDING ORDER ***************\n");
		
		System.out.println("\n **************** ALL ORDERS ***************\n");
		List<Object> allOrders = shop.getAll(manager, "Order");
		
		for(Object obj : allOrders)
		{
			System.out.println("==============================================\n");
			Order order = (Order)obj;
			User u = manager.find(User.class, order.getUserId());
			StringBuilder builder = new StringBuilder("Id Order: ").append(order.getId()+"\n")
									.append("Creation Date: ").append(order.getDateTime()+"\n")
									.append("By User: ").append(u.getName() + " " + u.getSurname() +"\n")
									.append("Email User: ").append(u.getEmail()+"\n")
									.append("Amount: ").append(order.getAmount()+"\n")
									.append("Products Ordered:\n");
			System.out.println(builder.toString());
			System.out.println(shop.printOrderItemByOrderId(manager, order.getId()));

		}
		
		manager.close();
	}

}
