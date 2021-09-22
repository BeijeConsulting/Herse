package it.beije.herse.oca;

public class StringBuilderUtil {
	/*
	 * 
String substring(StringBuilder sb, int beginIndex)
String substring(StringBuilder sb, int beginIndex, int endIndex)
StringBuilder delete(StringBuilder sb, int start, int end)
StringBuilder reverse(StringBuilder sb)
	 */

	public String substring(StringBuilder x, int beginIndex) {

		StringBuilder newSb = new StringBuilder();

		if (beginIndex>x.length()-1 || beginIndex<0) return "ERRORE 1";

		for (int i = beginIndex; i<x.length();i++) {
			newSb.append(x.charAt(i));
		}

		return newSb.toString();

	}


	public String substring(StringBuilder x, int beginIndex, int endIndex) {

		StringBuilder newSb = new StringBuilder();

		if (beginIndex>x.length()-1 || beginIndex<0) return "ERRORE 1";
		if (endIndex>x.length() || endIndex<0) return "ERRORE 2";

		for (int i = beginIndex; i<endIndex;i++) {
			newSb.append(x.charAt(i));
		}

		return newSb.toString();

	}


	public StringBuilder delete(StringBuilder x, int start, int end) {
		
		StringBuilder newSb = new StringBuilder();
		
		if (start<0) start=0;
		if (start>=x.length() || end<=start || end>x.length()) return new StringBuilder("ERRORE");
		
		for(int i=0;i< x.length();i++) {
			if(i>=start && i<end) continue;
			newSb.append(x.charAt(i));
		}
		
		return newSb;

	}

	public StringBuilder reverse(StringBuilder x) {
		
		StringBuilder newSb = new StringBuilder();
		
		for(int i=x.length()-1; i>=0;i--) newSb.append(x.charAt(i));

		return newSb;
	}
	 
	public static void main(String args[]) {
		StringBuilderUtil s = new StringBuilderUtil();

		System.out.println(s.substring(new StringBuilder("Hello"), 1));
		System.out.println(s.substring(new StringBuilder("Hello"), 10));
		System.out.println(s.substring(new StringBuilder("Hello"), -1));
		
		
		System.out.println(s.substring(new StringBuilder("Hello"), 3,5));
		System.out.println(s.substring(new StringBuilder("Hello"), -1, 13));
		System.out.println(s.substring(new StringBuilder("Hello"), 1,7));
		
		System.out.println(s.delete(new StringBuilder("H&M"), 1, 2).toString());
		System.out.println(s.delete(new StringBuilder("H&M"), 12, 2).toString());
		System.out.println(s.delete(new StringBuilder("H&M"), -3, 12).toString());
		
		System.out.println(s.reverse(new StringBuilder("Hello")).toString());
		
	}

}
