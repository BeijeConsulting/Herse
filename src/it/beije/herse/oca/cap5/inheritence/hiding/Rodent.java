package it.beije.herse.oca.cap5.inheritence.hiding;

public class Rodent {
	protected int tailLength = 4;
	
	public void getRodentDetails() {
		System.out.println("[parentTail="+tailLength+"]");
	}
}
