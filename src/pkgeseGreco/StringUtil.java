package pkgeseGreco;

public class StringUtil {
	
	public static String substring(String s, int beginIndex) {
		String r = "";
		for(int i = beginIndex; i < s.length(); i++) {
			r = r + s.charAt(i);
		}
		return r;
	}
	
	public static String substring(String s, int beginIndex, int endIndex) {
		String r = "";
		for(int i = beginIndex; i <= endIndex; i++) {
			r = r + s.charAt(i);
		}
		return r;
	}
	
	public static String toLowerCase(String s) {
		String r = "";
		for(int i = 0; i < s.length(); i++) {
			r = r + (char)(s.charAt(i) + 32);
		}
		return r;
	}
	
	public static String toUpperCase(String s) {
		String r = "";
		for(int i = 0; i < s.length(); i++) {
			r = r + (char)(s.charAt(i) - 32);
		}
		return r;
	}
	
	public static boolean equals(String s1, String s2) {
		boolean b = false;
		if(s1.length() != s2.length()) {
			b = false;
		}else {
			for(int i = 0; i < s1.length(); i++) {
				if(s1.charAt(i) == s2.charAt(i)) {
					b = true;
				}else {
					return false;
				}
			}
			
		}
		return b;
	}
	
	public static boolean equalsIgnoreCase(String s1, String s2) {
		boolean b = false;
		if(s1.length() != s2.length()) {
			b = false;
		}else {
			for(int i = 0; i < s1.length(); i++) {
				if((s1.charAt(i) == s2.charAt(i))||((s1.charAt(i) + 32) == s2.charAt(i))||((s1.charAt(i) - 32) == s2.charAt(i))) {
					b = true;
				}else {
					return false;
				}
			}
			
		}
		return b;
	}
	
	public static boolean contains(String s, String str) {
		//String s grande, str piccola
		boolean b = false;
		int c = 0;
		if(s.length() >= str.length()) {
			for(int i = 0; i < s.length(); i++){
				if((s.charAt(i) == str.charAt(c)) && (i < s.length()) ) {
					c++;
					b = true;
				}else if(c < str.length()){
					b = false;
				}
				if(c == str.length()) {
					b = true;
					return b;
				}
			}
		}else {
			for(int i = 0; i < str.length(); i++){
				if((str.charAt(i) == s.charAt(c)) && (i < str.length()) ) {
					c++;
					b = true;
				}else if(c < s.length()){
					b = false;
				}
				if(c == s.length()) {
					b = true;
					return b;
				}
			}
		}
		return b;
	}
	
	public static boolean startsWith(String s, String prefix) {
		boolean b = false;
		for(int i = 0; i < prefix.length(); i++){
			if(prefix.charAt(i) == s.charAt(i)) {
				b = true;
			}else {
				return false;
			}
		}
		return b;
	}
	
	public static boolean endsWith(String s, String suffix) {
		boolean b = false;
		int c = s.length() - 1;
		for(int i = suffix.length() - 1; i >= 0; i--) {
			if(suffix.charAt(i) == s.charAt(c)) {
				b = true;
				c--;
			}else {
				return false;
			}
		}
		return b;
	}
	
	public static String replace(String s, char oldChar, char newChar) {
		String str = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == oldChar) {
				str = str + newChar;
			}else {
				str = str + s.charAt(i);
			}
		}
		return str;
	}
	
	public static String replace(String s, String oldChar, String newChar) {
		String str = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == oldChar.charAt(0)) {
				str = str + newChar;
			}else {
				str = str + s.charAt(i);
			}
		}
		return str;
	}
	
	public static String trim(String s) {
		String str = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != ' ') {
				str = str + s.charAt(i);
			}else if(i > 0){
				if(s.charAt(i - 1) != ' ') {
					str = str + s.charAt(i);
				}
			}
		}
		return str;
	}
	
}
