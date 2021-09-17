package it.beije.herse.stringUtil;

public class StringUtilTest {
	public static void main(String args[]) {
		System.out.println(StringUtil.substring("Nicola", 2));
		System.out.println(StringUtil.substring("Nicola", 4, 5));
		System.out.println(StringUtil.toLowerCase("Ciao"));
		System.out.println(StringUtil.toUpperCase("Ciao"));
		System.out.println(StringUtil.equals("Ciao", "ciao"));
		System.out.println(StringUtil.equalsIgnoreCase("Ciao", "ciao"));
		System.out.println(StringUtil.contains("ciao", "no"));
		System.out.println(StringUtil.startsWith("ciao", "ci"));
		System.out.println(StringUtil.endsWith("ciao", "xiao"));
		System.out.println(StringUtil.replace("australopiteco", 'a', 'o'));
		System.out.println(StringUtil.replace("cicicocococca", "ci", "co"));
		System.out.println(StringUtil.trim("Nic ol  a"));
		
		/**/
		StringBuilder sb = new StringBuilder("Nicola");
		System.out.println(StringBuilderUtil.substring(sb, 2));
		System.out.println(StringBuilderUtil.substring(sb, 2, 3));
		System.out.println(StringBuilderUtil.delete(sb, 0, 3));
		System.out.println(StringBuilderUtil.reverse(sb));

		


		









	}
}
