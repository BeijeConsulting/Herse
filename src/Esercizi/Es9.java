package Esercizi;
/*Scrivere il metodo: “public String [] addString(String s, String[] a)”, 
 * che accetta come parametri una stringa ed un array di stringhe. 
 * Restituisce un nuovo array, identico ad array, aggiungendo però, 
 * come ultimo elemento, la stringa s.*/
public class Es9 {
	public String[] addString(String s, String[] a) {
		int n=a.length+1,i;
		String stringa[] = new String[n];
		for(i=0;i<a.length;i++)
			stringa[i]=a[i];
		stringa[i]=s;
		return stringa;
	}
}
