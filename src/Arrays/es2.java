package Arrays;
//Trovare l’indice del massimo elemento in un array (o il minimo)

public class es2 {

	public static void main(String args[]) {
		int [] numbers = new int[] {10, 20, 43, 3, 65, 210, 6, 9};
		int maxI = 0;
		int max = numbers[0];
		for(int i  = 0; i < numbers.length; i++) {
			if(numbers[i] > max) {
				maxI = i;
			}
		}
		System.out.println("L'indece del numero massimo è : " + maxI);
	}
}
