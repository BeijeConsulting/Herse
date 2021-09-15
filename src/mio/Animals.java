package mio;
import mio2.Leone;
import mio3.Gorilla;
public class Animals{
	public static void main(String args[]){
	
			Leone l = new Leone("Simba", 120, 13);
			System.out.println(l.nome + " " +  l.peso +  " " + "kg " + l.age);
			
			Gorilla g = new Gorilla("Tarzan", 200, "Banana");
			System.out.println(g.nome + " " +  g.peso +  " " + "kg " + g.ciboPreferito);
	}
}