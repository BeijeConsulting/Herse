package it.beije.herse.oca.cap5.inheritence.constructors;

public class Gorilla extends Animal {
	
	public Gorilla(int age) {
		super(age,"Gorilla");
	}
	
	public Gorilla() {
		super(5);
	}
	
	public static void main(String[] args) {
		Gorilla g = new Gorilla(32);
		
		System.out.println(g.getAge());
		System.out.println(g.getName());
	}
}
