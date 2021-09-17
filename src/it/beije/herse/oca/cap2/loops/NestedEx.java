package it.beije.herse.oca.cap2.loops;

public class NestedEx{
	public void doubleFor(){
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				System.out.print("* ");
			}
			System.out.println("");
		}
	}

	public void doPlusDoWhile(){
		int i=0;
		do{
			int j=0;
			while(j<5){
				System.out.print("* ");
				j++;
			}
			System.out.println("");
			i++;
		}while(i<5);
	}

	public static void main(String[] args){
		NestedEx n = new NestedEx();
		n.doubleFor();
		System.out.println("");
		n.doPlusDoWhile();
	}
}