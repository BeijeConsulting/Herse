package Arrays;
import java.util.Arrays;

/*
 * Scrivere il metodo: “public int mostRecurrent(int [] array)” , che trova l’elemento più ricorrente in un array. Il metodo restituisce l’elemento trovato.
 */

public class es5 {
	
	public static void main(String args[]) {
		int[] numbers = new int[] {12, 32, 45, 67, 89, 89, 89, 12, 34, 89, 102, 12, 34, 12, 12, 12}; //{12, 12, 32, 34 45, 67, 89, 89, 89, 89}
		System.out.println(new es5().mostRecurrent(numbers));
	}
	
	public int mostRecurrent(int[] array) {
		int cont = 1;
		Arrays.sort(array);
		int maxFreq = array[0];
		for(int i = 0; i < array.length; i++) {
			int cont2 = 1;
			int app = array[i];
			cont2 ++;
			for(int j = i++; j< array.length; j++) {
				if(app == array[j]) {
					cont2++;
					if(cont < cont2) {
						maxFreq = app;
						cont = cont2;
					}
				}
			}
		}
		return maxFreq;
	}
	
}
