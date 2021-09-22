package Esercizi;
public class Es1 {
	void MaxArray(int[] n) {
		int i,max=n[0];
		for(i=1;i<n.length;i++)
			if(n[i]>max) 
				max=n[i];
		System.out.println("Il massimoo nel vettore è "+max);
	}
}
