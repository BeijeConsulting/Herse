package it.beije.herse.oca.cap3.string;

public class StringBuilderUtil {
	
	public static String substring(StringBuilder sb, int beginIndex) {
		StringBuilder newSB = new StringBuilder();
		for(int i=beginIndex;i<sb.length();i++) newSB.append(sb.charAt(i));
		return newSB.toString();
	}
	
	public static String substring(StringBuilder sb, int beginIndex, int endIndex) {
		StringBuilder newSB = new StringBuilder();
		for(int i=beginIndex;i<endIndex;i++) newSB.append(sb.charAt(i));
		return newSB.toString();
	}
	
	public static StringBuilder delete(StringBuilder sb, int start, int end) {
		StringBuilder newSB = new StringBuilder();
		for(int i=0;i<sb.length();i++) if(i<start || i>end) newSB.append(sb.charAt(i));
		return newSB;
	}
	
	public static StringBuilder reverse(StringBuilder sb) {
		StringBuilder newSB = new StringBuilder();
		for(int i=sb.length()-1;i>=0;i--) newSB.append(sb.charAt(i));
		return newSB;
	}
	
	public static void main(String[] args) {
		System.out.println("String substring(new StringBuilder(\"Hello World\"), 4): "+
					StringBuilderUtil.substring(new StringBuilder("Hello World"), 4));
		System.out.print("\n");
		
		System.out.println("String substring(new StringBuilder(\"Hello World\"), 0, 4): "+
				StringBuilderUtil.substring(new StringBuilder("Hello World"), 0, 4));
		System.out.print("\n");
		
		System.out.println("StringBuilder delete(new StringBuilder(\"Hello World\"), 4, 10): "+
				StringBuilderUtil.delete(new StringBuilder("Hello World"), 4, 10));
		System.out.print("\n");
		
		System.out.println("StringBuilder (new StringBuilder(\"Hello World\")): "+
				StringBuilderUtil.reverse(new StringBuilder("Hello World")));
		System.out.print("\n");
	}
}
