package it.beije.herse.oca.cap3.array;

import java.util.Arrays;

public class ArrayExercise {
	//Trovare il massimo elemento in un array (o il minimo)
	public void findMaxAndMin(int[] a) {
		int max=0;
		int min=Integer.MAX_VALUE;
		
		for(int i : a) {
			if(i>max) max = i;
			if(i<min) min = i;
		}
		
		System.out.println("Max: "+max);
		System.out.println("Min: "+min);
	}
	
	//Trovare l’indice del massimo elemento in un array (o il minimo)
	public void findMaxAndMinIndex(int[] a) {
		int max=0;
		int maxIndex=-1;
		
		int min=Integer.MAX_VALUE;
		int minIndex=-1;
		
		for(int i=0;i<a.length;i++) {
			if(a[i]>max) {
				max = a[i];
				maxIndex = i;
			}
			if(a[i]<min) {
				min = a[i];
				minIndex = i;
			}
		}
		
		System.out.println("Max's index: "+maxIndex);
		System.out.println("Min's index: "+minIndex);
	}
	
	//Scrivere un metodo “boolean contains(int e, int[] array)” che restituisca true se l’elemento e è presente nell’array, 
	//false altrimenti. Ripetere l’esercizio con “boolean contains(Object e, Object[] array)”, quali differenze ci sono?
	public boolean containsInt(int e, int[] array) {
		for(int i : array) {
			if(i==e) return true;
		}
		return false;
	}
	
	public boolean containsObject(Object e, Object[] array) {
		for(Object i : array) {
			if(i==e) return true;
		}
		return false;
	}
	
	//Verificare la sequenza crescente di un array. Il metodo “boolean isCrescente(int [] array)” 
	//restituisce true se tutti gli elementi dell’array passato sono in ordine crescente, false altrimenti.
	public boolean isCrescente(int[] array) {
		for(int i=0;i<array.length;i++) {
			for(int j=i+1;j<array.length;j++) {
				if(array[i]>array[j]) return false;
			}
		}
		return true;
	}
	
	//Scrivere il metodo: “public int mostRecurrent(int [] array)” , 
	//che trova l’elemento più ricorrente in un array. Il metodo restituisce l’elemento trovato.
	public int mostRecurrent(int[] array) {
		int index=-1;
		int max = 0;
		for(int i=0;i<array.length;i++) {
			int count = 0;
			for(int j=i;j<array.length;j++) {
				if(array[i] == array[j]) count++;
			}
			if(count>max) {
				max = count;
				index = i;
			}
		}
		return array[index];
	}
	
	//Scrivere un programma MediaMultipliDiTre che calcoli la media di un array di numeri interi, 
	//considerando i soli numeri divisibili per tre.
	public double mediaMultipliTre(int[] array) {
		int somma = 0;
		int count = 0;
		for(int i : array) {
			if(i%3==0) {
				somma += i;
				count++;
			}
		}
		return (double) somma/count;
	}
	
	//Scrivere un programma StampaZigZag che, dato un array di 10 numeri interi contenente valori a piacere, 
	//ne stampa gli elementi secondo il seguente ordine: il primo, l’ultimo, il secondo, il penultimo, il terzo, il terz’ultimo, ecc…
	public void stampaZigZag(int[] array) {
		int j=array.length-1;
		for(int i=0;i<array.length/2;i++) {
			System.out.print(array[i]+" "+array[j]+" ");
			j--;
		}
		if(array.length%2!=0) System.out.println(array[array.length/2+1]); 
	}
	
	//Scrivere un programma Media che calcoli la media di un array di numeri interi
	public double media(int[] array) {
		int somma = 0;
		for(int i : array) somma += i;
		return (double) somma/array.length;
	}
	
	//Scrivere il metodo: “public String [] addString(String s, String[] a)”, 
	//che accetta come parametri una stringa ed un array di stringhe. 
	//Restituisce un nuovo array, identico ad array, aggiungendo però, come ultimo elemento, la stringa s.
	public String[] addString(String s, String[] a) {
		String[] newArray = new String[a.length+1];
		for(int i=0;i<a.length;i++) {
			newArray[i] = a[i];
		}
		newArray[a.length] = s;
		return newArray;
	}
	
	public static void main(String[] args) {
		ArrayExercise e = new ArrayExercise();
		int[] intArray = {11, 32, 7, 42, 77, 11, 57, 12, 8, 6};
		int[] sortedArray = {7, 11, 32, 42, 77};
		Object[] objArray = {"Abc", "Array", "object", "Hello World"};
		String[] stringArray = {"Abc", "Array", "object", "Hello World"};
		
		e.findMaxAndMin(intArray);
		System.out.print("\n");
		
		e.findMaxAndMinIndex(intArray);
		System.out.print("\n");
		
		String s1 = new String("object");
		System.out.println("containsInt's result: "+e.containsInt(32, intArray));
		System.out.println("containsObject's result: "+e.containsObject(s1, objArray));
		System.out.print("\n");
		
		System.out.println("intArray isCrescente's result: "+e.isCrescente(intArray));
		System.out.println("sortedArray isCrescente's result: "+e.isCrescente(sortedArray));
		System.out.print("\n");
		
		System.out.println("intArray's mostRecurrent: "+e.mostRecurrent(intArray));
		System.out.print("\n");
		
		System.out.println("intArray's mediaMultipliTre: "+e.mediaMultipliTre(intArray));
		System.out.print("\n");
		
		e.stampaZigZag(intArray);
		System.out.println("\n");
		
		System.out.println("intArray's media: "+e.media(intArray));
		System.out.print("\n");
		
		String s2 = "String";
		System.out.print("stringArray + "+s2+": "+Arrays.toString(e.addString(s2, stringArray)));
		System.out.println("\n");
	}
}
