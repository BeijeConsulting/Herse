package it.beije.herse.oca;

import java.util.Scanner;

public class Calcolatrice {

	private double risultato;

	public void setRisultato(double risultato) {
		this.risultato=risultato;
	}
	public double getRisultato() {
		return this.risultato;
	}

	public void on() {

		boolean end=false;
		Scanner s = new Scanner(System.in);
		double num;
		String operazione=null;
		System.out.println("La calcolatrice è pronta");
		System.out.println("");

		System.out.println("Inserisci numero..");
		setRisultato(s.nextDouble());


		while(!end) {

			System.out.println("Seleziona operazione da svolgere: *, /, -, +, e");
			System.out.println("Per uscire digita: exit");
			operazione = s.next();
			if(operazione.equals("exit")) {
				end = true;
				break;
			}

			System.out.println("Inserisci numero..");
			num = s.nextDouble();
			this.risultato=calcola(this.risultato, operazione, num);
			System.out.println("Risultato: " + getRisultato());

		}

		s.close();

	}

	private double calcola(double x, String operazione, double y) {

		double result=0;

		switch(operazione) {
		case "*":
			result = x*y;
			break;
		case "/":
			result = x/y;
			break;
		case "+":
			result=x+y;
			break;
		case "-":
			result=x-y;
			break;
		case "e":
			double z = y;
			result=1;
			while(z>0) { result=result*x; z--; }
			break;
		default:
			System.out.println("ERRORE NEL CALCOLO");
			break;    
		}

		return result;
	}

	// NUOVO 

	static final String[] operazioni = {"e","/","*","-","+"};

	public void calcolaRiga() {

		boolean error = false;

		while(true) {
			Scanner s = new Scanner(System.in);
			System.out.println("Scrivi l'espressione da valutare o scrivi 'exit' per uscire");
			String x = s.nextLine();
			if(x.equals("exit")) break;
			StringBuilder sb = new StringBuilder(x.trim());
			StringBuilder sb2 = new StringBuilder();
			int risultato = 0;
			int num1=0;
			int num2=0;
			boolean change = false;
			int in=0,out=0;
			boolean negStamp=false;

			for(int i=0;i<operazioni.length; i++) {


				if(!sb.toString().contains(operazioni[i])) continue;

				while(sb.toString().contains(operazioni[i]) && !negStamp) {
					
					if(sb.indexOf(operazioni[i])==0 && i==3) {negStamp=true;}

					else if (sb.toString().indexOf(operazioni[i])-1<0 || sb.toString().indexOf(operazioni[i])+1>=sb.length()) error=true;

					for(int j=sb.toString().indexOf(operazioni[i])-1, x1=0; j>=0 ; j--,x1++) {
						
						if(sb.charAt(j)>= 48 && sb.charAt(j)<=57) {
							
							if(negStamp && sb.indexOf("-",1)>0) {
							num1+= -(sb.charAt(j)-48) * (int)(calcola(10,"e",x1)); // QUA BISOGNA VEDERE
							}else {
						    num1+= (sb.charAt(j)-48) * (int)(calcola(10,"e",x1));
							}

						} else {
							in=j+1;
							break;
						}

					}

					for(int k=sb.toString().indexOf(operazioni[i])+1; k<sb.length();k++) {

						if(sb.charAt(k)>= 48 && sb.charAt(k)<=57){
							sb2.append(sb.charAt(k));

						} else {
							out=k;
							break;
						}
						out=k+1;
					}
					sb2.reverse();
					int k=sb2.length()-1;
					while(k>=0) {
						num2 += (sb2.charAt(k)-48) * (int)(calcola(10,"e",k)); 
						k--;
					}


					sb2.delete(0, sb2.length());
					risultato = (int)(calcola(num1, operazioni[i], num2));
					if(negStamp && in>0) {
						in--;
						negStamp=!negStamp;
						}
					
					sb.replace(in,out,""+risultato);
					in=0;
					out=0;

					num1=0;
					num2=0;
					System.out.println(negStamp);
				}
			}
			
			System.out.println( !error ? risultato : "Errore");
			error=false;
		}
	}

	public static void main(String... args) {
		Calcolatrice calcolatrice = new Calcolatrice();
		calcolatrice.calcolaRiga();
		//calcolatrice.on();

	}
}


