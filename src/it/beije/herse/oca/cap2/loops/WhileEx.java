package it.beije.herse.oca.cap2.loops;

public class WhileEx{
	int roomInBelly = 5;

	public void eatCheese(int bitesOfCheese) {
		while (bitesOfCheese > 0 && roomInBelly > 0) {
			bitesOfCheese--;
			roomInBelly--;
		}
		System.out.println(bitesOfCheese+" pieces of cheese left");
	}
	
	public static void main(String[] args){
		WhileEx w = new WhileEx();
		w.eatCheese(3);
	}
}