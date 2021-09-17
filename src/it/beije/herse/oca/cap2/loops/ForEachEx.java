package it.beije.herse.oca.cap2.loops;

public class ForEachEx{
	public void arrayLoop(){
		final String[] names = new String[3];
		names[0] = "Lisa";
		names[1] = "Kevin";
		names[2] = "Roger";
		for(String name : names) {
			System.out.print(name + ", ");
		}
	}

	public void listLoop(){
		java.util.List<String> names = new java.util.ArrayList<String>();
		names.add("Angela");
		names.add("Raymond");
		names.add("Linda");
		for(String name : names) {
			System.out.print(name + ", ");
		}
	}

	public static void main(String[] args){
		ForEachEx f = new ForEachEx();
		f.arrayLoop();
		System.out.println("");
		f.listLoop();
	}
}