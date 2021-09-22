package it.beije.herse.oca;
import java.util.Scanner;

public class CFS {
    // Scrivere un programma che chieda agli utenti due stringhe in ingresso, le stringhe possono valere solo: “carta”, “forbice” o “sasso”.
    // Il programma dovrà quindi effettuare i dovuti controlli e dichiarare il vincitore secondo le note regole della “morra cinese”
    // (forbice vince su carta, carta vince su sasso, sasso vince su forbice).


    public static void main(String[] args) {
        boolean ok = false;
        String in="",in2="";
        Scanner scanner = new Scanner(System.in);

        while (!ok) {
            System.out.println("GIOCATORE 1 - SCEGLIE: sasso forbice carta");
            in = scanner.nextLine();
            if (in.equals("sasso") || in.equals("forbice") || in.equals("carta")){

                System.out.println("GIOCATORE 2 - SCEGLIE: sasso forbice carta");
                in2 = scanner.nextLine();
            }
                if (in2.equals("sasso") || in2.equals("forbice") || in2.equals("carta")) {
                    ok=true;
                } else {
                    System.out.println("Inserire stringhe valide");
                }

        }

        boolean uguali = in.equals(in2);
        boolean vince=false;

        if (!uguali) {
            switch (in) {
                case "carta":
                    vince = in2.equals("sasso");
                    break;
                case "sasso":
                    vince = in2.equals("forbice");
                    break;
                case "forbice":
                    vince = in2.equals("sasso");
                    break;
            }

            String r = vince ? "La prima stringa vince sulla seconda" : "La seconda stringa vince sulla prima";
            System.out.println(r);

        } else {
            System.out.println("La partita è patta");
        }


    }

    }
