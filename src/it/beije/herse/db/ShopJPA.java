package it.beije.herse.db;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.herse.shop.Order;
import it.beije.herse.shop.OrderItem;
import it.beije.herse.shop.Product;
import it.beije.herse.shop.ShopEntityManager;
import it.beije.herse.shop.User;

public class ShopJPA {
	
	public String printOrderItemByOrderId(EntityManager manager, int orderId)
	{
		String jpqlSelect = "SELECT x FROM OrderItem as x WHERE order_id=" + orderId;
		Query q = manager.createQuery(jpqlSelect);
		List<Object> items = q.getResultList();
		String response = "";
		
		for(Object obj : items)
		{
			OrderItem item = (OrderItem)obj;
			Product p = manager.find(Product.class, item.getProductId());
			StringBuilder builder = new StringBuilder("\tId Product: ").append(p.getId()+"\n")
					.append("\tName: ").append(p.getName()+"\n")
					.append("\tDescription: ").append(p.getDescription() + "\n")
					.append("\tPrice: ").append(item.getSellPrice()+"\n")
					.append("\tOrdered Quantity: ").append(item.getQuantity()+"\n")
					.append("\t---------------------");
			
			response += builder.toString();
		}
		
		return response;
	}
	
	public int itemCounter(List<Object> list, int objId)
	{
		int counter = 0;
		for(Object o : list)
		{
			if(((Product)o).getId() == objId)
				counter++;
		}
		return counter;
	}
	
	public Double getAmount(EntityManager manager, List<Object> list)
	{
		double amount = 0;
		
		for(Object o : list)
		{
			Product p = (Product)o;
			amount += p.getPrice();
		}
		
		return amount;
	}
	
	public List<Object> randomPicker (EntityManager manager, List<Object> list, int numberToChoose)
	{
		Random rand = new Random();
		List<Object> response = new ArrayList<>();
		
		for(int i = 0; i < numberToChoose; i++)
		{
			int n = rand.nextInt(list.size()-1); // Obtain a number between [0 - N].
			System.out.println(i+1 + " PRODUCT SELECTED");
			response.add(list.get(n));
		}
		
		return response;
	}
	
	public List<Object> getAll(EntityManager manager, String tableName)
	{
		String jpqlSelect = "SELECT x FROM "+ tableName +"  as x";
		Query q = manager.createQuery(jpqlSelect);
		
		return q.getResultList();
	}
	
	public void insertOrder(EntityManager manager, List<Object> productList, int userId)
	{
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		try
		{
			LocalDateTime dateTime = LocalDateTime.now();
			List<Integer> productsIds = new ArrayList<>();
			int orderId = 0;
			double amountOrder = getAmount(manager, productList);
			
			Order newOrder = new Order();
			newOrder.setDateTime(dateTime);
			newOrder.setAmount(amountOrder);
			newOrder.setUserId(userId);
			
			manager.persist(newOrder);
			manager.flush();
			orderId = newOrder.getId();
			
			for(Object o : productList)
			{
				Product p = (Product)o;
				if(!productsIds.contains(p.getId()))
					productsIds.add(p.getId());
				else
					continue;
					
				OrderItem item = new OrderItem();
				item.setOrderId(orderId);
				item.setProductId(p.getId());
				item.setSellPrice(p.getPrice());
				item.setQuantity(itemCounter(productList, p.getId()));				
				manager.persist(item);
			}
			
			transaction.commit();
			
			
		}
		catch(Exception e)
		{
			transaction.rollback();
			System.out.println(e);
		}
		
	}

}