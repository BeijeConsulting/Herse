package it.beije.herse.OCA;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class CalcolatriceParentesi {
	
	static int  StringtoInt(String s) {
		int l = s.length()-1;
		double x = 0;
		for(int i =0; i<s.length(); i++) {
			x = x + (Math.pow(10, l)*((int)s.charAt(i) - '0'));
			l--;
		}
	return (int)x;
	}
	
	static int  StringtoInt(ArrayList s) {
		double l = s.size()-1;
		double x = 0;
		for(int i =0; i<s.size(); i++) {
			x = x + (Math.pow(10, l)*((int)s.get(i) - '0'));
			l--;
		}
	return (int)x;
	}
	
	static int risultato(int a, int b, String op) {
		int ris = 0;
		switch(op){
			case"+":
				ris = a + b;
				break;
				
			
			case "-":
				ris = a - b;
				break;
				
			case "*":
				ris = a*b;
				break;
			case "/" :
				ris = a/b;
				break;
				}
		return ris;
	}

	public static void main(String[] args) {
		System.out.println("Inserire espressione ");
		Scanner scann = new Scanner (System.in);
		String s = scann.next();
		String buff = new String("");
		scann.close();
		
		ArrayList <String>  a = new ArrayList<>();
		int j = 0;
		int x1, x2, ris=0, temp=0;
	 
	/*	for(int i =0; i<s.length(); i++) {
			System.out.println(s + "\t indice " + i);
			if(s.charAt(i) == '(') {
				a.add(String.valueOf(s.charAt(i)));
				s = s.substring(i+1);
				i = 0;
			}
			
			 if(s.charAt(i) == '+' || s.charAt(i) == '-'
						|| s.charAt(i) == '*' || s.charAt(i) == '/' ) {
				a.add(s.substring(0, i));
				a.add(String.valueOf(s.charAt(i)));
				s = s.substring(i+1);
				i = 0;
			 }
			 if(s.charAt(i) == ')') {
				a.add(s.substring(0, i));
				a.add(String.valueOf(s.charAt(i)));
				s = s.substring(i+1);
				i = 0;
			}
		}

		a.add(s);	*/
		
		
		for(int i = 0; i<s.length(); i++) {
			if(s.charAt(i)== '(' || s.charAt(i) == ')') {
				a.add(String.valueOf(s.charAt(i)));
				a.add(buff);
			}
			
			else if(s.charAt(i) == '+' || s.charAt(i) == '-'
					|| s.charAt(i) == '*' || s.charAt(i) == '/' ) {
				a.add(String.valueOf(s.charAt(i)));
				a.add(buff);
			}
			else buff += s.charAt(i); 
		}
		
		System.out.println(a);
		
	/*	for(int i =0; i<a.size(); i++) {
			System.out.println(a);
			if(a.get(i).equals("*") || a.get(i).equals("/")) {
				x1 = StringtoInt(a.get(i-1));
				x2 = StringtoInt(a.get(i+1));
				temp = risultato(x1, x2, a.get(i));
				a.remove(i+1);
				a.remove(i);
				a.set(i-1, String.valueOf(temp));
				i = 0;	
				}
			}
		for(int i =0; i<a.size(); i++) {
			 if(a.get(i).equals("+") || a.get(i).equals("-")) {
				x1 = StringtoInt(a.get(i-1));
				x2 = StringtoInt(a.get(i+1));
				temp = risultato(x1, x2, a.get(i));
				a.remove(i+1);
				a.remove(i);
				a.set(i-1, String.valueOf(temp));
				i = 0;
				
			}			
		}
		
		
		System.out.println(temp);*/
		
	}
}