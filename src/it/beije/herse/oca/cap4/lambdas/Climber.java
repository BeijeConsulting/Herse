package it.beije.herse.oca.cap4.lambdas;

interface Climb {
	boolean isTooHigh(int height, int limit);
}

public class Climber {
	public static void main(String[] args) {
		check((h, l) -> h>l, 20);
	}

	private static void check(Climb climb, int height) {
		if (climb.isTooHigh(height, 10)) System.out.println("too high");
		else System.out.println("ok");
	}
}
