package esercizi;

public class MorraCinese {
	
		static  boolean test(String s) {
		boolean b = false;
		if(s.equalsIgnoreCase("forbice")) {
			b = true;
		}
		else if(s.equalsIgnoreCase("sasso")) {
			b = true;
		}
		else if(s.equalsIgnoreCase("carta")) {
		b = true;
		}
		return b;
	}

	public static void main(String[] args) {
		String s1 = args[0];
		String s2 = args[1];
		
		String vince1 = "Vince giocatore 1";
		String vince2 = "Vince giocatore 2";
		
		if(test(s1)==false) {
			System.out.println("prima stringa errata");
			return;
		}
		else if(test(s2)==false) {
			System.out.println("seconda stringa errata");
			return;
		}
		
		System.out.println("giocatore 1 " + s1 + " giocatore 2 " + s2);
		
		if(s1.equals(s2)) {
			System.out.println("pari");
			return;
		}
		
		else if(s1.equalsIgnoreCase("forbice")) {
			if(s2.equals("carta")) System.out.println(vince1);
			else System.out.println(vince2);
		}
		
		else if(s1.equalsIgnoreCase("sasso")) {
			if(s2.equalsIgnoreCase("carta")) System.out.println(vince2);
			else System.out.println(vince1);
		}
		
		else if(s2.equalsIgnoreCase("sasso")) System.out.println(vince1);
		else System.out.println(vince2);

	}

}
