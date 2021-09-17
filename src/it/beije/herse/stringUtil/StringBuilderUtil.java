package it.beije.herse.stringUtil;

public class StringBuilderUtil {

	/**/
	public static String substring(StringBuilder sb, int beginIndex) {
		String newS = "";

		for (int i = beginIndex; i < sb.length(); i++) {
			newS += sb.charAt(i);
		}

		return newS;
	}

	/**/
	public static String substring(StringBuilder sb, int beginIndex, int endIndex) {
		String newS = "";

		for (int i = beginIndex; i < endIndex; i++) {
			newS += sb.charAt(i);
		}

		return newS;
	}

	/**/
	public static StringBuilder delete(StringBuilder sb, int start, int end) {
		StringBuilder newSb = new StringBuilder();

		for (int i = 0; i < sb.length(); i++) {
			if (i < start || i >= end) {
				newSb.append(sb.charAt(i));
			}
		}

		return newSb;
	}

	/**/
	public static StringBuilder reverse(StringBuilder sb) {
		StringBuilder newSb = new StringBuilder();

		for (int i = sb.length() - 1; i >= 0; i--) {
			newSb.append(sb.charAt(i));
		}

		return newSb;
	}
}
