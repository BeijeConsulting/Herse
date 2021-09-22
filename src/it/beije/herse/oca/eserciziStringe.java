package it.beije.herse.oca;
public class eserciziStringe {

    public static void SoloVocali(String x){

        for(int i=0; i<x.length(); i++){
            if(x.charAt(i)=='a' || x.charAt(i)=='e' || x.charAt(i)=='o' || x.charAt(i)=='i' || x.charAt(i)=='u') {
                System.out.print(x.charAt(i));
            }
        }
    }

    public static void StampaMaiuscole(String[] x){
        for(int i = 0; i<x.length;i++){
            char nuova = x[i].toUpperCase().charAt(0);
            char vecchia = x[i].charAt(0);
            if(nuova==vecchia){
                System.out.println(x[i]);
            }
        }
    }

    public static void Contrario(String x){
        StringBuilder s = new StringBuilder();
        for(int i= x.length()-1; i>=0; i--){
            s.append(x.charAt(i));
        }
        System.out.println(s.toString());
    }

    public static void Concatena(String x, String y, String z){
        String tot= x.concat("*"+ y + "*" + z);
        System.out.println(tot);
    }

    public static void setter(String x){
        String z = x;
        System.out.println("public void set"+ z + "(String x){ \n bambi = z; \n }");
    }
    public static void getter(String g){

        System.out.println("public get "+ g + "(String x){ \n return " + g + "; \n }");

    }

    public static void main(String[] args) {
        // Scrivere un programma SoloVocali che, data una stringa, ne stampa le sole vocali
        SoloVocali("caeiouy");
        System.out.print("\n");


        // Scrivere un programma StampaMaiuscole che, dato un array di stringhe, ne stampa solo quelle con la prima lettera maiuscola
        String[] arr2 = {"Poco", "poco","AAAA"};

        StampaMaiuscole(arr2);

        // Scrivere il metodo
        //1  public int contaLettera(char c, String str)
        //che conta le occorrenze della lettera c nella stringa str
         class cosi {

            public int contaLettera(char c, String str) {
                int howmany = 0;
                for (int i = 0; i < str.length(); i++) {
                    if (str.toLowerCase().charAt(i) == c) {
                        howmany++;
                    }
                }
                return howmany;
            }
        }
        cosi c = new cosi();
        int howmany = c.contaLettera('c',"Cacciatore");
        System.out.println(howmany);


        // Scrivere un programma Contrario che, data una stringa, la stampa al contrario. Per esempio, la stringa “Viva Java!” verrà “!avaJ aviV”
        Contrario("Viva Java!");

       // Scrivere un programma Concatena che chiede all’utente di inserire tre singole parole e le ristampa interponendovi un asterisco. Per esempio, se l’utente inserisce “gatto”, “cane” e “topo” il programma stamperà “gatto*cane*topo”.
        Concatena("cane","gatto","topo");

        // Scrivere un metodo che, data una stringa in input, assuma questa come un nome di variabile e stampi per questa variabile il suo metodo “setter”
        setter("bambi");
        // Scrivere un metodo che, data una stringa in input, assuma questa come un nome di variabile e stampi per questa variabile il suo metodo “getter”
        getter("animale");
    }
}


