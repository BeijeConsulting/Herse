package Esercizi2;

public class Test {
	//public class YetMoreInitializationOrder {
		 static { add(2); }
		 static void add(int num) { System.out.print(num + " "); }
		 Test() { add(5); }
		 static { add(4); }
		 static { add(6); }
		 static { new Test();}

	public static void main(String args[]) {
		
	}

}
