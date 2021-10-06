package Shop;

import javax.persistence.*;
public class ShopEntityManager {
	
	private ShopEntityManager() {}
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager newEntityManager() {
		if (entityManagerFactory == null) 
			entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		
		return entityManagerFactory.createEntityManager();
	}

}
