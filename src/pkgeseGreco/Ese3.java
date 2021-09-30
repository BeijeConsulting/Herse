package pkgeseGreco;
import java.util.*;

public class Ese3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Object[] array = {4, 6, 10, 63, 45, 3};
		System.out.println("Inserisci un numero:");
		int n = sc.nextInt();
		if(contains((Object)n, array)) {
			System.out.println("Il numero inserito è presente nell'array");
		}else {
			System.out.println("Il numero inserito non è presente nell'array");
		}
		sc.close();
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
