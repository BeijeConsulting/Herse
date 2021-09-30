package pkgeseGreco;

public class Ese1 {

	public static void main(String[] args) {
		int[] array = {4, 6, 10, 63, 45, 3};
		int max = 0;
		int min = array[0];
		for(int i = 0; i < array.length; i++) {
			if(array[i] > max) {
				max = array[i];
			}
			if(array[i] < min) {
				min = array[i];
			}
		}
		System.out.println("Il valore più alto è: " + max);
		System.out.println("Il valore più basso è: " + min);
	}

}
