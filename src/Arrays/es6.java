package Arrays;
/*
 * Scrivere un programma MediaMultipliDiTre che calcoli la media di un array di numeri interi, considerando i soli numeri divisibili per tre.
 */
public class es6 {
	
	public static void main(String args[]) {
		int[] num = new int[] {1, 3, 4, 6, 9};
		System.out.println(new es6().mediaMultipliDiTre(num));
	}
	
	public int mediaMultipliDiTre(int[] array) {
		int somma = 0;
		int media = 0;
		int cont = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] % 3 == 0) {
				cont++;
				somma += array[i];
			}
		}
		media = somma / cont;
		return media;
	}
	
}
