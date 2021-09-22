package it.beije.herse.oca;
public class esercizi {

    public static void main(String[] args) {
            /* ESERICIO 6
1   654321
12   54321
123   4321
1234   321
12345   21
123456   1
    */
        int[] arr = {6,5,4,3,2,1};
        int x = arr.length-1;

     for (int i=0;i<arr.length;i++) {
                       for (int j = arr.length - 1; j >= x; j--) {
                                                 System.out.print(arr[j]);
                        }
                       x--;
                       System.out.print("   ");

                        for (int z = i; z < arr.length; z++) {
                            System.out.print(arr[z]);
    }
         System.out.print("\n");
}
        /* ESERCIZIO 7
        Scrivere un programma che stampi i primi 100 elementi della successione di Fibonacci.
         */

        int primo = 0;
        int secondo = 1;
        int tot;
        for(int i=0; i<45;i++){
            tot = primo + secondo;
            System.out.print(tot + ", ");
            primo = secondo;
            secondo = tot;
        }



        /* ESERCIZIO 8
        Scrivere un programma che stampi le n righe della successione di Fibonacci in questo modo:
1
1, 1
1, 1, 2
1, 1, 2, 3
1, 1, 2, 3, 5
1, 1, 2, 3, 5, 8
1, 1, 2, 3, 5, 8, 13
    */
        System.out.println(" ");

        int ultimo=0;

        for(int i=0; i<45;i++) {

            int x1=0;
            int y=1;
            int z=x1+y;

            System.out.print(y);

              while(z<=ultimo) {

                  System.out.print(", " + z);

                  x1=y;
                  y=z;
                  z=x1+y;

              }
            System.out.print("\n");
            ultimo=z;

          }

          }
      }




