package Arrays;
/*
 * Scrivere il metodo: “public String [] addString(String s, String[] a)”, che accetta come parametri una stringa ed un array di stringhe. 
 * Restituisce un nuovo array, identico ad array, aggiungendo però, come ultimo elemento, la stringa s.
 */

public class es9 {
	
	public static void main (String args[]) {
		String str = "ciao";
		String[] arStr = new String[] {"a", "b", "c", "d", "f"};
		new es9().addString(str, arStr);
	}

	public String[] addString(String s, String[] a) {
		String[] newAr = new String[a.length+1];
		for(int i = 0; i <= a.length; i++) {
			if(i < a.length)
				newAr[i] = a[i];
			else 
				newAr[i] = s;
		}
		return newAr;
	}
		
	
}
