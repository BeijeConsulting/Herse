package it.beije.herse.oca;
public class morra {

    public static void main(String[] args) {
       String[] s = {"sasso","carta","forbice","sasso"};
       boolean vince=false;

        if (args[0].equals(args[1])) {
            System.out.println("patta");
        } else {
            for (int i = 0; i<3;i++){
                  if(args[0].equals(s[i])){
                      vince = args[1].equals(s[i+1]);
                }
            }
            String fine = "Vince il giocatore";
            String chi = vince ? " 2" : " 1";
            System.out.println(fine + chi);
        }

    }
}
