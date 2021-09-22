package Esercizi;
/*Scrivere un programma Media che calcoli la media di un array di numeri interi*/
public class Es8 {
	public void Media(int[] n) {
		int i;
		double somma=0;
		for(i=0;i<n.length;i++)
			somma+=n[i];
		System.out.print("La media è: "+somma/n.length+"\n");
	}

}
