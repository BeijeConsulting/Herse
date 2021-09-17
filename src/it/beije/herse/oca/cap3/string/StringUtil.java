package it.beije.herse.oca.cap3.string;

// TUTTI I METODI DEVONO ESSERE STATIC
// SI POSSONO USARE charAt(), indexOf(), length()
public class StringUtil {
	private static char[] array;
	
	public static String substring(String s, int beginIndex) {
		array = new char[s.length()-beginIndex];
		int c = 0;
		for(int i=beginIndex;i<s.length();i++) {
			array[c] = s.charAt(i);
			c++;
		}
		return new String(array);
	}
	
	public static String substring(String s, int beginIndex, int endIndex) {
		array = new char[endIndex-beginIndex];
		int c = 0;
		for(int i=beginIndex;i<endIndex;i++) {
			array[c] = s.charAt(i);
			c++;
		}
		return new String(array);
	}
	
	// Upper case => 65(A) a 90(Z)
	// Lower case => 97(a) a 122(z)
	public static String toLowerCase(String s) {
		array = new char[s.length()];
		for(int i=0;i<array.length;i++) {
			int l =  s.charAt(i);
			if(l>=65 && l<=90) l += 32;
			array[i] = (char) l;
		}
		return new String(array);
	}
	
	public static String toUpperCase(String s) {
		array = new char[s.length()];
		for(int i=0;i<array.length;i++) {
			int l =  s.charAt(i);
			if(l>=97 && l<=122) l -= 32;
			array[i] = (char) l;
		}
		return new String(array);
	}
	
	public static boolean equals(String s1, String s2) {
		if(s1.length() != s2.length()) return false;
		
		for(int i=0;i<s1.length();i++) {
			if(s1.charAt(i) != s2.charAt(i)) return false;
		}
		
		return true;
	}
	
	public static boolean equalsIgnoreCase(String s1, String s2) {
		if(s1.length() != s2.length()) return false;
		
		for(int i=0;i<s1.length();i++) {
			if(StringUtil.toLowerCase(s1).charAt(i) != StringUtil.toLowerCase(s2).charAt(i)) return false;
		}
		
		return true;
	}
	
