package it.beije.herse.oca;
import javax.sound.midi.Soundbank;
import java.util.SortedMap;

public class StringUtil {

	// Di seguito i metodi da implementare in un'ipotetica classe StringUtil:

	public static String substring(String s, int beginIndex){

		int num = s.length()-1;
		StringBuilder builder = new StringBuilder(num-beginIndex+1);
		for (int i=beginIndex; i<=num;i++){
			builder.append(s.charAt(i));
		}
		return builder.toString();
	}
	public String substring(String s, int beginIndex, int endIndex){

		StringBuilder builder = new StringBuilder(endIndex-beginIndex);
		for (int i=beginIndex; i<endIndex;i++){
			builder.append(s.charAt(i));
		}
		return builder.toString();

	}
	public String toUpperCase(String s){

		StringBuilder builder = new StringBuilder();
		for(int i =0; i< s.length(); i++){
			int lettera= (int)s.charAt(i);
			if(lettera>96){
				int z = (int) s.charAt(i) - 32;
				builder.append((char)z);
			} else {
				builder.append(s.charAt(i));
			}

		}
		return builder.toString();
	}

	public String toLowerCase(String s){

		StringBuilder builder = new StringBuilder();
		for(int i =0; i< s.length(); i++){
			int lettera= (int)s.charAt(i);
			if(lettera<96){
				int z = (int) s.charAt(i) + 32;
				builder.append((char)z);
			} else {
				builder.append(s.charAt(i));
			}

		}
		return builder.toString();
	}

	public boolean equals(String s1, String s2) {
		boolean yesno=false;
		if (s1.length() == s2.length()) {
			yesno = true ;
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) != s2.charAt(i)) {
					yesno = false;
					System.out.println("prova override");
					break;
				}
			}
		}
		return yesno;
	}

	public boolean equalsIgnoreCase(String s1, String s2){
		StringUtil s = new StringUtil();
		String s3 =  s.toLowerCase(s1);
		String s4 = s.toLowerCase(s2);
		boolean uguale = s.equals(s3,s4);
		return uguale;
	}

	public boolean contains(String s, String str){
		int i = 0;
		int prog = s.indexOf(str.charAt(0));
		boolean yesno = false;

		if(prog!=-1){
			yesno = true;
			while (yesno){

				if(s.charAt(prog)!=str.charAt(i)) {
					yesno=!yesno;
				}
				prog++;
				i++;
				yesno = i<str.length() && i<s.length() ? true : false;
			}
		}
		return yesno;
	}

	public boolean startsWith(String s, String prefix){
		boolean starsWith=false;
		if(s.contains(prefix)){
			starsWith = s.indexOf(prefix.charAt(0))==0 ? true : false;
		}
		return starsWith;
	}

	public boolean endsWith(String s, String suffix){
		boolean endsWith=false;
		if(s.contains(suffix)){
			endsWith = s.indexOf(suffix.charAt(0))==(s.length()- suffix.length()) ? true : false;
		}
		return endsWith;
	}

	public String replace(String s, char oldChar, char newChar) {

		StringBuilder builder = new StringBuilder(s.length());
		if (s.indexOf(oldChar) != -1) {
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == oldChar) {
					builder.append(newChar);
				} else {
					builder.append(s.charAt(i));
				}
			}
			return builder.toString();
		}
		return "ERRORE";
	}

	public String trim(String s) {

		StringBuilder sb = new StringBuilder(s);


		while(sb.indexOf(" ")>-1 || sb.indexOf("\t")>-1 || sb.indexOf("\n")>-1) {

			if(sb.indexOf(" ")>-1)  sb.delete(sb.indexOf(" "),sb.indexOf(" ")+1);   
			if(sb.indexOf("\t")>-1) sb.delete(sb.indexOf("\t"),sb.indexOf("\t")+1); 
			if(sb.indexOf("\n")>-1) sb.delete(sb.indexOf("\n"),sb.indexOf("\n")+1);

		}

		return sb.toString();
	}







	public static void main(String[] args) {
		// substring 1
		String d = "MariAnna";
		System.out.println(substring(d,4));
		// substring 2
		StringUtil su = new StringUtil();
		String sub2 = su.substring("Pappagallo",5,10);
		System.out.println(sub2);
		// toUpperCase
		String upper = su.toUpperCase("DiGerire");
		System.out.println(upper);
		// toLowerCase
		String lower = su.toLowerCase("DiGerire");
		System.out.println(lower);
		// equals()
		System.out.println(su.equals("pippo","poppi"));
		// equalsIgnoreCase
		System.out.println(su.equalsIgnoreCase("bun","ban"));
		// boolean contains(String s, String str)
		String g = new String("hello");

		System.out.println("Contains");
		System.out.println(g.contains("hellos"));

		// mio contains
		System.out.println("Mio contains");
		System.out.println(su.contains("hello","hellos"));

		// start
		System.out.println("Mio starsWith");
		System.out.println(su.startsWith("hello","he"));
		System.out.println(su.startsWith("hello","hr"));

		// end
		System.out.println("Mio endsWith");
		System.out.println(su.endsWith("hello","llo"));
		System.out.println(su.endsWith("hello","loo"));

		// replace
		System.out.println("Replace");
		System.out.println(su.replace("Hello",'H','B'));
		
		// trim
		System.out.println("Trim");
		System.out.println(su.trim("\tH el\nl o"));

	}
}
