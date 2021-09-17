package it.beije.herse.oca;

import java.util.Arrays;

public class EserciziArray {

	//Trovare il massimo elemento in un array
	public static int maxElem(int[] arr) {

		Arrays.sort(arr);

		return arr[arr.length-1];

	}

	//Trovare l’indice del massimo elemento in un array
	public static int maxIndValue(int[] arr) {

		int indValue = 0;

		for(int i = 0; i < arr.length - 1; i++)
			if(arr[indValue] < arr[i])
				indValue = i;

		return indValue;

	}

	//Scrivere un metodo “boolean contains(int e, int[] array)” che restituisca true se l’elemento e è presente nell’array,
	//false altrimenti. Ripetere l’esercizio con “boolean contains(Object e, Object[] array)”, quali differenze ci sono?
	public boolean contains(int e, int[] array) {

		boolean flag = false;

		if(Arrays.binarySearch(array, e) >= 0)
			flag = true;

		return flag;

	}

	public boolean contains(Object e, Object[] array) {

		boolean flag = false;

		if(Arrays.binarySearch(array, e) >= 0)
			flag = true;

		return flag;

	}

	//Verificare la sequenza crescente di un array. Il metodo “boolean isCrescente(int [] array)” restituisce true se tutti
	//gli elementi dell’array passato sono in ordine crescente, false altrimenti
	public boolean isCrescente(int[] array) {

		boolean flag = false;

		int[] arrClone = array.clone();
		Arrays.sort(arrClone);

		if(Arrays.equals(arrClone, array))
			flag = true;

		return flag;

	}

	//Scrivere il metodo: “public int mostRecurrent(int [] array)” , che trova l’elemento più ricorrente in un array.
	//Il metodo restituisce l’elemento trovato.
	public int mostRecurrent(int[] array) {

		int[] count = new int[array.length];

		for(int i = 0; i < array.length-2; i++)
			for(int j = i+1; j < array.length-1; j++)
				if(array[i] == array[j])
					count[i]++;

		return array[EserciziArray.maxIndValue(count)];

	}

	//Scrivere un programma MediaMultipliDiTre che calcoli la media di un array di numeri interi,
	//considerando i soli numeri divisibili per tre.
	public static float MediaMultipliDiTre(int[] arr) {

		float media = 0;
		int count = 0;

		for(int x : arr) {

			if(x % 3 == 0) {

				media += x;
				count++;

			}

		}

		return (float)(media/count);

	}
	
	//Scrivere un programma StampaZigZag che, dato un array di 10 numeri interi contenente
	//valori a piacere, ne stampa gli elementi secondo il seguente ordine: il primo, l’ultimo, il secondo, il penultimo, il terzo, il terz’ultimo, ecc…
	public static void StampaZigZag(int[] arr) {
		
		int j = 0;
		int z = arr.length-1;
		
		for(int i = 0; i < arr.length; i++) {
			
			if(i % 2 == 0) {
				System.out.println(arr[j]);
				j++;
			}else {
				System.out.println(arr[z]);
				z--;
			}
			
		}
		
	}

	//Scrivere un programma Media che calcoli la media di un array di numeri interi
	public static float media(int[] arr) {

		float media = 0;

		for(int x : arr)
			media += x;

		return (float)(media/arr.length);

	}
	
	//Scrivere il metodo: “public String [] addString(String s, String[] a)”, che accetta
	//come parametri una stringa ed un array di stringhe. Restituisce un nuovo array,
	//identico ad array, aggiungendo però, come ultimo elemento, la stringa s.
	public String [] addString(String s, String[] a) {
		String[] sl = new String[a.length+1];
		for(int i = 0; i < a.length; i++) {
			sl[i] = a[i];
		}
		sl[a.length] = s;
		return  sl;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EserciziArray e = new EserciziArray();
		System.out.println(EserciziArray.maxElem(new int[] {4,7,2,9,1}));
		System.out.println();
		System.out.println(EserciziArray.maxIndValue(new int[] {4,7,2,9,1}));
		System.out.println();
		System.out.println(e.contains(3,new int[] {4,7,2,9,1}));
		System.out.println(e.contains(9,new int[] {4,7,2,9,1}));
		System.out.println();
		System.out.println(e.contains(Integer.valueOf(3),new Integer[] {4,7,2,9,1}));
		System.out.println(e.contains(Integer.valueOf(9),new Integer[] {4,7,2,9,1}));
		System.out.println();
		System.out.println(e.isCrescente(new int[] {4,7,2,9,1}));
		System.out.println(e.isCrescente(new int[] {1,2,3,4,5}));
		System.out.println();
		System.out.println(e.mostRecurrent(new int[] {4,7,2,9,1,9,5,6,9,4,6,9,3,6,9}));
		System.out.println();
		System.out.println(EserciziArray.MediaMultipliDiTre(new int[] {4,7,2,9,1,12,8,6,2,5,3,10}));
		System.out.println();
		System.out.println(EserciziArray.media(new int[] {4,7,2,9,1,12,8,6,2,5,3,10}));
		System.out.println();
		EserciziArray.StampaZigZag(new int[] {1,3,5,7,8,6,4,2});
		System.out.println();
		
		String[] s = e.addString("Savino", new String[]{"Ciao,","sono"});
		
		for(String str : s)
			System.out.println(str);

	}

}
