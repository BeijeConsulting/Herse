package it.beije.herse.oca;
public class array {

    public static void trovaMax(int[] x) {
        int max = x[0];
        for (int i = 1; i < x.length; i++) {
            if (x[i] > max) {
                max = x[i];
            }
        }
        System.out.println(max);
    }

    public static int trovaIndexMin(int[] x) {
        int min = 0;
        for (int i = 1; i < x.length; i++) {
            if (x[i] < x[min]) {
                min = i;
            }
        }
        return min;
    }

    public static class esercizioBoolean {

        boolean trovato;
        boolean trovato2;

        boolean contains(int e, int[] array) {

            for (int i = 0; i < array.length; i++) {
                if (array[i] == e) {
                    trovato = true;
                    break;
                }
            }
            return trovato;
        }

        boolean contains(Object e, Object[] array) {

            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(e)) {
                    trovato2 = true;
                    break;
                }
            }
            return trovato2;
        }
    }

    public static boolean isCrescente(int[] x) {
        boolean ok = true;
        for (int i = 1; i < x.length; i++) {
            if (x[i] < x[i - 1]) {
                ok = !ok;
                break;
            }

        }
        return ok;
    }

    public int mostRecurrent(int[] x) {
        int most = x[0];
        int maxRip=1;
        for (int i = 0; i < x.length; i++) {
            int quante = 0;
            for (int j = 0; j < x.length; j++) {
                if (x[i] == x[j]) {
                    quante++;
                }
            }
            if (quante > maxRip) {
                maxRip = quante;
                most = x[i];

            }
        }
        return (maxRip > 1 ? most : (99999996));
    }

    public double MediaMultipliDiTre(int[] x){
        int media = 0;
        int quanti =0;
        for(int i=0; i<x.length; i++){
            if(x[i]%3==0){
                media+=x[i];
                quanti++;
            }
        }
        return (quanti>0 ? (double) media/ (double) quanti : 0);
    }

    public String [] addString(String s, String[] a){

        String[] nuovo = new String[a.length+1];
        for(int i = 0; i< a.length;i++){
            nuovo[i]= a[i];
        }
        nuovo[a.length]= s;
        return nuovo;
    }

        public static void main(String[] args) {
            // Trovare il massimo elemento in un array (o il minimo)
            int x[] = {-1, -19, 0, 1, 1, 3, 44};
            trovaMax(x);
            // Trovare l’indice del massimo elemento in un array (o il minimo)
            int min = trovaIndexMin(x);
            System.out.println("indice minimo " + min);
            // Scrivere un metodo “boolean contains(int e, int[] array)” che restituisca true se l’elemento e è presente nell’array,
            // false altrimenti. Ripetere l’esercizio con “boolean contains(Object e, Object[] array)”, quali differenze ci sono?
            esercizioBoolean booly = new esercizioBoolean();
            boolean p = booly.contains(56, x);
            System.out.println(p);
            Object e = new Object();
            Object e2 = new Object();
            Object[] array = {e};
            boolean p2 = booly.contains(e, array);
            System.out.println(p2);
            //Verificare la sequenza crescente di un array. Il metodo “boolean isCrescente(int [] array)” restituisce true se tutti gli elementi
            // dell’array passato sono in ordine crescente, false altrimenti.
            System.out.println(isCrescente(x));
            int[] x1 = {1, 6, 3, 4, 5};
            System.out.println(isCrescente(x1));
            // Scrivere il metodo: “public int mostRecurrent(int [] array)” , che trova l’elemento più ricorrente in un array.
            // Il metodo restituisce l’elemento trovato.
            array ric = new array();
            System.out.println(ric.mostRecurrent(x1));
            System.out.println(ric.mostRecurrent(x));
            // Scrivere un programma MediaMultipliDiTre che calcoli la media di un array di numeri interi, considerando i soli numeri divisibili per tre.
            System.out.println(ric.MediaMultipliDiTre(x1));
            // Scrivere un programma StampaZigZag che, dato un array di 10 numeri interi contenente valori a piacere, ne stampa gli elementi
            // secondo il seguente ordine: il primo, l’ultimo, il secondo, il penultimo, il terzo, il terz’ultimo, ecc…

            int[] z =  {1,2,3,4,5,6,7,8,9,10};



            for(int i=0,j=z.length-1; i<j; i++,j--){
                System.out.print(z[i]);
                System.out.print(z[j]);
            }

           // Scrivere un programma Media che calcoli la media di un array di numeri interi
            int media = 0;
            for(int i=0;i<z.length;i++){
                media+=z[i];
            }
            System.out.println((double)media/z.length);

           // Scrivere il metodo: “public String [] addString(String s, String[] a)”, che accetta come parametri una stringa ed un array di stringhe.
            // Restituisce un nuovo array, identico ad array, aggiungendo però, come ultimo elemento, la stringa s.
            String[] prova = {"Pippo", "Pluto"};
            String[] stampa = ric.addString("Paperino",prova);
            for(int i = 0; i<stampa.length;i++){
                System.out.println(stampa[i]);
            }
        }
    }
