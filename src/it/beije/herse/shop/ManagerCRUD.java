package it.beije.herse.shop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class ManagerCRUD {
	
	private EntityManager manager = ShopEntityManager.newEntityManager();
	private EntityTransaction t = manager.getTransaction();

	public List<Object> select(String query) {
		
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
	
	public void close() {
		manager.close();
	}
	
	public void open() {
		manager = ShopEntityManager.newEntityManager();
	}
	
	public boolean isOpen() {
		return manager.isOpen();
	}
	
}
