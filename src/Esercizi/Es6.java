package Esercizi;
/*Scrivere un programma MediaMultipliDiTre che calcoli la media di un array di numeri interi, 
considerando i soli numeri divisibili per tre.*/
public class Es6 {
	public void MediaMultipliDiTre(int[] n) {
		int i,somma=0;
		for(i=0;i<n.length;i++)
			if(n[i]%3==0)
				somma+=n[i];
		System.out.println("La media dei numeri divisibili per tre è: "+(somma/3));
	}

}
