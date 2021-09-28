package esercizi;
import java.util.*;

public class esempiLista{
	
	public static void main(String[] args){
		ArrayList <String> list1 = new ArrayList<String>(10);
		list1.add("hawk");
		ArrayList <String> list2 = new ArrayList<String>(list1);
		System.out.println(list1);
		System.out.println(list2);
		System.out.println();
		
		list2.remove(0);
		System.out.println(list1);
		System.out.println(list2);
		System.out.println();
	}
}