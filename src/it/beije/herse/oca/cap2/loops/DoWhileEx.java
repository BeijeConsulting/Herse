package it.beije.herse.oca.cap2.loops;

public class DoWhileEx{
	int roomInBelly = 5;

	public void eatCheese(int bitesOfCheese) {
		do{
			bitesOfCheese--;
			roomInBelly--;
		}while (bitesOfCheese > 0 && roomInBelly > 0);
		System.out.println(bitesOfCheese+" pieces of cheese left");
	}
	
	public static void main(String[] args){
		DoWhileEx x = new DoWhileEx();
		x.eatCheese(0);
	}
}