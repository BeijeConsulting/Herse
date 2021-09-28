package it.beije.herse.OCA;

public class Singleton {
	
	private Singleton () {}
	
	private static Singleton instance;
	
	public static Singleton getInstance() {
		if (instance == null)
			instance = new Singleton();
		
		return instance;
	}

}
