package it.beije.herse.oca.cap2.loops;

public class LoopExercise{

	public void printFirstTen(){
		for(int i=0;i<10;i++) System.out.print(i+" ");
	}

	public void printFirstTenEven(){
		int c=0;
		for(int i=0;i<20;i++){
			if(i%2==0){
				System.out.print(i+" ");
				c++;
			}
			if(c==10) break;
		}
	}

	public void printMult(int x){
		for(int i=0;i<=10;i++) System.out.println(i+": "+i*x);
	}

	public void printTriangle(){
		int x=6;
		for(int i=0;i<6;i++){
			for(int j=0;j<x;j++) System.out.print("*");
			System.out.println("");
			x--;
		}
	}

	public void printReverseTriangle(){
		int x=1;
		for(int i=0;i<6;i++){
			for(int j=0;j<x;j++) System.out.print("#");
			System.out.println("");
			x++;
		}
	}

	public void printMatrix(){
		int leftCount = 1;
		int rightCount = 6;
		for (int i=0;i<6;i++){
			//Left numeber
			for(int j=1;j<=leftCount;j++) System.out.print(j);
			leftCount++;

			System.out.print("  ");

			//Right number
			for(int j=rightCount;j>=1;j--) System.out.print(j);
			rightCount--;

			System.out.println("");
		}
	}

	public void firstOneHundredFibonacci(){
		long prec = 0L;
		long curr = 1L;
		long next;
		
		for(int i=0;i<100;i++){
			if(prec==0) System.out.println(prec);
			System.out.println(curr);
			next = prec + curr;
			prec = curr;
			curr = next;
		}
	}

	public void nFibonacci(int n){
		for(int j=0;j<n;j++){
			long prec = 0L;
			long curr = 1L;
			long next;
			for(int i=0;i<=j;i++){
				if(prec==0) System.out.print(prec+", ");
				if(i==j) System.out.print(curr+"\n");
				else System.out.print(curr+", ");
				next = prec + curr;
				prec = curr;
				curr = next;
			}
		}
	}			

	public static void main(String[] args){
		LoopExercise l = new LoopExercise();
		l.printFirstTen();
		System.out.println("\n");
		l.printFirstTenEven();
		System.out.println("\n");
		l.printMult(2);
		System.out.println("\n");
		l.printTriangle();
		System.out.println("\n");
		l.printReverseTriangle();
		System.out.println("\n");
		l.printMatrix();
		System.out.println("\n");
		l.firstOneHundredFibonacci();
		System.out.println("\n");
		l.nFibonacci(7);
	}
}