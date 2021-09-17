package it.beije.herse.oca.cap4.constructors;

public class Mouse {
	private int numTeeth;
	private int numWhiskers;
	private int weight;
	
	public Mouse(int weight) {
		this(weight, 16); // calls constructor with 2 parameters
	}
	
	public Mouse(int weight, int numTeeth) {
		this(weight, numTeeth, 6); // calls constructor with 3 parameters
	}
	
	public Mouse(int weight, int numTeeth, int numWhiskers) {
		this.weight = weight;
		this.numTeeth = numTeeth;
		this.numWhiskers = numWhiskers;
	}
	
	public void print() {
		System.out.println(weight + " " + numTeeth + " " + numWhiskers);
	}

	public static void main(String[] args) {
		Mouse mouse1 = new Mouse(15);
		System.out.print("Mouse 1: ");
		mouse1.print();
		
		Mouse mouse2 = new Mouse(15, 18);
		System.out.print("Mouse 2: ");
		mouse2.print();
		
		Mouse mouse3 = new Mouse(11, 15, 7);
		System.out.print("Mouse 3: ");
		mouse3.print();
	}
}
