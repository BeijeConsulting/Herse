package esarray;


public class StampaZigZag {

	public static void main(String[] args) {
		int [] a = new int [10];
		int lunghezza = a.length;
		System.out.println(lunghezza);
		
		for(int i=0; i<a.length; i++)
			a[i] = i;
		
		for(int i = 0; i<lunghezza/2; i++) {
			System.out.print(a[i] + " ");
			System.out.print(a[lunghezza-1-i] + " ");
			
		}
		
		

	}

}
