package Arrays;
/*
 *  * Verificare la sequenza crescente di un array. Il metodo “boolean isCrescente(int [] array)” restituisce true se tutti gli elementi dell’array passato sono in ordine crescente, false altrimenti.
 */


public class es4 {
	
	public static void main() {
		int[] numbers = new int[] {1, 2, 3, 4, 5} ;
		System.out.println(new es4().isCrescente(numbers));
	}
	
	public boolean isCrescente(int[] array) {
		for(int i = 0; i < array.length - 1; i ++) {
			if(array[i] > array[i+1]) {
				return false;
			}
		}
		return true;
	}
	
}
