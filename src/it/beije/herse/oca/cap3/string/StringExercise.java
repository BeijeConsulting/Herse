package it.beije.herse.oca.cap3.string;

public class StringExercise{
	String[] vocali = {"A", "E", "I", "O", "U"};
	
	public void soloVocali(String s){
		for(String cur : vocali) if(s.toUpperCase().contains(cur)) System.out.println(cur);
	}

	public void stampaMaiuscole(String[] a){
		for(String cur : a) if(cur.startsWith(cur.toUpperCase().substring(0,1))) System.out.println(cur);
	}

	public int contaLettera(char c, String str){
		int count = 0;
		if(str.indexOf(c) == -1) count=-1;
		else{
			for(int i=0;i<str.length();i++){
				if(c == str.charAt(i)) count++;
			}
		}
		return count;
	}

	public void contrario(String s){
		int len = s.length()-1;
		char[] rev = new char[len+1];
		for(int i=0;i<s.length();i++){
			rev[i] = s.charAt(len);
			len--;
		}
		for(int i=0;i<s.length();i++) System.out.print(rev[i]);
		System.out.print("\n");
	}

	public void concatena(String[] s){
		String result = "";
		for(int i=0;i<s.length;i++) result += s[i]+"*";
		System.out.println(result);
	}

	public void stampaSetter(String nome){
		String nomeLowerCase = nome.toLowerCase();
		String nomeCapitalize = nomeLowerCase.replace(nomeLowerCase.substring(0,1), 
					nomeLowerCase.substring(0,1).toUpperCase());	

		System.out.println("public void set"+nomeCapitalize+"(String "+nomeLowerCase+"){");
		System.out.println("\t"+"this."+nomeLowerCase+" = "+nomeLowerCase+";");
		System.out.println("}");
	}

	public void stampaGetter(String nome){
		String nomeLowerCase = nome.toLowerCase();
		String nomeCapitalize = nomeLowerCase.replace(nomeLowerCase.substring(0,1), 
					nomeLowerCase.substring(0,1).toUpperCase());	

		System.out.println("public String get"+nomeCapitalize+"(){");
		System.out.println("\t"+"return "+nomeLowerCase+";");
		System.out.println("}");
	}
	
	public static void main(String[] args){
		StringExercise s = new StringExercise();

		s.soloVocali("Stringhe");
		System.out.print("\n");

		String[] s1 = {"stringa", "Vocali", "CONSONANTI"};
		s.stampaMaiuscole(s1);
		System.out.print("\n");

		System.out.println(s.contaLettera('e', "Lettera"));
		System.out.print("\n");

		s.contrario("Hello World!");
		System.out.print("\n");

		String[] s2 = {"Gatto", "Cane", "Topo"};
		s.concatena(s2);
		System.out.print("\n");
		
		s.stampaSetter("NOME");
		System.out.print("\n");
		
		s.stampaGetter("nome");
	}
}