package Esercizi;

import java.util.Arrays;

public class Es4 {
	boolean isCrescente(int[] n){
		int[] e=new int [n.length];
		int i;
		for(i=0;i<n.length;i++)
			e[i]=n[i];
		Arrays.sort(e);
		for(i=0;i<n.length;i++) 
			if(n[i]!=e[i])
				return false;
	return true;
		
	}

}
