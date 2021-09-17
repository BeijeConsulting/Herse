package it.beije.herse.oca.cap2.operators;

public class BinaryArithOP{
	public static void main(String[] args){
		int x;
		int y;
		float z = 1.11F;

		x = 3 * 7 + 2 - 9 * (-5);
		y = x*x*x;
		z += 1;

		System.out.println("X: "+x);
		System.out.println("Y: "+y);
		System.out.println("Z: "+z);
	}
}