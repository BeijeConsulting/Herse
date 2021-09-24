package Esercizi;
public class Main {
	public static void main(String[] args) {
		int[] n= {5,3,8,1,3,35,80,3,65,32};
		int i;
		String[] s= {"ciao","mi", "chiamo"};
		String s2="Omar";
		Es1 es1=new Es1();
		Es2 es2=new Es2();
		Es3 es3=new Es3();
		Es4 es4=new Es4();
		Es5 es5=new Es5();
		Es6 es6=new Es6();
		Es7 es7=new Es7();
		Es8 es8=new Es8();
		Es9 es9=new Es9();
		System.out.print("Primo esercizio: ");
/*Es.1*/es1.MaxArray(n);
		System.out.print("Secondo esercizio: ");
/*Es.2*/es2.MaxIndex(n);
/*Es.3*/System.out.print("Terzo esercizio: L'elemento "+14+" è presente nell’array? "+es3.contains(14,n)+"\n");
/*Es.4*/System.out.print("Quarto esercizio: Array in ordine crescente? "+es4.isCrescente(n)+"\n");
/*Es.5*/System.out.print("Quinto esercizio: Il numero con più ricorrenze è:  "+es5.mostRecurrent(n)+"\n");
/*Es.6*/System.out.print("Sesto esercizio: ");
		es6.MediaMultipliDiTre(n);
/*Es.7*/System.out.print("Settimo esercizio: ");
		es7.StampaZigZag(n);
/*Es.8*/System.out.print("Ottavo esercizio: ");
		es8.Media(n);		
/*Es.9*/System.out.print("Nono esercizio: ");
		s=es9.addString(s2, s);
		for(i=0;i<s.length;i++)
			System.out.print(s[i]+" ");
	}

}
