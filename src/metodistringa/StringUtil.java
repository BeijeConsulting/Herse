package metodistringa;

public class StringUtil {
	
	static String substring(String s, int beginIndex) {
		String a = new String();
		if(s == null)
			return a;
		for(int i = beginIndex; i<s.length(); i++)
			a = a + s.charAt(i);
		return a;
	}
	
	static String substring(String s, int beginIndex, int endIndex) {
		if(s==null)
			return s;
		String a = new String();
		for(int i = beginIndex; i<endIndex; i++)
			a = a + s.charAt(i);
		return a;
	}
	
	static String toLowerCase(String s) {
		if(s==null)
			return s;
		int l = 'a' - 'A';
		char c;
		String a = new String();
		for(int i=0; i<s.length(); i++){
			c = s.charAt(i);
			/*if(c>='a')
				a = a + c;
			else {
				c =(char) (c + l);
				a = a + c;*/
			if(c>='A' && c<='Z') {
				c = (char) (c+l);
				a = a+c;	
			}
			else {
				a = a + c;
			}
		}
		return a;
	}
	static String toUpperCase(String s) {
		if(s==null)
			return s;
		int l = 'a' - 'A';
		char c;
		String a = new String();
		for(int i=0; i<s.length(); i++){
			c = s.charAt(i);
			if(c<='Z')
				a = a + c;
			else {
				c =(char) (c - l);
				a = a + c;
			}
		}
		return a;
	}
	
	static boolean equals(String s1, String s2) {
		if(s1 == null )
			return false;
		if(s2 == null)
			return false;
		if(s1.length()!=s2.length())
			return false;
		boolean b = true;
		if( s1.length() != s2.length())
			return false;
		for(int i=0; i<s1.length(); i++)
			if(s1.charAt(i) != s2.charAt(i)) {
				b = false;
				break;
			}
		return b;
	}
	static boolean equalsIgnoreCase(String s1, String s2) {
		s1 = toLowerCase(s1);
		s2 = toLowerCase(s2);
		return equals(s1, s2);
	}
	static boolean contains(String s, String str) {
		boolean b = false;
		String sub = new String();
		for(int i = 0; (i<s.length()) && (b == false); i++) {
			sub = substring(s, i, str.length()+i);
		//	System.out.println(sub);
			if(equals(str, sub)) b = true;
		}
		return b;
		
	}
	static boolean startsWith(String s, String prefix) {
		boolean b = false;
		String sub = substring(s, 0, prefix.length());
		b = equals(sub, prefix);
		return b;
	}
	
	static boolean endsWith(String s, String suffix) {
		String sub = substring(s, s.length() - suffix.length(), s.length());
		System.out.println(sub);
		return equals(sub, suffix);
	}
	
	static String replace(String s, char oldChar, char newChar) {
		String a = new String();
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)==oldChar) { 
				a = a + newChar;
			}
			else {
				a = a + s.charAt(i);
			}
		
		}
		return a;	
	}
	static String replace(String s, String oldChar, String newChar) {
		String a = new String();
		String sub = new String();
		for(int i=0; i<s.length(); i++) {
			if(i>(s.length() - oldChar.length())) {
				a = a + s.charAt(i);
			}
			else {
			sub = substring(s, i, oldChar.length() + i);
		//	System.out.println(i + " " + sub);
				if(equals(sub, oldChar)) {
					a = a + newChar;
					i = i + oldChar.length()-1;
				}
				else {
					a = a + s.charAt(i);
				}
			}
		}
		return a;
	}
	static String trim(String s) {
		String a = new String();
		for(int i = 0; i<s.length(); i++) {
			if(s.charAt(i)>' ') {
				a = substring(s, i);
				break;
			}
			
		}
//		System.out.println("prova" + a);
		for(int i = a.length(); i>0; i--) {
			if(s.charAt(i)<=' ') {
				continue;
			}
			else {
				a = substring(a, 0, i);
			}
			
		}
		return a;
	}

	public static void main(String[] args) {
	//	String s = "		Animals\t\n";
	//	String q = "		Animals";
	//	String r = "Ciao mondo e ciao Caciocavallo";
	//	String t = "Ciao mondo e ciao Caciocavallo";
	//	s = substring(s, 3);
	//	s = substring(s, 3, 4);
	//	s = toLowerCase(s);
	//	s = toUpperCase(s);
	//	System.out.println(substring(r , 7));
	//	System.out.println(r.substring(7));
	//	System.out.println(substring(r, 7, 8));
	//	System.out.println(r.substring(7,8));
	//	System.out.println(toUpperCase(r) + " " + toLowerCase(r));
	//	System.out.println(r.toUpperCase() + " " + r.toLowerCase());
	//	System.out.println(equals(s, q));
	//	System.out.println(s.equals(q));
	//	System.out.println(equals(s, "animals"));
	//	System.out.println(s);
	//	System.out.println(equalsIgnoreCase(s, "ANIMAls"));
	//	System.out.println(contains(s, "mal"));
	//	System.out.println(startsWith(s, "ani"));
	//	System.out.println(endsWith(s, "s"));
	//	System.out.println(replace(s, 'a', '3'));
	//	System.out.println(replace(s, "ni", "**"));
	//	System.out.println(trim("\t   ciao    \t"));
	//	System.out.println(s.trim());
		
	/**Prova dello String pool*/
	/*	String s1 = "ciao";
		String s2 = "ciao";
		String s3 = new String("ciao");
		String s4 = new String("ciao");
		boolean b1 = s1==s2;
		boolean b2 = s3==s4;
		System.out.println(b2);
		System.out.println(s3.equals(s4));*/
		
		boolean b = true;
		String s = "fluffy";
		String s1 = new String ("fluffy");
		System.out.println(s.equals(s1));
		
		
		char c = 'c';
		c++;
		char f = c;
	}

}