	public static boolean containsWithOutIndexOf(String s, String str) {
		OUT: for(int i=0;i<s.length();i++) {
			if(s.charAt(i) == str.charAt(0)) {
				int c = i;
				for(int j=0;j<str.length();j++) {
					if(s.charAt(c) != str.charAt(j)) continue OUT;
					c++;
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean contains(String s, String str) {
		if(s.indexOf(str) != -1) return true;
		return false;
	}
	
	public static boolean startsWith(String s, String prefix) {
		for(int i=0;i<prefix.length();i++) {
			if(s.charAt(i) != prefix.charAt(i)) return false;
		}
		return true;
	}
	
	public static boolean endsWith(String s, String suffix) {
		int c = s.length()-suffix.length();
		for(int i=0;i<suffix.length();i++) {
			if(s.charAt(c) != suffix.charAt(i)) return false;
			c++;
		}
		return true;
	}
	
	public static String replace(String s, char oldChar, char newChar) {
		array = new char[s.length()];
		for(int i=0;i<array.length;i++) {
			if(s.charAt(i) == oldChar) array[i] = newChar;
			else array[i] = s.charAt(i);
		}
		return new String(array);
	}
	
	public static String trim(String s) {
		int whiteSpaces = 0;
		for(int i=0;i<s.length();i++)
			if(s.charAt(i)==' ' || s.charAt(i)=='\t' || s.charAt(i)=='\n' || s.charAt(i)=='\r')
				whiteSpaces++;
		
		if(whiteSpaces != 0) {
			array = new char[s.length()-whiteSpaces];
			int j=0;
			for(int i=0;i<s.length();i++) {
				if(s.charAt(i)!=' ' && s.charAt(i)!='\t' && s.charAt(i)!='\n' && s.charAt(i)!='\r') { 
					array[j] = s.charAt(i);
					j++;
				}
			}
			return new String(array);
		}
		return s;
	}
	
	public static String replace(String s, String oldChar, String newChar) {
		int nReplace = 0;
		int beginIndex = s.indexOf(oldChar, 0);
		while(beginIndex!=-1) {
			nReplace++;
			beginIndex = s.indexOf(oldChar, beginIndex+1);
		}
		if(nReplace==0) return s;
		
		array = new char[s.length()-(oldChar.length()-newChar.length())*nReplace];
		
		if(oldChar.length() == newChar.length()) {
			for(int i=0;i<array.length;i++) array[i] = s.charAt(i);
			
			int replaceStart = s.indexOf(oldChar);
			while(replaceStart!=-1) {
				int oldCharIndex = replaceStart;
				for(int k=0;k<newChar.length();k++) {
					array[oldCharIndex] = newChar.charAt(k);
					oldCharIndex++;
				}
				replaceStart = s.indexOf(oldChar, replaceStart+1);
			}
		}
		else if(oldChar.length() > newChar.length()) {
			char[] a = new char[s.length()];
			for(int i=0;i<a.length;i++) a[i] = s.charAt(i);
			
			int replaceStart = s.indexOf(oldChar);
			while(replaceStart!=-1) {
				int oldCharIndex = replaceStart;
				for(int k=0;k<oldChar.length();k++) {
					if(k<newChar.length()) a[oldCharIndex] = newChar.charAt(k);
					else a[oldCharIndex] = 0;
					oldCharIndex++;
				}
				replaceStart = s.indexOf(oldChar, replaceStart+1);
			}
			
			int x=0;
			for(int i=0;i<a.length;i++) {
				if(a[i]!=0) {
					array[x] = a[i];
					x++;
				}
			}
		}
		else {
			int replaceStart = s.indexOf(oldChar);
			int stringIndex = 0;
			int replaceCount = 0;
			
			for(int i=0;i<array.length;i++) {
				if(i == replaceStart) {
					int newCharIndex = 0;
					for(int j=i;j<(replaceStart+newChar.length());j++) {
						array[j] = newChar.charAt(newCharIndex);
						newCharIndex++;
						i = j;
					}
					stringIndex += oldChar.length()-1;
					replaceCount++;
					replaceStart = s.indexOf(oldChar, i+1) + (newChar.length() - oldChar.length())*replaceCount;
				}
				else array[i] = s.charAt(stringIndex);
				stringIndex++;
			}
		}
		return new String(array);
	}
	
	public static void main(String[] args) {
		System.out.println("substring(String s, int beginIndex): "+StringUtil.substring("Hello World", 4));
		System.out.print("\n");
		
		System.out.println("substring(String s, int beginIndex, int endIndex): "+StringUtil.substring("Hello World", 3, 8));
		System.out.print("\n");
		
		System.out.println("toLowerCase(String s): "+StringUtil.toLowerCase("Hello World"));
		System.out.print("\n");
		
		System.out.println("toUpperCase(String s): "+StringUtil.toUpperCase("Hello World"));
		System.out.print("\n");
		
		System.out.println("equals(String s1, String s2): "+StringUtil.equals("hello world", "HELLO WORLD"));
		System.out.print("\n");
		
		System.out.println("equalsIgnoreCase(String s1, String s2): "+StringUtil.equalsIgnoreCase("hello world", "HELLO WORLD"));
		System.out.print("\n");
		
		System.out.println("contains(String s, String str): "+StringUtil.contains("Hello World", "World"));
		System.out.print("\n");
		
		System.out.println("startsWith(String s1, String s2): "+StringUtil.startsWith("Hello World", "Hell"));
		System.out.print("\n");
		
		System.out.println("endsWith(String s1, String s2): "+StringUtil.endsWith("Hello World", "Hell"));
		System.out.print("\n");
		
		System.out.println("replace(String s, char oldChar, char newChar): "+StringUtil.replace("Hello World", 'W', 'w'));
		System.out.print("\n");
		
		System.out.println("replace(\"HeLLo\", \"LL\", \"ll\"): "+StringUtil.replace("HeLLo", "LL", "ll"));
		
		System.out.println("replace(\"HeLLo HeLLo HeLLo\", \"LL\", \"l\"): "+StringUtil.replace("HeLLo HeLLo HeLLo", "LL", "l"));
		
		System.out.println("replace(\"HeLLo HeLLo\", \"LL\", \"llo\"): "+StringUtil.replace("HeLLo HeLLo", "LL", "llo"));
		System.out.print("\n");
		
		System.out.println("trim(String s): "+StringUtil.trim("Hello World"));
		System.out.print("\n");
	}
}
