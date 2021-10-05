package it.beije.herse.shop;

public class MyShop {

	public static void main(String[] args) {
		
		ManagerCRUD manager = new ManagerCRUD();
		
		User user = (User)manager.selectFromID(User.class, 2);
		
		System.out.println(user);
		
		if(manager.close())
			System.out.println("Connessione chiusa");
		else
			System.out.println("Errore: connessione aperta");
		
	}

}
