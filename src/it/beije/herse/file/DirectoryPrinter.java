package it.beije.herse.file;

import java.io.File;
import java.io.FileWriter;

public class DirectoryPrinter {
	
	private static String tab = "";
	private FileWriter writer;

	public DirectoryPrinter(File output) throws Exception {
		writer = new FileWriter(output);
	}

	public void printAllContent(File f){
		if(f.isDirectory()) {
			String[] dirList = f.list();
			for(int i=0;i<dirList.length;i++) {
				File nextFile = new File(f+"\\"+dirList[i]);
				
				char id = '*';
				if(nextFile.isDirectory()) id = '\\';
				System.out.println(tab+id+dirList[i]);
				
				tab += '\t';
				printAllContent(nextFile);
				tab = tab.substring(1);
			}
		}
	}
	
	public void writeAllContent(File f) throws Exception {
		if(f.isDirectory()) {
			String[] dirList = f.list();
			for(int i=0;i<dirList.length;i++) {
				File nextFile = new File(f+"\\"+dirList[i]);
				
				char id = '*';
				if(nextFile.isDirectory()) id = '\\';
				writer.write(tab+id+dirList[i]);
				writer.write('\n');
				
				tab += '\t';
				writeAllContent(nextFile);
				tab = tab.substring(1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		File output = new File("/temp/file/DirectoryPrinter.txt");
		File directory = new File("/temp");
		
		DirectoryPrinter p = new DirectoryPrinter(output);
		//p.printAllContent(directory);
		p.writeAllContent(directory);
		p.writer.flush();
		p.writer.close();
		System.out.println("File creato");
	}

}
