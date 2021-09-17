package Arrays;
/*
 * Scrivere un metodo “boolean contains(int e, int[] array)” che restituisca true se l’elemento e è presente nell’array, false altrimenti. 
 * Ripetere l’esercizio con “boolean contains(Object e, Object[] array)”, quali differenze ci sono?
 */
public class es3_1 {
	
	public static void main(String args[]) {
		int elemento = 1;
		int[] elementi = new int[] {14, 56, 78, 99, 43, 12};
		
		System.out.println(contains(elemento, elementi));
		
	}

	
	public static boolean contains(int e, int[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == e) {
				return true;
			}
		}
		return false;
	}
}
