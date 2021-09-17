package Arrays;
/*
 * Ripetere l’esercizio (3_1) con “boolean contains(Object e, Object[] array)
 */

public class es3_2 {
	
	public static void main(String args[]) {
		Object elemento = 14;
		Object[] elementi = new Object[] {14, 56, 78, 99, 43, 12};
		
		System.out.println(contains(elemento, elementi));
		
	}

	
	public static boolean contains(Object e, Object[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == e) {
				return true;
			}
		}
		return false;
	}

}
