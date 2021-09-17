package it.beije.herse.oca.cap2.loops;

public class ForEx{

	public void basicFor(){
		for(int i = 0; i < 10; i++) System.out.print(i + " ");
	}	

	// Creating an Infinite Loop
	public void infiniteLoop(){
		for( ; ;) System.out.println("Hello World");
	}

	// Adding Multiple Terms to the for Statement
	public void multipleTerms(){
		long z = 0;
		for(long y = 0, x = 0; x < 5 && y < 10; x++, y++) {
			System.out.print(y + " ");
			z = x;
		}
		System.out.print(z);
	}

	public static void main(String[] args){
		ForEx f = new ForEx();
		//f.infiniteLoop();

		f.basicFor();
		System.out.println("");
		f.multipleTerms();
	}
}