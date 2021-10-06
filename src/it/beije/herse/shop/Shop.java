package it.beije.herse.shop;

import javax.persistence.EntityManager;

public class Shop {

	public static void main(String[] args) {
		
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		User user = manager.find(User.class, 1);
		
		System.out.println(user);
		
		Order order = manager.find(Order.class, 1);
		
		System.out.println(order);
		
		manager.close();
	}

}
