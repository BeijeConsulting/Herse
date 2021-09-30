package pkgeseGreco;

public class Ese5 {

	public static void main(String[] args) {
		int[] array = {4, 6, 10, 63, 45, 3, 6, 3, 3, 6, 6};
		Ese5 ese5 = new Ese5();
		System.out.println("Il numero più ricorrente è " + ese5.mostRecurrent(array));
	}
	
	public int mostRecurrent(int[] array) {
		int n = -1, c = 0;
		for(int i = 0; i < array.length; i++) {
			int cont = 0;
			for(int j = 0; j < array.length; j++) {
				if(array[i] == array[j]) {
					cont++;
				}
			}
			if(cont > c) {
				c = cont;
				n = array[i];
			}
		}
		return n;
	}

}
