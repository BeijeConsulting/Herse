package Shop;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.EntityManager;
public class Funzioni {
	static Scanner scanner=new Scanner(System.in);
	static EntityManager manager=ShopEntityManager.newEntityManager();

	public static void listaUtenti() {
		List<User> users=manager.createQuery("select u from User as u").getResultList();
		for(User u:users)
			System.out.println(u);
	}

	public static void inserisciUtente() {
		manager.getTransaction().begin();
		User user=new User();
		user.setName(scanner.next());
		user.setSurname(scanner.next());
		user.setEmail(scanner.next());
		user.setPassword(scanner.next());
		manager.persist(user);
		manager.getTransaction().commit();
	}

	public static void updateUtente() {

	}
	public static void deleteUtente() {
		manager.getTransaction().begin();
		System.out.println("Id da eliminare: ");
		manager.remove(manager.find(User.class, Integer.parseInt(scanner.next())));
		manager.getTransaction().commit();
	}

	/*------------------------------------------------------------------*/

	public static void listaProdotti() {
		List<Product> prodotti=manager.createQuery("SELECT p FROM Product as p").getResultList();
		for(Product p:prodotti)
			System.out.println(p);
	}


	public static void inserisciProdotto() {
		manager.getTransaction().begin();
		Product product=new Product();
		product.setName(scanner.next());
		product.setPrice(Double.parseDouble(scanner.next()));
		product.setDesc(scanner.next());
		product.setQty(Integer.parseInt(scanner.next()));
		manager.persist(product);
		manager.getTransaction().commit();
	}

	public static void updateProdotto() {

	}

	public static void deleteProdotto() {
		manager.getTransaction().begin();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Id da eliminare: ");
		manager.remove(manager.find(Product.class, Integer.parseInt(scanner.next())));
		manager.getTransaction().commit();

	}

	/*------------------------------------------------------------------*/
	public static void listaOrdini() {
		List<Order> ordini=manager.createQuery("SELECT o FROM Order as o").getResultList();
		for(Order o:ordini)
			System.out.println(o);
	}
	public static void Ordine() {
		Integer id;
		System.out.println("Inserisci il tuo Id:");
		id=Integer.parseInt(scanner.next());
		if(login(id)) {
			carrello(id);
		}

	}
	public static boolean login(Integer id) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<User> users=manager.createQuery("select u from User as u").getResultList();
		for(User u:users)
			if(u.getId().equals(id))
				return true;
		return false;
	}

	/*------------------------------------------------------------------*/

	public static void listaOrdiniProdotto() {
	}

	public static void carrello(Integer user) {
		Integer id,qty;
		int exit=1;
		Double tot = 0.0;
		List<Product> carrello = new ArrayList<Product>();
		List<Integer> q=new ArrayList();
		do {
			System.out.println("Inserisci id prodotto da acquistare:");
			listaProdotti();
			do {
				id=Integer.parseInt(scanner.next());
				if(controlloIdProdotto(id)==false)
					System.out.println("Id non presente, inserire uno corretto!");
			}while(controlloIdProdotto(id)==false);
			System.out.println("Inserisci la quantità");
			do {
				qty=Integer.parseInt(scanner.next());
				if(controlloQuantità(id,qty)!=false) {
					Product p=manager.find(Product.class, id);
					manager.getTransaction().begin();
					p.setQty(p.getQty()-qty);
					manager.getTransaction().commit();
					q.add(qty);
				}

				else
					System.out.println("Quantità non disponibile, riprovare!");
			}while(controlloQuantità(id,qty)==false);
			carrello.add(manager.find(Product.class, id));
			tot+=qty*carrello.get(carrello.size()-1).getPrice();
			System.out.println("Premi 1 per continuare gli acquisti\nPremi 0 per uscire.");
			exit=Integer.parseInt(scanner.next());
		}while(exit!=0);
		inserisciOrdineItem(carrello,q,inserisciOrdine(user,tot));
	}

	public static boolean controlloQuantità(Integer id,Integer qty) {
		Product p=manager.find(Product.class, id);
		if(p.getQty()>=qty) {
			return true;
		}
		return false;
	}

	public static boolean controlloIdProdotto(Integer id) {
		if(manager.find(Product.class, id)!=null)
			return true;
		return false;
	}

	public static Integer inserisciOrdine(Integer user,Double tot) {
		manager.getTransaction().begin();
		Order order=new Order();
		order.setAmount(tot);
		order.setDataTime(LocalDateTime.now());
		order.setUserId(user);
		manager.persist(order);
		manager.getTransaction().commit();
		return order.getId();
	}
	public static void inserisciOrdineItem(List<Product> carrello,List<Integer> q,Integer orderId) {
		OrderItem orderItem=new OrderItem();
		int i=0;
		for(Product c:carrello) {
			manager.getTransaction().begin();
			orderItem.setOrderId(orderId);
			orderItem.setProductId(c.getId());
			orderItem.setSellPrice(c.getPrice());
			orderItem.setQuantity(q.get(i));
			i++;
			manager.persist(orderItem);
			manager.getTransaction().commit();
		}
	}
}

