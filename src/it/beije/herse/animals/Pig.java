package it.beije.herse.animals;

public class Pig extends Mammal implements Omnivorous {

	String breed;
	int weight;

	public Pig(int age, int weight, int numOfBone, String skinType, String breatheType, String breed) {

		this.age=age;
		this.weight=weight;
		this.numOfBone=numOfBone;
		this.skinType=skinType;
		this.breatheType=breatheType;
		this.breed=breed;
		this.howManyBreasts=8;
	}

	public Pig() {}

	public String getSkinType() {
		return this.skinType;
	}
	public String getBreatheType() {
		return this.breatheType;
	}

	public void run() {

		System.out.println("I'm running");

	}

	public void hunt() {

		System.out.println("I'm hunting!!!");
	}

	public void escape() {

		System.out.println("I'm escaping");

	}

}