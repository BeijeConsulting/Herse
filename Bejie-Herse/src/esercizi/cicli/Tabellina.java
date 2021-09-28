package esercizi.cicli;


import java.util.Scanner;

//Scrivere un programma che stampi le tabellina del numero dato come argomento
public class Tabellina {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Scanner input=new Scanner(System.in);
    System.out.println("Inserisci un numero: ");
    int n=input.nextInt();
    
    for(int i=0;i<=10;i++) {
    	System.out.println(n+"*"+i+"="+(n*i));
    }
     

	}

}
