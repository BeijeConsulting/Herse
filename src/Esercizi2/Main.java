package Esercizi2;
public class Main {

	public static void main(String[] args) {
		Ese ese=new Ese();
		System.out.println(ese.substring("ciao", 2));
		System.out.println(ese.substring("ciao", 1,2));
		System.out.println(ese.toLowerCase("CiAo"));
		System.out.println(ese.toUpperCase("CiAo"));
		if(ese.equals("ciccio", "ciccio"))
			System.out.println("Uguali");
		else
			System.out.println("Diverse");
		
		if(ese.equalsIgnoreCase("porta", "PORTA"))
			System.out.println("Uguali");
		else
			System.out.println("Diverse");
		
		if(ese.contains("anna", "capanna"))
			System.out.println("La stringa è contenuta");
		else
			System.out.println("La stringa NON è contenuta");
		
		if(ese.startsWith("capanna", "cap"))
			System.out.println("La stringa inizia ");
		else
			System.out.println("La stringa NON inizia");
		
		if(ese.endsWith("capanna", "anna"))
			System.out.println("La stringa finisce ");
		else
			System.out.println("La stringa NON finisce");



	}

}
