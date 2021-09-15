package cicli;
public class Fibonacci{
	public static void main(String args[]){
		int fib = 1;
		int pre = 1;
		int temp = 0;
		System.out.println(fib);
		
		for(int i=1; i<6; i++){
			System.out.println(fib);
			temp = fib;
			fib = fib + pre;
			pre = temp;
		}
	}
}