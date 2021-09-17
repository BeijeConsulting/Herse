package it.beije.herse.oca.cap3.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * fare una calcolatrice da linea di comando o da scanner, 
 * ma l'espressione deve essere riconosciuta elemento per elemento, 
 * sia che venga passata come stringa intera, 
 * sia che vengano passati numeri ed operatori uno alla volta via scanner
 * (NO parse, NO valueOf)
 * v1: +, -, *, /.
 * v2: (, ).
 */
public class Calculator {
	private List<String> numbers = new ArrayList<String>();
	private List<String> operators = new ArrayList<String>();
	private List<String> expression = new ArrayList<String>();
	
	private List<Integer> sortedExpNum = new ArrayList<Integer>();
	private List<String> sortedExpOp = new ArrayList<String>();
	
	public Calculator(){
		for(int i=0;i<Short.MAX_VALUE;i++) numbers.add(""+i);
		
		operators.add("+");
		operators.add("-");
		operators.add("*");
		operators.add("/");
		operators.add("=");
	}
	
	public void saveExpression(String in) {
		boolean flag = false;
		for(String o : operators) {
			if(in.contains(o)) flag = true;
		}
		
		if(flag) {
			int beginIndex = 0;
			int endIndex = 0;
			String cur;
			
			for(int i=0;i<in.length();i++) {
				cur = in.substring(i, i+1);
				for(String o : operators) {
					if(o.equals(cur)) {
						if(i!=0) {
							endIndex = i;
							expression.add(in.substring(beginIndex, endIndex));
							expression.add(cur);
							beginIndex = endIndex+1;
							break;
						}
						else{
							expression.add(cur);
							beginIndex = 1;
							if(!operators.contains(in.substring(in.length()-1))) expression.add(in.substring(beginIndex));
						}
					}
				}
			}
		}
		else expression.add(in);
		
		for(int i=0;i<expression.size();i++) if(expression.get(i).equals("")) expression.remove(i);
	}
	
	public void startCalc() {
		if(isValid()) {
			sortByPriority();
			calc();
		}
		else System.out.println("Not valid expression");
	}
		
	private boolean isValid() {
		for(int i=0;i<expression.size();i++) {
			// Se nelle posizioni pari non c'è un numero => NOT VALID
			if(i%2 == 0) {
				if( !numbers.contains(expression.get(i)) ) return false;
			}
			// Se nelle posizioni dispari non c'è un operatore => NOT VALID
			else {
				if( !operators.contains(expression.get(i)) ) return false;
			}
		}
		return true;
	}
	
	// Riordina l'espressione in base alla priorità degli operatori
	// dividendo expression in 2 List: sortedExpNum e sortedExpOp
	private void sortByPriority() {
		// aggiungo tutti i * e / a sortedExpOp e
		// i numeri alla loro destra e (eventuale) sinistra a sortedExpNum
		while(expression.contains("*") || expression.contains("/"))
			for(int i=0;i<expression.size();i++) {
				if(expression.get(i).equals("*") || expression.get(i).equals("/")) {
					sortedExpOp.add(expression.remove(i));
					// se a sinistra c'è un numero => prendi prima quello
					if(i>0 && numbers.contains(expression.get(i-1))) {
						sortedExpNum.add(numbers.indexOf(expression.remove(i-1)));
						sortedExpNum.add(numbers.indexOf(expression.remove(i-1)));
					}
					// altrimenti => prendi solo quello a destra
					else sortedExpNum.add(numbers.indexOf(expression.remove(i)));
					break;
				}
			}
		
		while(expression.contains("+") || expression.contains("-"))
			for(int i=0;i<expression.size();i++) {
				if(expression.get(i).equals("+") || expression.get(i).equals("-")) {
					sortedExpOp.add(expression.remove(i));
					// se a sinistra c'è un numero => prendi prima quello
					if(i>0 && numbers.contains(expression.get(i-1))) {
						sortedExpNum.add(numbers.indexOf(expression.remove(i-1)));
						if(i>0 && numbers.contains(expression.get(i-1))) sortedExpNum.add(numbers.indexOf(expression.remove(i-1)));
					}
					// se a destra c'è un numero => prendi solo quello a destra
					else if(numbers.contains(expression.get(i))) sortedExpNum.add(numbers.indexOf(expression.remove(i)));
					break;
				}
			}
		
		sortedExpOp.add(expression.remove(0));
	}
	
//	private void calcHighPriority() {
//		while(expression.contains("*") || expression.contains("/")) {
//			if(expression.get(0).equals("*") || expression.get(0).equals("/") ) {
//					
//			}
//			else expression.add(expression.remove(i));
//		}
//	}
	
	private void calc() {
		double result = 0;
		String op = "";
		
		for(int i=0;i<sortedExpOp.size();i++) {
			if(i==0) {
				result = sortedExpNum.get(i);
			}
			
			op = sortedExpOp.get(i);
			switch(op) {
			case "+":
				result += sortedExpNum.get(i+1);
				break;
			case "-":
				result -= sortedExpNum.get(i+1);
				break;
			case "*":
				result *= sortedExpNum.get(i+1);
				break;
			case "/":
				result /= sortedExpNum.get(i+1);
				break;
			case "=":
				System.out.println("Result: "+result);
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Calculator c = new Calculator();
		
		Scanner s = new Scanner(System.in);
		String input;
			
		do {
			input = s.nextLine().replaceAll("\\s","");
			c.saveExpression(input);
		}while(input.charAt(input.length()-1) != '=');	
		
		s.close();
		
		System.out.println(c.expression);
		
		c.startCalc();
		
		System.out.println(c.sortedExpNum);
		System.out.println(c.sortedExpOp);
	}
}
