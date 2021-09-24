package Library;

public class StringUtil {

	public static String substring(String s, int beginIndex) { // Funziona
		String sReturn = "";
		if(beginIndex >= s.length())
			System.out.println("String index out of range!");
		for(int i = beginIndex; i < s.length(); i++ ) {
			sReturn += s.charAt(i);
		}
		return sReturn;
	}
	
	public static String substring(String s, int beginIndex, int endIndex) { // Funziona
		String sReturn = "";
		if(beginIndex >= s.length() || beginIndex < 0 || endIndex > s.length() || endIndex < 0)
			System.out.println("String index out of range!");
		for(int i = beginIndex; i < endIndex; i++)
			sReturn += s.charAt(i);
		return sReturn;
	}
	
	public static String toLowerCase(String s) { // Funziona
		String str = s;
		String sReturn = "";
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int dec = ch;
			if(dec >= 65 && dec <= 90) {
				dec += 32;
				ch = (char) dec;
				sReturn += ch;
			} else {
				sReturn += ch;
			}
		}
		return sReturn;
	}
	
	public static String toUpperCase(String s) { // Funziona
		String str = s;
		String sReturn = "";
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int dec = ch;
			if (dec >= 97 && dec <= 122) {
				dec -= 32;
				ch = (char) dec;
				sReturn += ch;
			} else {
				sReturn += ch;
			}
		}
		return sReturn;
	}
	
	public static boolean equals(String s1, String s2) { // Funziona
		if(s1.length() != s2.length())
			return false;
		int cont = 0;
		for (int i = 0; i < s1.length(); i ++) {
			if(s1.charAt(i) == s2.charAt(i))
				cont++;
		}
		if(cont == s1.length() && cont == s2.length())
			return true;
		else
			return false;
	}
	
	public static boolean equalsIgnoreCase(String s1, String s2) { // Funziona
		if(s1.length() != s2.length())
			return false;
		int cont = 0;
		for (int i = 0; i < s1.length(); i ++) {
			char ch1 = s1.charAt(i);
			char ch2 = s2.charAt(i);
			int dec1 = ch1;
			int dec2 = ch2;
			if(s1.charAt(i) == s2.charAt(i))
				cont++;
			if(dec1 >= 65 && dec1 <= 90) {
				if(dec1 + 32 == dec2) {
					cont++;
				}
			}
		}
		if(cont == s1.length() && cont == s2.length())
			return true;
		else
			return false;
	}
	
	public static boolean contains(String s, String str) { // Funziona
		if (str.length() > s.length()) {
			return false;
		}
		int cont = 0;
		int temp = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == str.charAt(0)) {
				temp = i;
				for(int j = 0; j < str.length(); j++) {
					if(s.charAt(temp) == str.charAt(j)) {
						cont++;
						temp++;
					}
				}
			}
			
		if(cont == str.length())
			return true;
		}	
		return false;
	}
	
	public static boolean startsWith(String s, String prefix) { // Funziona
		if(prefix.length() > s.length()) 
			return false;
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			if(i >= prefix.length()) 
				return false;
			if (s.charAt(i) == prefix.charAt(i))
				count++;
			if (count == prefix.length())
				return true;
		}
		return false;
	}
	
	public static boolean endsWith(String s, String suffix) { // Funziona
		if(suffix.length() > s.length())
			return false;
		int count = 0;
		String temp = "";
		int start = (s.length() - suffix.length());
		for (int i = start; i < s.length(); i ++) {
			temp += s.charAt(i);
		}
		for(int i = 0; i < temp.length(); i ++) {
			if(temp.charAt(i)==suffix.charAt(i))
				count++;
			if (count == suffix.length())
				return true;
		}
		return false;
	}
	
	public static String replace(String s, char oldChar, char newChar) { // Funziona
		String returnS ="";
		for(int i = 0; i < s.length(); i ++) {
			
			if(s.charAt(i) == oldChar)
				returnS += newChar;
				else 
					returnS += s.charAt(i);
		}
		return returnS;
	}
	
	public static String replace(String s, String oldChar, String newChar) { // Funziona
		String returnS = "";
		int cont = 0;
		int temp = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == oldChar.charAt(0))
			{
				for(int j = 0; j <oldChar.length(); j++) {
					temp = i;
					if(s.charAt(temp) == oldChar.charAt(j)) {
						cont++;
						temp++;
					}
				}
			}
			if(cont == oldChar.length()) {
				for(int x = 0; i < newChar.length(); i++) {
					returnS += newChar.charAt(x);
				}
			} else {
				returnS += s.charAt(i);
			}
		}
		return returnS;	
	}
	
	public static String trim(String s) { // Funziona
		String returnS = "";
		int dec = 32;
		int temp = 0;
		for(int i = 0; dec == 32; i ++) {
			temp = i;
			char ch = s.charAt(i);
			dec = ch;
		}
		
		for(int j = temp; j < s.length(); j++) {
			returnS += s.charAt(j);
		}
		return returnS;
	}
}
