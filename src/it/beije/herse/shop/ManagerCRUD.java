package it.beije.herse.shop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class ManagerCRUD {
	
	private final EntityManager manager = ShopEntityManager.newEntityManager();
	private final EntityTransaction t = manager.getTransaction();

	public List<Object> selectAll(String query) {
		
		Query select = manager.createQuery(query);
		
		return select.getResultList();
		
	}
	
	public Object selectFromID(Class<?> typeObject, Integer id) {
		return manager.find(typeObject, id);
	}
	
	public void insert(Object o) {
		t.begin();
		manager.persist(o);
		t.commit();		
	}
	
	public boolean close() {
		manager.close();
		return !manager.isOpen();
	}

}
