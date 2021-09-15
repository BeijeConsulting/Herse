package it.beije.herse.OCA;


	 public class Prove {
		 static { add(2); }
		 static void add(int num) { System.out.print(num + " "); }
		 Prove() { add(5); }
		 static { add(4); }
		 { add(6); }
		 static { new Prove(); }
		 static { add(8); }
		 public static void main(String[] args) { } }
	


