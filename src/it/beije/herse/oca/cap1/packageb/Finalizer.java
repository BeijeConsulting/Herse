package it.beije.herse.oca.cap1.packageb;

public class Finalizer {

	protected void finalize() {
		System.out.println("Calling finalize");
	}

	public static void main(String[] args) {
		for(int i=0;i<103000;i++)
			new Finalizer();
	}
}