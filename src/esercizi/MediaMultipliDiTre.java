package esercizi;

public class MediaMultipliDiTre {

	public static void main(String[] args) {
		int[] a = {3, 30, 5, 3};
		double media = 0;
		int count = 0;
		for(int i : a) {
			if((i%3) == 0) {
				media += i;
				count++;
			}
		}
		media = media/count;
		System.out.println(media);
		

	}

}
