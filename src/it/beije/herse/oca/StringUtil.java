package it.beije.herse.oca;

public class StringUtil {

	//*
	public static String substring(String s, int beginIndex) {

		String str = "";

		if(beginIndex >= 0 && beginIndex < s.length())
			for(int i = beginIndex; i < s.length(); i++)
				str += s.charAt(i);
		else
			str = "-1";

		return str;

	}

	//*
	public static String substring(String s, int beginIndex, int endIndex) {

		String str = "";

		if(beginIndex >= 0 && beginIndex <= endIndex && endIndex <= s.length() && beginIndex < s.length())		
			for(int i = beginIndex; i < endIndex; i++)
				str += s.charAt(i);
		else
			str = "-1";

		return str;

	}

	//*
	public static String toLowerCase(String s) {

		final int shift = 32;
		String str = "";

		for(int i = 0; i < s.length(); i++) {

			if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
				str += (char)(s.charAt(i) + shift);
			else
				str += s.charAt(i);

		}

		return str;

	}

	//*
	public static String toUpperCase(String s) {

		final int shift = 32;
		String str = "";

		for(int i = 0; i < s.length(); i++) {

			if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
				str += (char)(s.charAt(i) - shift);
			else
				str += s.charAt(i);

		}

		return str;

	}

	//*
	public static boolean equals(String s1, String s2) {

		if(s1.length() == s2.length()) {

			for(int i = 0; i < s1.length(); i++)
				if(s1.charAt(i) != s2.charAt(i))
					return false;

			return true;

		}

		return false;

	}

	//*
	public static boolean equalsIgnoreCase(String s1, String s2) {

		String str1 = toLowerCase(s1);
		String str2 = toLowerCase(s2);

		if(str1.length() == str2.length()) {

			for(int i = 0; i < str1.length(); i++)
				if(str1.charAt(i) != str2.charAt(i))
					return false;

			return true;

		}

		return false;

	}

	//*
	public static boolean contains(String s, String str) {


		boolean flag = false;

		if(s.length() >= str.length()) {

			int j = 0;

			for(int i = 0; i < s.length() && j < str.length(); i++) {

				if(s.charAt(i) == str.charAt(j))
					j++;
				else
					j = 0;

			}

			if(j == str.length())
				flag = true;

		}

		return flag;

	}

	//*
	public static boolean startsWith(String s, String prefix) {

		boolean flag = true;

		if(s.length() >= prefix.length())			
			for(int i = 0; i < prefix.length() && flag; i++)
				if(s.charAt(i) != prefix.charAt(i))
					flag = false;

		return flag;

	}

	//*
	public static boolean endsWith(String s, String suffix) {

		boolean flag = true;

		if(s.length() >= suffix.length())			
			for(int i = s.length()-1, j = suffix.length()-1; j > 0 && flag; i--, j--)
				if(s.charAt(i) != suffix.charAt(j))
					flag = false;

		return flag;

	}

	//*
	public static String replace(String s, char oldChar, char newChar) {

		String newString = "";

		for(int i = 0; i < s.length(); i++) {

			if(s.charAt(i) == oldChar)
				newString += newChar;
			else
				newString += s.charAt(i);

		}
		
		if(equals(newString, ""))
			newString = "-1";

		return newString;

	}

	//*
	public static String replace(String s, String oldChar, String newChar) {

		String newString = "";

		if(s.length() >= oldChar.length()) {

			int j = 0;
			int startIndex = 0;
			boolean flag = false;

			for(int i = 0; i < s.length() && j < oldChar.length(); i++) {

				if(s.charAt(i) == oldChar.charAt(j)) {
					j++;
					if(!flag)
						startIndex = i;
					flag = true;
				}else {
					j = 0;
					flag = false;
				}

			}

			if(j == oldChar.length()) {

				for(int i = 0; i < startIndex; i++)
					newString += s.charAt(i);
				for(int i = 0; i < newChar.length(); i++)
					newString += newChar.charAt(i);
				for(int i = startIndex + oldChar.length(); i < s.length(); i++)
					newString += s.charAt(i);

			}

		}
		
		if(equals(newString, ""))
			newString = "-1";

		return newString;

	}

	//*
	public static String trim(String s) {

		String newString = "";
		boolean flagStart = true;
		boolean flagEnd = true;
		int startIndex = 0;
		int endIndex = s.length();

		for(int i = 0; i < s.length() && flagStart; i++) {
			if(s.charAt(i) != ' ') {
				flagStart = false;
				startIndex = i;
			}
		}

		for(int i = s.length()-1; i < 0 && flagEnd; i--) {
			if(s.charAt(i) != ' ') {
				flagEnd = false;
				endIndex = i+1;
			}
		}

		if(!flagStart) {
			newString = substring(s, startIndex, endIndex);
		}

		return newString;
	}

	public static void main(String[] args) {

		String s = "Ciao sono Savino";

		System.out.println(substring(s, 10));
		System.out.println(s.substring(10));
		System.out.println();
		System.out.println(substring(s, 10,13));
		System.out.println(s.substring(10,13));
		System.out.println();
		System.out.println(toLowerCase(s));
		System.out.println(s.toLowerCase());
		System.out.println();
		System.out.println(toUpperCase(s));
		System.out.println(s.toUpperCase());
		System.out.println();
		System.out.println(equals(s,"Ciao sono Savino"));
		System.out.println(equals(s,"ciao sono savino"));
		System.out.println(s.equals("Ciao sono Savino"));
		System.out.println(s.equals("ciao sono savino"));
		System.out.println();
		System.out.println(equalsIgnoreCase(s,"ciao sono savino"));
		System.out.println(s.equalsIgnoreCase("ciao sono savino"));
		System.out.println();
		System.out.println(contains(s, "Savino"));
		System.out.println(s.contains("Savino"));
		System.out.println();
		System.out.println(startsWith(s, "ciao"));
		System.out.println(s.startsWith("ciao"));
		System.out.println();
		System.out.println(endsWith(s, "Savino"));
		System.out.println(s.endsWith("Savino"));
		System.out.println();
		System.out.println(replace(s,'a',' '));
		System.out.println(s.replace('a', ' '));
		System.out.println();
		System.out.println(replace(s," sono",""));
		System.out.println(s.replace(" sono", ""));
		System.out.println();
		System.out.println(trim("    " + s + "     "));
		System.out.println(("    " + s + "     ").trim());

	}

}
