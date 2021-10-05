
public class Test {
	static int count;
	static {count = 0; System.out.println("blocco statico con count = " + count);}
	Test(){
		System.out.println("sono nel costruttore");
		count++;
	}

	public static void main(String[] args) {
//		System.out.println("nel main " + count);
//		count++;
//		System.out.println("nel main 2 " + count);
//		Test t = new Test();
//		Test a = new Test();
//		Test r = new Test();
		System.out.println(count);

	}

}
