package Esercizi;
public class Es2 {
	void MaxIndex(int[] n) {
		int i,indice=0,max=n[0];
		for(i=1;i<n.length;i++)
			if(n[i]>max) {
				max=n[i];
				indice=i;
			}
		System.out.println("Il numero più grande nel vettore si trova in posizione :"+indice);
}
}
