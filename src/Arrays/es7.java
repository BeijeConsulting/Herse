package Arrays;
/*
 * crivere un programma StampaZigZag che, dato un array di 10 numeri interi contenente valori a piacere, ne stampa gli elementi secondo il 
 * seguente ordine: il primo, l’ultimo, il secondo, il penultimo, il terzo, il terz’ultimo, ecc…
 */
public class es7 {
	
	public static void main(String args[]) {
		int[] num = new int[] {1, 1, 1, 1, 1, 2, 2, 2, 2, 2};
		new es7().stampaZigZag(num);
	}
	
	public void stampaZigZag(int[] array) {
		int j = array.length -1;
		for(int i = 0; i < array.length ; i++) {
			if (array.length % 2 == 0 && i == j+1)
				break;
			System.out.print(array[i] + " " );
			if(array.length %2 !=1 && i == j)
				break;
			System.out.print(array[j] + " ");
			j--;
			
		}
	}
	
}
