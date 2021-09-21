package it.beije.herse.oca.cap4.lambdas;

import java.util.*;
import java.util.function.*;

public class PredicateSearch {

	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal("fish", false, true));
		animals.add(new Animal("kangaroo", true, false));
		animals.add(new Animal("rabbit", true, false));
		animals.add(new Animal("turtle", false, true));
		
		System.out.println("Can Hop: "+print(animals, a -> a.canHop()));
		System.out.println("Can Swim: "+print(animals, a -> a.canSwim()));
	}

	private static List<String> print(List<Animal> animals, Predicate<Animal> checker) {
		List<String> animalNames = new ArrayList<>();
		
		for (Animal animal : animals) {
			if (checker.test(animal)) animalNames.add(animal.toString());
		}
		
		return animalNames;
	}
}
