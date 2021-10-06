package Shop;
import java.util.*;
import javax.persistence.EntityManager;
public class Funzioni {
	public static void listaUtenti() {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<User> users=manager.createQuery("select u from User as u").getResultList();
		for(User u:users)
			System.out.println(u);
		manager.close();
	}

	public static void inserisciUtente() {
		Scanner scanner=new Scanner(System.in);
		EntityManager manager=ShopEntityManager.newEntityManager();
		manager.getTransaction().begin();
		User user=new User();
		user.setName(scanner.next());
		user.setSurname(scanner.next());
		user.setEmail(scanner.next());
		user.setPassword(scanner.next());
		manager.persist(user);
		manager.getTransaction().commit();
		manager.close();
		scanner.close();
	}

	public static void updateUtente() {
		
	}
	public static void deleteUtente() {
		EntityManager manager=ShopEntityManager.newEntityManager();
		manager.getTransaction().begin();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Id da eliminare: ");
		manager.remove(manager.find(User.class, Integer.parseInt(scanner.next())));
		manager.getTransaction().commit();
		scanner.close();
		manager.close();
	}

	/*------------------------------------------------------------------*/

	public static void listaProdotti() {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<Product> prodotti=manager.createQuery("SELECT p FROM Product as p").getResultList();
		for(Product p:prodotti)
			System.out.println(p);
		manager.close();
	}

	
	public static void inserisciProdotto() {
		Scanner scanner=new Scanner(System.in);
		EntityManager manager=ShopEntityManager.newEntityManager();
		manager.getTransaction().begin();
		Product product=new Product();
		product.setName(scanner.next());
		product.setPrice(Double.parseDouble(scanner.next()));
		product.setDesc(scanner.next());
		product.setQty(Integer.parseInt(scanner.next()));
		manager.persist(product);
		manager.getTransaction().commit();
		manager.close();
		scanner.close();
	}
	
	public static void updateProdotto() {
		
	}
	
	public static void deleteProdotto() {
		EntityManager manager=ShopEntityManager.newEntityManager();
		manager.getTransaction().begin();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Id da eliminare: ");
		manager.remove(manager.find(Product.class, Integer.parseInt(scanner.next())));
		manager.getTransaction().commit();
		scanner.close();
		manager.close();
	}

	/*------------------------------------------------------------------*/
	public static void listaOrdini() {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<Order> ordini=manager.createQuery("SELECT o FROM Order as o").getResultList();
		for(Order o:ordini)
			System.out.println(o);
		manager.close();
	}


	/*------------------------------------------------------------------*/

	public static void listaOrdiniProdotto() {
		EntityManager manager=ShopEntityManager.newEntityManager();
		int n=1;
		while(manager.find(OrderItem.class, n)!=null) {
			System.out.println(manager.find(User.class, n));
			n++;
		}
		manager.close();
	}

}

