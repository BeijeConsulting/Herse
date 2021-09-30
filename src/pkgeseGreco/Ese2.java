package pkgeseGreco;

public class Ese2 {

	public static void main(String[] args) {
		int[] array = {4, 6, 10, 63, 45, 3};
		int max = 0, c1 = 0, c2 = 0;
		int min = array[0];
		for(int i = 0; i < array.length; i++) {
			if(array[i] > max) {
				max = array[i];
				c1 = i;
			}
			if(array[i] < min) {
				min = array[i];
				c2 = i;
			}
		}
		System.out.println("Il valore più alto è " + max + " all'indice " + c1);
		System.out.println("Il valore più basso è " + min + " all'indice " + c2);
	}

}
