package it.beije.herse.oca;
public class ProveVarie {

	public static String glide(String s) {
		return "1";
	}
	public static String glide(String... s){
		return "2";
	}
	public static String glide(Object s){
		return "3";
	}
	public static String glide(String s, String t){
		return "4";
	}

	public static void main(String[] args) {

		System.out.print(glide("a", "b", "c"));
	}
}