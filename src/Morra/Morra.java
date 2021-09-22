	/*Scrivere un programma che chieda agli utenti due stringhe in ingresso, 
	 * le stringhe possono valere solo: “carta”, “forbice” o “sasso”. 
	 * Il programma dovrà quindi effettuare i dovuti controlli e 
	 * dichiarare il vincitore secondo le note regole della “morra cinese” 
	 * (forbice vince su carta, carta vince su sasso, sasso vince su forbice).
	 */
package Morra;
public class Morra{
	public void Start(String g1, String g2) {
		if(g1.equalsIgnoreCase(g2))
			System.out.println("Pareggio");
		switch(g1) {
		case"carta":
			if(g2.equalsIgnoreCase("forbice"))
				System.out.println("Vince giocatore 2");
			else if(g2.equalsIgnoreCase("sasso"))
				System.out.println("Vince giocatore 1");
			break;
		case"forbice":
			if(g2.equalsIgnoreCase("carta"))
				System.out.println("Vince giocatore 1");
			else if(g2.equalsIgnoreCase("sasso"))
				System.out.println("Vince giocatore 2");
			break;
		case"sasso":
			if(g2.equalsIgnoreCase("carta"))
				System.out.println("Vince giocatore 2");
			else if(g2.equalsIgnoreCase("forbice"))
				System.out.println("Vince giocatore 1");
			break;
		default:
			System.out.println("Input non corretti!");
			break;
		}
	}
	
}
