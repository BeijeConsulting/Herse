package esarray;

public class AggiungiStringa {
	
	static public String [] addString(String s, String[] a) {
		String [] newa = new String [a.length+1];
		for(int i = 0; i<a.length; i++) {
			newa[i] = a[i];
		}
		newa[newa.length-1] = s;
		return newa;
	}

	public static void main(String[] args) {
		String [] s = new String [3];
		String [] sb = new String[150];
		s[0] = new String("Ciao");
		s[1] = new String("mamma");
		s[2] = new String("guarda");
		sb = addString("come mi diverto", s);
		for(String i : sb)
			System.out.print(i + " ");
		
	}

}
