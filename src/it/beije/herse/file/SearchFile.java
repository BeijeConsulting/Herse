package it.beije.herse.file;

import java.io.File;

public class SearchFile {

	public static boolean search(File path) {

		boolean existsDir = false;

		if(path.isDirectory()) {

			String[] listFD = path.list();
			
			System.out.println(path.getPath() + "\\");
			
			for(int i = 0; i < listFD.length; i++) {
				
				if(new File(path.getPath() + "\\" + listFD[i]).isFile()) {
					System.out.println("\t\t" + listFD[i]);
				}else {
					//System.out.println("\t" + listFD[i]);
					search(new File(path.getPath() + "\\" + listFD[i]));
				}
				
			}

		}

		return existsDir;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File f = new File("\\Users\\savin\\Desktop\\Test OCA");
		search(f);
		
	}

}
