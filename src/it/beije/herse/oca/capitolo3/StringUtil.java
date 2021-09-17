package it.beije.herse.oca.capitolo3;

import java.util.ArrayList;

public class StringUtil {

	public static void main(String[] args) {
		String s = "enere";
		String s2 = new String("cenere");

		String s6 = " \te \n";
		String s7 = "amb";
		String s10 = "";
		System.out.println(trim(s6));
	}


	public static String substring(String s, int beginIndex) {
		String result = "";
		if (beginIndex < s.length()) {
			for (int i = beginIndex; i < s.length(); i++) {
				char c = s.charAt(i);
				result += c;
			}
		} else {
			result = "";
		}
		return result;
	}

	public static String substring(String s, int beginIndex, int endIndex) {
		String result = "";
		if (beginIndex < s.length() && beginIndex <= endIndex && endIndex <= s.length()) {
			for (int i = beginIndex; i < endIndex; i++) {
				char c = s.charAt(i);
				result += c;
			}
		} else {
			result = "";
		}
		return result;
	}

	public static String toLowerCase(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
				char c = s.charAt(i);
				char ch = (char) (c + 32);
				result += ch;
			} else {
				result += s.charAt(i);
			}
		}
		return result;
	}

	public static String toUpperCase(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
				char c = s.charAt(i);
				char ch = (char) (c - 32);
				result += ch;
			} else
				result += s.charAt(i);
		}
		return result;
	}

	public static boolean equals(String s1, String s2) {
		boolean isEqual = false;
		if (s1 != null && s2 != null) {

			if (s1.length() == 0 && s2.length() == 0) {
				return isEqual = true;
			}

			for (int i = 0; i < s1.length(); i++) {
				if (s1.length() == s2.length() && s1.charAt(i) == s2.charAt(i)) {
					isEqual = true;

				} else {
					isEqual = false;
				}
			}
		} else {
			isEqual = false;
		}
		return isEqual;
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		boolean isEqual = false;
		if (s1 != null && s2 != null) {
			if (s1.length() == 0 && s2.length() == 0) {
				return isEqual = true;
			}
			for (int i = 0; i < s1.length(); i++) {
				if (s1.length() == s2.length() && s1.toLowerCase().charAt(i) == s2.toLowerCase().charAt(i)) {
					isEqual = true;
				} else {
					isEqual = false;
				}
			}
		}

		return isEqual;
	}

	public static boolean contains(String str, String s) {
		boolean contains = false;
		int indice = 0;
		if (s != null && str != null && str.length() >= s.length()) {
			if (s.length() == 0) {
				contains = true;
				return contains;
			}
			for (int i = 0, j = 0; i < str.length() & j < s.length(); i++) {
				if (str.charAt(i) == s.charAt(j)) {
					j++;
					contains = true;
				}
			}

		}

		return contains;
	}

	public static boolean startsWith(String s, String prefix) {
		boolean startsWith = false;
		int indice = 0;
		if (s != null && prefix != null) {
			if (prefix.length() == 0) {
				startsWith = true;
				return startsWith;
			}
			if (prefix.length() == 0 && s.length() == 0) {
				startsWith = true;
				return startsWith;
			}
			for (int i = 0; i < prefix.length(); i++) {
				if (s.length() >= prefix.length()) {
					if (s.charAt(i) == prefix.charAt(i)) {
						startsWith = true;
					}
				} else
					startsWith = false;
			}
		}

		return startsWith;
	}

	public static boolean endsWith(String s, String suffix) {
		boolean endsWith = false;

		if (s != null && suffix != null) {
			if (suffix.length() == 0) {
				return endsWith = true;
			}
			if (suffix.length() == 0 && s.length() == 0) {
				return endsWith = true;
			}
//		
			String str = substring(s, s.length() - suffix.length());
			System.out.println(str);
			if (equals(str, suffix)) {
				endsWith = true;
			}
		}

		return endsWith;
	}

	public static String replace(String s, char oldChar, char newChar) {
		String sr = "";
		if (s != null) {
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) != oldChar) {
					sr += s.charAt(i);

				} else if (s.charAt(i) == oldChar) {
					sr += newChar;
				}
			}
		}
		return sr;
	}
//&& substring(s, i, oldChar.length()).equals(oldChar)//	&& j < oldChar.length() //s.charAt(i) == oldChar.charAt(0)
	// // s.charAt(i) == oldChar.charAt(0)
	// substring(s, i, s.charAt(oldChar.charAt(oldChar.length()-1)))

	public static String replace(String s, String oldChar, String newChar) {
		boolean contains = contains(s, oldChar);
		String st = "";
		// int indice = 0;
		// enere e am amnamram
		if (s != null && oldChar != null && newChar != null) {
			if (contains) {
				for (int i = 0; i < s.length(); i++) {

					if (substring(s, i, oldChar.length()).equals(oldChar)) {
						// st = substring(s, i, oldChar.length()) + newChar;
						// System.out.println("primo passo");
						st += replace(s, s.charAt(i), newChar.charAt(0)) + substring(newChar, 1, newChar.length());
						System.out.println(replace(s, s.charAt(i), newChar.charAt(i)));
						System.out.println(substring(newChar, 1, newChar.length()));
						// st += substring(s, i, oldChar.length()) + newChar;
						// for ( int x = 0; x < newChar.length(); x++) {

						// st += substring(newChar, 0, newChar.length());
						// System.out.println(replace(s, s.charAt(i), newChar.charAt(x)));
						// System.out.println(substring(newChar, x, newChar.length()));
						// }

					}
				}
			}
		}
		System.out.println(st);
		return st;

	}

	public static String trim(String s) {
		String st = "";
		int indice = 0;
		if (s != null) {
			char[] ch = s.toCharArray();
			for(int i = 0; i < ch.length; i++) {
				if ((ch[i] != ' ') && (ch[i] != '\t') && (ch[i] != '\n')) {
					st += ch[i];
				} 
			}
			
		}
		return st;
	}

	public static String substring(StringBuilder sb, int beginIndex) {
		StringBuilder result = new StringBuilder();
		if (sb != null && beginIndex < sb.length()) {
			for (int i = beginIndex; i < sb.length(); i++) {
				char c = sb.charAt(i);
				result.append(c);
			}
		}
		String s = result.toString();
		return s;
	}

	public static String substring(StringBuilder sb, int beginIndex, int endIndex) {
		StringBuilder result = new StringBuilder();
		if (sb != null && beginIndex < sb.length() && beginIndex <= endIndex && endIndex <= sb.length()) {
			for (int i = beginIndex; i < endIndex; i++) {
				char c = sb.charAt(i);
				result.append(c);
			}
		}
		String s = result.toString();
		return s;
	}

	public static StringBuilder delete(StringBuilder sb, int start, int end) {
		StringBuilder sb1 = new StringBuilder();
		if (sb != null && start < sb.length() && start <= end && end <= sb.length()) {
			String s1 = sb.toString().substring(0, start);
			String s2 = sb.toString().substring(end, sb.length());
			String s3 = s1 + s2;
			sb1 = new StringBuilder(s3);
			System.out.println(sb1);

		}
		return sb1;
	}

	public static StringBuilder reverse(StringBuilder sb) {
		StringBuilder result = new StringBuilder();
		if (sb != null) {
			for (int i = sb.length() - 1; i >= 0; i--) {
				result.append(sb.charAt(i));
			}
		}
		return result;
	}
}
