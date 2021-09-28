package esercizi;
import java.util.Arrays;

public class EsArray {
	
	static boolean contains (int e, int [] array) {
		boolean contiene = false;
		for(int i=0; i<array.length; i++) {
			if(array[i]==e) {
				contiene = true;
				break;
			}
		}
		return contiene;
	}


static boolean containsO(Object e, Object[] array) {
	boolean contiene = false;
	for(Object i : array) {
		if(i==e) {
			contiene = true;
			break;
		}
	}
	return contiene;
}
	
	
	static boolean isCrescente(int [] array) {
		boolean c = true;
		for(int i = 1; i<array.length; i++) {
			if(array[i-1]>array[i]) {
				c = false;
				break;
			}
		}
		return c;
	}
	
	public static int mostRecurrent(int [] array) {
		int maxRicorrenza = 0;
		int count = 0;
		int most = 0;
		Arrays.sort(array);
		for(int i = 1; i<array.length; i++) {
			if(array[i] == array[i-1]) {
				count++;
			}
			else {
				if(count>maxRicorrenza) {
					most = array[i-1];
				}
				maxRicorrenza = count;
				count = 0;
			}
		}
		return most;
	}

	public static void main(String[] args) {
		int[] array = {1, 25, 15, 150, 56, 268, 81, 157};
		/*	int max = array[0];
		int min = array[0];
		for(int i=0; i<array.length; i++) {
			if(array[i]>=max)
				max = array[i];
			}
		System.out.println(max);
		
		for(int i=0; i<array.length; i++) {
			if(array[i]<=min)
				min = array[i];
			}
		System.out.println(min);*/
		
	/*	int max = array[0];
		int indice = 0;
		for(int i=0; i<array.length; i++) {
			if(array[i]>=max) {
				max = array[i];
				indice = i;
			}
		}
		System.out.println(max + "\tall'indice\t" + indice); */
		
	//	System.out.println(contains(25, array));
	//	System.out.println(contains(7, array));
		
	//	Object [] a = new Object[] {15, 22, 43};
		
	//	System.out.println(containsO((Object) new Integer(22), a));
		
	//	System.out.println(isCrescente(array));
	//	Arrays.sort(array);
	//	System.out.println(isCrescente(array));
		
		
	//	int [] a = {1, 1, 1, 5, 5 , 5, 5, 12, 56, 1, 1, 1 , 1, 1};
	//	System.out.println(mostRecurrent(a));
		
		String [] names = new String[2];
		System.out.println(names.toString());
		
		
		
	}
}
	
	
