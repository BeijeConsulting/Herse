package it.beije.herse.oca.cap2.operators;

public class SwitchEx{
	public static void main(String[] args){
		int dayOfWeek = 0;
		switch(dayOfWeek) {
			default:
				System.out.println("Weekday");
			case 0:
				System.out.println("Sunday");
			case 6:
				System.out.println("Saturday");
				break;
		}
	}
}