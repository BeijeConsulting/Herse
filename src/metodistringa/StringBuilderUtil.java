package metodistringa;

public class StringBuilderUtil {
	
	static String substring(StringBuilder sb, int beginIndex) {
		String s = new String();
		StringBuilder tmp = new StringBuilder();
		for(int i = beginIndex; i<sb.length(); i++) {
			tmp.append(sb.charAt(i));
		}
		s = tmp.toString();
		return s;
	}
	static String substring(StringBuilder sb, int beginIndex, int endIndex) {
		String s = new String();
		StringBuilder tmp = new StringBuilder();
		for(int i = beginIndex; i<endIndex; i++) {
			tmp.append(sb.charAt(i));
		}
		s = tmp.toString();
		return s;
	}
	static StringBuilder delete(StringBuilder sb, int start, int end) {
		StringBuilder tmp = new StringBuilder();
		for(int i = 0; i<start; i++) {
			tmp.append(sb.charAt(i));
		}
		for(int i = end; i<sb.length(); i++) {
			tmp.append(sb.charAt(i));
		}
		return tmp;
	}
	
	static StringBuilder reverse(StringBuilder sb) {
		StringBuilder tmp = new StringBuilder();
		for(int i = sb.length()-1; i>=0; i--) {
			tmp.append(sb.charAt(i));
		}
		return tmp;
	}
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("Mamma mia");
		String s = new String();
//		s = substring(sb, 6);
		s = substring(sb, 0, 5);
		
//		System.out.println(delete(sb , 0, 5));
		System.out.println(reverse(sb));

	}

}
