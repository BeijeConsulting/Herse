package it.beije.herse.oca.cap2.operators;
public class LogicalOP{
	public static void main(String[] args){
		int x;
		int y;
		float z = 1.11F;
		
		x = 3 * 7 + 2 - 9 * (-5);
		if(1.11F != (int)z && x>10) y = x % 10;
		else y = x*x;

		z = (y==8) ? (int) x*x : (int) x;

		System.out.println("X: "+x);
		System.out.println("Y: "+y);
		System.out.println("Z: "+z);
	}
}