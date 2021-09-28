package it.beije.herse.oca;

public class Runner {
	
	public static void main(String[] args) {
		
		//Singleton singleton = new Singleton();

		System.out.println(Singleton.getInstance());
		System.out.println(Singleton.getInstance());
		System.out.println(Singleton.getInstance());
		System.out.println(Singleton.getInstance());
		System.out.println(Singleton.getInstance());

	}

}
