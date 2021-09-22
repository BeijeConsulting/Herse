package EserciziStringhe;
import java.util.Scanner;
public class Metodi {
	int i=0;
		public void SoloVocali(String s) {
			for(;i<s.length();i++)
				if(s.charAt(i)==65||s.charAt(i)==69||s.charAt(i)==73||s.charAt(i)==79||s.charAt(i)==85||s.charAt(i)==97||s.charAt(i)==101||s.charAt(i)==105||s.charAt(i)==111||s.charAt(i)==117)
					System.out.print(s.charAt(i));
		}
		
		public void StampaMaiuscole(String[] s) {
			for(;i<s.length;i++)
				if(s[i].charAt(0)>64&&s[i].charAt(0)<91)
					System.out.print(s[i]+" ");
		}
		
		public int contaLettera(char c, String str) {
			int cont=0;
			for(;i<str.length();i++)
				if(str.charAt(i)==c)
					cont++;
			return cont;
		}
		
		public void Contrario(String s) {
			for(i=s.length();i>=0;i--)
				System.out.print(s.charAt(i-1));
		}
		
		public void Concatena(){
			String[] s=new String[3];
			System.out.println("Inserisci tre parole: ");
			for(;i<3;i++) {
				System.out.println((i+1)+"^: ");
				Scanner scanner=new Scanner(System.in);
				s[i]=scanner.next();
			}
			for(i=0;i<3;i++){
				System.out.print(s[i]);
				if(i+1<=2)
					System.out.print("*");
			}
		}
		
		







}
