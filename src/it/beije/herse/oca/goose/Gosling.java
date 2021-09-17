package it.beije.herse.oca.goose;

import it.beije.herse.oca.bird.Bird;

public class Gosling extends Bird { // extends means create subclass
	public static final int x;
	static {
		x = 10;
	}
	public void swim() {
		floatInWater(); // package access to superclass
		System.out.println(text); // package access to superclass
		}
		public void helpOtherSwanSwim() {
		Gosling other = new Gosling();
		floatInWater(); // package access to superclass
		System.out.println(other.text);// package access to superclass
		 }
		public void helpOtherBirdSwim() {
		Bird other = new Bird();
		//other.floatInWater(); // DOES NOT COMPILE
		// System.out.println(other.text); // DOES NOT COMPILE
		 }
}
