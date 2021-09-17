package it.beije.herse.stringUtil;

public class StringUtil {
	/**/
	public static String substring(String s, int beginIndex) {
		String newS = "";

		for (int i = beginIndex; i < s.length(); i++) {
			newS += s.charAt(i);
		}

		return newS;
	}

	/**/
	public static String substring(String s, int beginIndex, int endIndex) {
		String newS = "";

		for (int i = beginIndex; i < endIndex; i++) {
			newS += s.charAt(i);
		}

		return newS;
	}

	/**/
	public static String toLowerCase(String s) {
		StringBuilder newS = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
				newS.append((char) (s.charAt(i) + 32));
			} else {
				newS.append(s.charAt(i));
			}
		}

		return newS.toString();
	}

	/**/
	public static String toUpperCase(String s) {
		StringBuilder newS = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				newS.append((char) (s.charAt(i) - 32));
			} else {
				newS.append(s.charAt(i));
			}
		}

		return newS.toString();
	}

	/**/
	public static boolean equals(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		} else {
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) != s2.charAt(i)) {
					return false;
				}
			}
		}
		return true;
	}

	/**/
	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		} else {
			s1 = toLowerCase(s1);
			s2 = toLowerCase(s2);
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) != s2.charAt(i)) {
					return false;
				}
			}
		}
		return true;
	}

	/**/
	public static boolean contains(String s, String str) {

		for (int i = 0; i < s.length(); i++) {
			int editableI = i;
			int countStr = 0;
			for (int j = 0; j < str.length(); j++) {
				if (s.charAt(editableI) != str.charAt(j)) {
					j = str.length() + 1;
				} else {
					editableI++;
					countStr++;
				}
			}
			if (countStr == str.length()) {
				return true;
			}
		}
		return false;
	}

	/**/
	public static boolean startsWith(String s, String prefix) {
		for (int i = 0; i < prefix.length(); i++) {
			if (s.charAt(i) != prefix.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/**/
	public static boolean endsWith(String s, String suffix) {
		int strIndex = s.length() - 1;
		for (int j = suffix.length() - 1; j >= 0; j--) {
			if (s.charAt(strIndex) != suffix.charAt(j)) {
				return false;
			} else {
				strIndex--;
			}
		}
		return true;
	}
	
	/**/
	public static String replace(String s, char oldChar, char newChar) {
		String newS = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == oldChar) {
				newS += newChar;
			}else {
				newS += s.charAt(i);
			}
		}
		return newS;
	}
	
	/**/
	public static String replace(String s, String oldChar, String newChar) {
		String newS = "";
		
		for (int i = 0; i < s.length(); i++) {
			int editableI = i;
			int countStr = 0;
			for (int j = 0; j < oldChar.length(); j++) {
				if (s.charAt(editableI) != oldChar.charAt(j)) {
					j = oldChar.length() + 1;
				} else {
					editableI++;
					countStr++;
				}
			}
			if (countStr == oldChar.length()) {
				newS += newChar;
				i += oldChar.length()-1;
			}else {
				newS += s.charAt(i);
			}
		}
		
		return newS;
	}
	
	/**/
	public static String trim(String s) {
		String newS = "";
		
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != ' ') {
				newS += s.charAt(i);
			}
		}
		
		return newS;
	}
}
