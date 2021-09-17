package it.beije.herse.oca.cap5.inheritence.hiding;

public class Panda extends Bear {
	public static void eat() {
		System.out.println("Panda bear is chewing");
	}
	
	public static void main(String[] args) {
		Bear.eat();
		Panda.eat();
	}
}
