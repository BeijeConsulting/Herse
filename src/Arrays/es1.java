package Arrays;
//Trovare il massimo elemento in un array (o il minimo)

public class es1 {

	public static void main(String args[]) {
		int [] numbers = new int[] {10, 20, 43, 3, 65, 210, 6, 9};
		int max = numbers[1];
		for(int i  = 0; i < numbers.length; i++) {
			if(numbers[i] > max) {
				max = numbers [i];
			}
		}
		System.out.println("Il massimo è : " + max);
	}
}
