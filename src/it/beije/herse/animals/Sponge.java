package it.beije.herse.animals;

public class Sponge extends Invertebrate {

	public final String BODY_TYPE;
	
    public Sponge(int age) {
    	
    	this.age=age;
    	this.BODY_TYPE= "Soft body";
    	
    }

    public String getBodyType() {
		
		return this.BODY_TYPE;
	}
    
  

	public  void move() {
		
		System.out.println("I can't move!");
	}
	
	
	public  void breathe() {

		System.out.println("I'm breathing..");
	}
	public void eat(String food) {

		System.out.println("I'm eating " + food + ".. that's good!");
	}
	public  void sleep() {
		System.out.println("I'm not tired");
	}
}
