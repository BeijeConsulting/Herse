package esercizi;
public class Stringhe{
	
	static void SoloVocali(String name){
		int x = 0;
		name = name.toLowerCase();
		for(int i = 0; i<name.length(); i++){
			if(name.charAt(i)=='a')
				System.out.print(name.charAt(i) + " ");
			if(name.charAt(i)=='e')
				System.out.print(name.charAt(i) + " ");
			if(name.charAt(i)=='i')
				System.out.print(name.charAt(i) + " ");
			if(name.charAt(i)=='o')
				System.out.print(name.charAt(i) + " ");
			if(name.charAt(i)=='u')
				System.out.print(name.charAt(i) + " ");
		}
		
	}
	
	static int contaLettere(char c, String str){
		int count = 0;
		for(int i=0; i<str.length(); i++){
			if(str.charAt(i)==c) count++;
		}
		return count;
	}
	
	public static void main (String args[]){
	//String  [] s = {"Come ti chiami?", "come stai?", "Quanti anni hai?"};
		//SoloVocali(s);
		
	/*	char c;
		for(int i=0; i<3; i++){
			c = s[i].charAt(0);
		if(c - 'A'<=26)
			System.out.println(s[i]);
		}*/
		
		String s = "Caciocavallo";
	//	System.out.println(contaLettere('c', s));
		
		/*int i=s.length()-1;
		while( i>=0){
			System.out.print(s.charAt(i));
			i--;
		}*/
		
	//	System.out.println(args[0] + "*" + args[1] + "*" + args[2]);
		
	}
}