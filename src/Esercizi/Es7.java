package Esercizi;
/*Scrivere un programma StampaZigZag che, 
dato un array di 10 numeri interi contenente valori a piacere, 
ne stampa gli elementi secondo il seguente ordine: 
	il primo, l’ultimo, il secondo, il penultimo, il terzo, il terz’ultimo, ecc…*/
public class Es7 {
	public void StampaZigZag(int[] n) {
		int i,j=n.length-1;
		System.out.print(n[0]);
		for(i=1;i<j;i++) {
			System.out.print(", "+n[j]+", "+n[i]);
			j-=1;
		}
		System.out.print(", "+n[i]+"\n");
	}
}
