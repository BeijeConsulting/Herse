package it.beije.herse.oca;

public class StringBuilderUtil {

	//*
	public static String substring(StringBuilder sb, int beginIndex) {

		String newString = "";

		if(beginIndex >= 0 && beginIndex < sb.length())
			for(int i = beginIndex; i < sb.length(); i++)
				newString += sb.charAt(i);
		else
			newString = "-1";

		return newString;

	}

	//*
	public static String substring(StringBuilder sb, int beginIndex, int endIndex) {

		String newString = "";

		if(beginIndex >= 0 && beginIndex <= endIndex && endIndex <= sb.length() && beginIndex < sb.length())		
			for(int i = beginIndex; i < endIndex; i++)
				newString += sb.charAt(i);
		else
			newString = "-1";

		return newString;

	}

	//*
	public static StringBuilder delete(StringBuilder sb, int start, int end) {

		StringBuilder newString = new StringBuilder();

		if(start < end && end <= sb.length() && start >= 0) {
			for(int i = 0; i < sb.length(); i++)
				if(i < start || i >= end)
					newString.append(sb.charAt(i));
		}else
			newString.append(sb);

		return newString;

	}

	//*
	public static StringBuilder reverse(StringBuilder sb) {

		StringBuilder newString = new StringBuilder();

		for(int i = sb.length()-1; i >= 0; i--)
			newString.append(sb.charAt(i));

		return newString;

	}

	public static void main(String[] args) {

		StringBuilder s = new StringBuilder("Ciao sono Savino");

		System.out.println(substring(s, 10));
		System.out.println(s.substring(10));
		System.out.println();
		System.out.println(substring(s, 10, 13));
		System.out.println(s.substring(10, 13));
		System.out.println();
		System.out.println(delete(s,5,10));
		System.out.println(s.delete(5,10));
		System.out.println();
		System.out.println(reverse(s));
		System.out.println(s.reverse());
		
	}

}
