package Esercizi;
/*Scrivere il metodo: “public int mostRecurrent(int [] array)” , 
che trova l’elemento più ricorrente in un array. 
Il metodo restituisce l’elemento trovato. */
public class Es5 {
	public int mostRecurrent(int [] array) {
		int i,j,n=array[0],cont,contMax=0;
		for(i=0;i<array.length;i++) {
			cont=0;
			for(j=0;j<array.length;j++)
				if(array[i]==array[j])
					cont+=1;
			if(cont>contMax) {
				n=array[i];
				contMax=cont;
			}
		}
		return n;
	}

}
