package esercizi;

public class Media {
		int [] a = {15, 123, 277, 185, 88};
		
		public double media() {
			int l = a.length;
			double m = 0;
			for(int i : a)
				m += i;
			return m/l;
		}

	public static void main(String[] args) {
		Media c = new Media();
		System.out.println(c.media());
	}

}
