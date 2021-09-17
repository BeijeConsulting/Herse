package it.beije.herse.oca;

import java.util.ArrayList;
import java.util.Scanner;

public class Calcolatrice {

	public void evaluate(String e) {
		String result = scanBrackets(e);
		if(result != null)
			System.out.println(e + " = " + scanBrackets(e));
		else
			System.out.println("Espressione non valida");
	}

	private double sum(double x, double y) {
		return x + y;
	}

	private double dif(double x, double y) {
		return x - y;
	}

	private double mol(double x, double y) {
		return x * y;
	}

	private double div(double x, double y) {

		double result = 0;

		try {
			result = x / y;
		}catch(ArithmeticException e) {
			System.err.println(e);
		}

		return result;

	}

	private Double scanExpression(String e) {

		boolean error = false;
		StringBuilder value = new StringBuilder();
		ArrayList<Double> values = new ArrayList<Double>();
		ArrayList<String> operators = new ArrayList<String>();

		for(int i = 0; i < e.length() && !error; i++) {
			
			if(e.charAt(i) >= '0' && e.charAt(i) <= '9' || e.charAt(i) == '.') {
				
				value.append(e.charAt(i));
				
			}else if(i == 0 && e.charAt(i) == '-') {
				
				value.append(e.charAt(i));
				
			}else if(e.charAt(i) == '+' || e.charAt(i) == '-' || e.charAt(i) == '*' || e.charAt(i) == '/') {
				
				operators.add(String.valueOf(e.charAt(i)));
				values.add(Double.parseDouble(value.toString()));
				value.delete(0, value.length());
				
			}else if(e.charAt(i) == ',') {
				
				value.append(".");
				
			}else {
				
				error = true;
				
			}

			if(i == e.length()-1) {
				
				values.add(Double.parseDouble(value.toString()));
				value.delete(0, value.length());
				
			}

		}

		if(!error) {

			for(int i = 0; i < operators.size(); i++) {

				if(operators.get(i).equals("*")) {

					double val = mol(values.get(i), values.get(i+1));
					values.remove(i+1);
					values.set(i, val);
					operators.remove(i);

					i--;

				}else if(operators.get(i).equals("/")) {

					double val = div(values.get(i), values.get(i+1));
					values.remove(i+1);
					values.set(i, val);
					operators.remove(i);

					i--;

				}

			}

			for(int i = 0; i < operators.size(); i++) {

				if(operators.get(i).equals("+")) {

					double val = sum(values.get(i), values.get(i+1));
					values.remove(i+1);
					values.set(i, val);
					operators.remove(i);

					i--;

				}else if(operators.get(i).equals("-")) {

					double val = dif(values.get(i), values.get(i+1));
					values.remove(i+1);
					values.set(i, val);
					operators.remove(i);

					i--;

				}

			}

		}else {
			return null;
		}

		return values.get(0);

	}

	private String scanBrackets(String e) {

		int sIndex = e.indexOf("(");
		int eIndex = e.indexOf(")");
		String result = "";
		StringBuilder s = new StringBuilder(e);
		boolean error = false;

		while(s.indexOf("(",sIndex) != -1) {
			
			boolean exit = false;

			while(!exit) {
				if(s.indexOf("(", sIndex+1) != -1 && s.indexOf("(", sIndex+1) < eIndex)
					sIndex = s.indexOf("(", sIndex+1);
				else
					exit = true;
			}

			result = resolveBrackets(s.substring(sIndex+1, eIndex));

			if(result != null)
				s.replace(sIndex, eIndex+1, result);
			else
				error = true;

			sIndex = s.indexOf("(", sIndex=0);
			eIndex = s.indexOf(")", eIndex=0);

		}
		
		if(error)
			return null;

		return scanExpression(s.toString()).toString();

	}

	private String resolveBrackets(String e) {
		return String.valueOf(scanExpression(e));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		Calcolatrice c = new Calcolatrice();
		String expression = scan.nextLine();

		c.evaluate(expression);

		scan.close();

	}

}
