package Arrays;
/*
 * Scrivere un programma Media che calcoli la media di un array di numeri interi
 */

public class es8 {
	
	public static void main(String args[]) {
		int[] num = new int[] {10, 15, 45, 87, 29};
		System.out.println(new es8().media(num));
	}
	
	public int media(int[] array) {
		int somma = 0;
		for(int i = 0; i < array.length; i++) {
				somma += array[i];
		}
		return somma / array.length;
	}

}
