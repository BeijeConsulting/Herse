package it.beije.herse.animals;

public abstract class Vertebrate extends Animal {

	public int numOfBone;
	public String skinType;
	public String breatheType;
	
	// ABSTRACT
	public abstract String getSkinType();
	public abstract String getBreatheType();
	
    // IMPLEMENTED
	public  void move() {

		System.out.println("Where?");
	}
	// OVERLOAD
	public  void move(String where) {

		System.out.println("I'm going to " + where);
	}

	public  void breathe() {

		System.out.println("I'm breathing..");
	}
	public void eat(String food) {

		System.out.println("I'm eating " + food + ".. that's good!");
	}
	public  void sleep() {
		System.out.println("Goodnight!");
	}
}
