package cicli;

public class FibonacciFigura {
		static void stampaFibonacci(int x) {
			int fib = 1;
			int pre = 1;
			int temp = 0;
			System.out.print(fib);
			for(int i = 1; i<x; i++) {
				System.out.print(", " + fib );
				
				temp = fib;
				fib = fib + pre;
				pre = temp;
			}
		}
	public static void main(String[] args) {
		int x = 50;
		for(int i =0; i<x; i++) {
			stampaFibonacci(i+1);
			System.out.print("\n");
		}

	}

}
