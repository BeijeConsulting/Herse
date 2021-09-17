package it.beije.herse.oca.cap4.encapsulation;

public class NotImmutable {
	private StringBuilder builder;
	
	public NotImmutable(StringBuilder b) {
		builder = b;
	}
	
	public StringBuilder getBuilder() {
		return builder;
	}
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("initial");
		NotImmutable problem = new NotImmutable(sb);
		sb.append(" added");
		StringBuilder gotBuilder = problem.getBuilder();
		gotBuilder.append(" more");
		System.out.println(problem.getBuilder());
	}
}
