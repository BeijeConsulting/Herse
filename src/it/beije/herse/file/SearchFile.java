package it.beije.herse.file;

import java.io.File;
import java.util.ArrayList;

public class SearchFile {

	public static ArrayList<String> searchDir(File path) {

		ArrayList<String> otherDir = new ArrayList<String>();

		if(path.isDirectory()) {

			String[] listFD = path.list();

			for(int i = 0; i < listFD.length; i++)
				if(new File(path.getPath() + "\\" + listFD[i]).isDirectory())
					otherDir.add(path.getPath() + "\\" + listFD[i]);

		}

		return otherDir;

	}

	public static ArrayList<String> searchFile(File path){

		ArrayList<String> otherFile = new ArrayList<String>();

		if(path.isDirectory()) {

			String[] listFD = path.list();

			for(int i = 0; i < listFD.length; i++) {
				if(new File(path.getPath() + "\\" + listFD[i]).isFile())
					otherFile.add(listFD[i]);
			}

		}

		return otherFile;

	}

	public static void printAll(ArrayList<String> path) {
		
		for(String s : path) {
			
			System.out.println(s + "\\");
			printFile(searchFile(new File(s)));
			
		}
		
	}
	
	public static void printFile(ArrayList<String> path) {
		
		for(String s : path)
			System.out.println("\t\t\t\t" + s);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File f = new File("\\Users\\savin\\Desktop");
		printAll(searchDir(f));
		
	}

}
