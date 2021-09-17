package it.beije.herse.oca;

import java.util.ArrayList;
import java.util.Scanner;

public class NewCalcolatrice {

	ArrayList<Double> values = new ArrayList<Double>();
	ArrayList<String> operators = new ArrayList<String>();

	public void evaluate(String e) {

		if(e.equals("+") || e.equals("-")) {

			operators.add(e);

			if(operators.size() == 2)
				scan(operators.get(0),operators.size()-2);

		}else if(e.equals("*") || e.equals("/")){

			operators.add(e);

		}else if(e.equals("=")) {

			scan(operators.get(0),operators.size()-1);

		}else if(Double.parseDouble(e) <= 0 || Double.parseDouble(e) > 0) {

			values.add(Double.parseDouble(e));

			if(operators.size() > 0)
				if(operators.get(operators.size()-1).equals("*") || operators.get(operators.size()-1).equals("/"))
					scan(operators.get(operators.size()-1),operators.size()-1);

		}

	}

	private void scan(String op, int index) {

		switch(op) {

		case "+":
			values.set(index, sum(values.get(index), values.get(index+1)));
			values.remove(index+1);
			operators.remove(index);
			break;

		case "-":
			values.set(index, dif(values.get(index), values.get(index+1)));
			values.remove(index+1);
			operators.remove(index);
			break;

		case "*":
			values.set(index, mol(values.get(index), values.get(index+1)));
			values.remove(index+1);
			operators.remove(index);
			break;

		case "/":
			values.set(index, div(values.get(index), values.get(index+1)));
			values.remove(index+1);
			operators.remove(index);
			break;

		}

	}

	private Double sum(double x, double y) {
		return x + y;
	}

	private Double dif(double x, double y) {
		return x - y;
	}

	private Double mol(double x, double y) {
		return x * y;
	}

	private Double div(double x, double y) {

		double result = 0;

		try {
			result = x / y;
		}catch(ArithmeticException e) {
			System.err.println(e);
		}

		return result;

	}

	public void print(String e) {

		if(values.size() > 1) {

			for(int i = 0; i < values.size(); i++) {
				System.out.print(values.get(i) + " ");
				if(!operators.isEmpty() && operators.size() > i)
					System.out.print(operators.get(i) + " ");
			}

			if(operators.get(0).equals("+"))
				System.out.println("= " + sum(values.get(0),values.get(1)));
			else if(operators.get(0).equals("-"))
				System.out.println("= " + sum(values.get(0),values.get(1)));

		}else if(e.charAt(e.length()-1) == '='){
			System.out.println(e + " " + values.get(0));
		}else {
			System.out.println(e + "\n" + values.get(0));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		NewCalcolatrice c = new NewCalcolatrice();
		String value = "";
		StringBuilder expression = new StringBuilder();
		int count = 0;

		do {

			if(count % 2 == 0) {
				System.out.println("Inserisci un valore:");
				value = s.nextLine();
			}else {
				System.out.println("Inserisci un operatore:");
				value = s.nextLine();
				if(value.length() > 1 || value.charAt(0) != '+' && value.charAt(0) != '-' && value.charAt(0) != '*' && value.charAt(0) != '/' && value.charAt(0) != '=')
					value = "";
				System.out.println(value);
			}

			if(!value.isEmpty()) {

				expression.append(value);

				count++;

				c.evaluate(value);

				c.print(expression.toString());

			}else {
				System.out.println("Valore non valido");
			}

		}while(!value.equals("="));

		c.print(expression.toString());

		s.close();

	}

}
