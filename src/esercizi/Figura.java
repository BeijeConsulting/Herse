package esercizi;

	public class Figura {
	 
	
	static void stampaSpazi(int spazi) {
		for(int i=0; i<spazi; i++)
			System.out.print(" ");
	}

	public static void main(String[] args) {
		int righe = 6;
		int spazi = 3;
		int x = 1;
		int y = 6;
		
		
		for(int i = 0; i<righe; i++) {
		//	System.out.println("ciclo " + i);
			for(int j = 1; j<=x; j++)
				System.out.print(j);
			stampaSpazi(spazi);
			for(int t = y; t>=1; t--)
				System.out.print(t);
			x++;
			y--;
			System.out.print("\n");
		}
	}

}
