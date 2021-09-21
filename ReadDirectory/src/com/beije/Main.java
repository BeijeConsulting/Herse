package com.beije;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static String getPercorso(){
        System.out.println("Inserisci percorso");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        return path;
    }
    public static ArrayList getFIles(String path){
        File f = new File(path);
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));

        return files;
    }

    public static String parsePath(File path){
        String strPath = path.toString();


    }

    public static void main(String[] args) {
        String percorso = getPercorso();
        System.out.println(getFIles(percorso));
    }
}
