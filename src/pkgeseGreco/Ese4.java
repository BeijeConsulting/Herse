package pkgeseGreco;

public class Ese4 {

	public static void main(String[] args) {
		int[] array = {4, 6, 10, 63, 45, 3};
		int[] array2 = {4, 6, 10, 63, 75, 83};
		if(isCrescente(array)) {
			System.out.println("L'array è crescente");
			System.out.println("");
		}else {
			System.out.println("L'array non è crescente");
			System.out.println("");
		}
		if(isCrescente(array2)) {
			System.out.println("L'array è crescente");
		}else {
			System.out.println("L'array non è crescente");
		}

	}
	
	public static boolean isCrescente(int[] array) {
		for(int i = 1; i < array.length; i++) {
			if(array[i] < array[i - 1]) {
				return false;
			}
		}
		return true;
	}

}
