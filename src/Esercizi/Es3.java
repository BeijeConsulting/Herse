package Esercizi;
public class Es3 {
	boolean contains(int e,int[] n ) {
		int i;
		for(i=0;i<n.length;i++)
			if(n[i]==e)
				return true;
		return false;
	}
}
