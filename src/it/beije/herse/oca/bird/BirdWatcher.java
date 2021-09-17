package it.beije.herse.oca.bird;

public class BirdWatcher extends Bird{

	public void watchBird() {
		Bird bird = new Bird();
		floatInWater(); // calling protected member
		System.out.println(bird.text); // calling protected member
		}
}
