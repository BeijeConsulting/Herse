package it.beije.herse.oca.capitolo4;


public class Yet {
	
	
	

	static { add(2); }
	static void add(int num) { System.out.print(num + " "); }
	Yet() { add(5); }
	static { add(4); }
	{ add(6); }
	{ new Yet(); }
	 static{ add(8); }
	
	public static void main(String[] args) { 
		new Yet();
	}

	
}


