package it.beije.herse.oca.cap2.loops;

public class LabelsEx{
	public void loopBreak(){
		int xCord = 1;
		int yCord = 0;
		OUTER_LOOP: for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(i==yCord && j==xCord){
					xCord++;
					yCord++;
					break OUTER_LOOP;
				}
				System.out.print("* ");
			}
			System.out.println("");
		}
	}

	public void loopContinue(){
		int xCord = 2;
		int yCord = 2;
		OUTER_LOOP: for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(i==yCord && j==xCord) continue OUTER_LOOP;
				System.out.print("* ");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args){
		LabelsEx l = new LabelsEx();
		l.loopBreak();
		System.out.println("");
		//l.loopContinue();
	}
}