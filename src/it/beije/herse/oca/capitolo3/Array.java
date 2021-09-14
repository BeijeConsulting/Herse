package it.beije.herse.oca.capitolo3;

import java.util.ArrayList;

public class Array {
	public static void main(String[] args) {
		int[] numbers2 = new int[]{12, 12, 13, 21, 12, 1, 1};
		System.out.println("il numero ricorsivo è: " + mostRecurrent(numbers2));
		/*
		int[] numbers = {1, 76, 830, 102, 5, 3};
		
		int [] numbers3 = new int[2];
		numbers3[0] = 1;
		numbers3[1] = 6;
		int[] numbers4 = {45, 97, 5, 23, 8, 90, 1, 2, 86, 12};
		int n = 76;
		max(numbers);
		System.out.println("l'array contiene il numero: " + contains(n, numbers));
		Object x = "pippo";
		Object[] arrX = {"456", 789, "chiara", 4.5, "pippo123"}; 
		System.out.println("Array object contiene: " + contains(x, arrX));
		System.out.println("array è crescente: " + isCrescente(numbers));
		
		System.out.println("La media dei multipli di tre: " + mediaMultipliTre(numbers2));
		System.out.println("La media: " + media(numbers));
		String s = "java";
		String st[] = {"php", "javascript", "c", "c++"};
		addString(s, st);
		stampaZigZag(numbers4);
		
		
		int[] n1 = {1, 2, 3};
		int[] n2 = n1;
		System.out.println(Arrays.toString(n1));
		System.out.println(Arrays.toString(n2));
		String[] strings = { "stringValue" };
		Object[] objects = strings;
		System.out.println(Arrays.toString(strings));
		
		System.out.println(Arrays.toString(objects));
		String[] againStrings = (String[]) objects;
		System.out.println(Arrays.toString(againStrings));
		//againStrings[0] = new StringBuilder(); 
		//objects[0] = 2;
		
		int[] numbers = { 1, 1, 6, 2, 6 };
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length; i++)
		 System.out.print (numbers[i] + " ");
		
		ArrayList arrL = new ArrayList();
		ArrayList arrL2 = new ArrayList(10);
		ArrayList arrL3 = arrL2;
		
		//arrL2.add(1, "ciao"); //errore indexOutOfBound
		arrL.add('r');
		System.out.println(arrL);
		
		ArrayList<String> safer = new ArrayList<>();
		safer.add("sparrow");
		//safer.add(Boolean.TRUE);
		
		arrL.remove(2);
		safer.remove("sparrow");
		
		arrL.set(0, 'o');
		
		String[] array = { "hawk", "robin" }; // [hawk, robin]
		List<String> list = Arrays.asList(array); // returns fixed size list
		System.out.println(list.size()); // 2
		list.set(1, "test"); // [hawk, test]
		array[0] = "new"; // [new, test]
		for (String b : array) System.out.print(b + " "); // new test
		//list.remove(1);		//errore
		*/
		
	}
	
	public static void walk(int start, int... nums) {
		System.out.println(nums.length);
		}
	
	public static int max(int[] arr) {
		int max = 0;
		int indice = 0;
		for(int i=0; i < arr.length; i++) {
			if(arr[i] > max) { 
				max = arr[i];
				indice = i;
			}
		}
		return max;
	}
	
	public static int maxIndice(int[] arr) {
		int max = 0;
		int indice = 0;
		for(int i=0; i < arr.length; i++) {
			if(arr[i] > max) { 
				max = arr[i];
				indice = i;
			}
		}
		System.out.println(max);
		return indice;
	}
	
	public static boolean contains(int e, int[] arr) {
		boolean isContained = false;
		for (int i= 0; i < arr.length; i++) {
			if(arr[i] == e) {
				isContained = true;
				break;
			}
			else  {
				isContained = false;
			}
		}
		return isContained;
	}
	
	public static boolean contains(Object e, Object[] arr) {
		boolean isContained = false;
		for (int i= 0; i < arr.length; i++) {
			if(arr[i].equals(e)) {
				isContained = true;
				break;
			}
			else  {
				isContained = false;
			}
		}
		return isContained;
	}
	
	public static boolean isCrescente(int[] arr) {
		boolean isCrescente = false;
		for (int i= 1; i < arr.length; i++) {
			if(arr[i] > arr[i - 1]) isCrescente = true;
			else isCrescente = false;
		}
		return isCrescente;
	}
	
	
	public static int mostRecurrent(int[] arr) { //manca verifica
		int nuovaRicorrenza = 0;
		int vecchiaRicorrenza = 0;
		int numRecurrent = arr[0];
		for (int i= 0; i < arr.length; i++) {
			
			for(int j = i; j < arr.length; j++) {
				if(arr[i] == arr[j] ) {
					vecchiaRicorrenza++;
					
				}
				
			}
			if(nuovaRicorrenza< vecchiaRicorrenza) {
				nuovaRicorrenza = vecchiaRicorrenza;
				numRecurrent = arr[i];
				
			}
			vecchiaRicorrenza = 0;
		}

		return numRecurrent;
	
	}
	
	public static int mediaMultipliTre(int[] arr) {
		int somma = 0;
		int divisore = 0;
		int media = 0;
		for (int i= 0; i < arr.length; i++) {
			if(arr[i]%3 ==0) {
				somma += arr[i];
				divisore++;
			}
		}
		return media = somma/divisore;
	}
	
	public static void stampaZigZag(int[] x) {
		for (int i= 0, j = x.length-1; i <= x.length/2 && j >= x.length/2; i++, j--) {
			System.out.print(x[i] + " ");
			System.out.print(x[j] + " ");
		}
	}
	
	public static int media(int[] arr) {
		int somma = 0;
		int divisore = 0;
		int media = 0;
		for (int i= 0; i < arr.length; i++) {
			somma += arr[i];
			divisore++;
		}
		return media = somma/divisore;
	}
	
	public static String[] addString(String s, String[] str) {
		String[] nuovoArray = new String[str.length+1];
		for (int i= 0; i < nuovoArray.length; i++) {
			if(i < nuovoArray.length-1) {
				nuovoArray[i] = str[i];
			} else {
				nuovoArray[i] = s;
			}
			//nuovoArray[nuovoArray.length-1] = s;
			
		}
		System.out.println(java.util.Arrays.toString(nuovoArray));
		return nuovoArray;
	}
	
	public static long[] stampaFibonacci() {
		long[] arrFibo = new long[100];
		arrFibo[0] = 1;
		arrFibo[1] = 1;

		for (int i = 2; i < 100; i++) {
			arrFibo[i] = arrFibo[i - 1] + arrFibo[i - 2];
		}
		return arrFibo;
	}

	public static void successioneFibonacci() {
		long[] arr = new long[100];
		arr = stampaFibonacci();
		ArrayList<Long> list = new ArrayList<Long>();
		for (long x : arr) {
			list.add(x);
			System.out.println(list);
		}
	}
}
