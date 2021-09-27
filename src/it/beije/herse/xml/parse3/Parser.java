package it.beije.herse.xml.parse3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Parser {

	public String getRootElement(List<String> listOfRows, boolean rowToCheck) {

		StringBuilder sb = new StringBuilder();

		if(rowToCheck) {
			sb.append(listOfRows.get(0));
			System.out.println("prima riga");
			System.out.println(sb.toString());
		} else {
			sb.append(listOfRows.get(1));
			System.out.println("2 riga");
			System.out.println(sb.toString());
		}

		int lastRow = listOfRows.size()-1;

		StringBuilder sb2 = new StringBuilder();

		sb2.append(listOfRows.get(lastRow));

		try {
			sb.deleteCharAt(sb.indexOf("<"));
			sb.deleteCharAt(sb.indexOf(">"));
			sb2.deleteCharAt(sb2.indexOf("<"));
			sb2.deleteCharAt(sb2.indexOf("/"));
			sb2.deleteCharAt(sb2.indexOf(">"));
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Errore nella scrittura del tag (<, >, mancanti)");
		}

		//		System.out.println(sb.toString());
		//		System.out.println(sb2.toString());

		if(sb.toString().equals(sb2.toString())) {
			return sb.toString();
		}

		return null;
	}

	public List<String> getChildNodes(List<String> listaElementi, String tag){

		int i=0;
		List<String> result = new ArrayList<String>();

		while(true) {
			int[] arrayPos = getChildNodes2(listaElementi, tag, i);

			if(arrayPos[0]!=-1 && arrayPos[1]!=-1) {

				for(int j=arrayPos[0]+1; j<arrayPos[1];j++) {

					StringBuilder sb = new StringBuilder(listaElementi.get(j));
					result.add(sb.subSequence(sb.indexOf("<")+1,sb.indexOf(">")).toString());
					
				}
				result.add("");	
				i = arrayPos[1]+1;

			} else if (arrayPos[0] == -1 && arrayPos[1] == -1){
				break;
			} else {
				System.out.println("ERRORE");
				break;
			}
		}

		return result;
		
	}

	public int[] getChildNodes2(List<String> listaElementi, String tag, int i){
		int start=i;
		int end=i;
		int inizioNodo=-1;
		int fineNodo=-1;

		while(true) {
			if(start==listaElementi.size() || end==listaElementi.size()) {
				break;
			}

			if(listaElementi.get(start).contains("<"+tag+ ">")) {
				inizioNodo=start;
			}
			if(listaElementi.get(end).contains("</"+ tag+">")) {
				fineNodo=end;
				break;
			}

			if(inizioNodo==-1|| fineNodo==-1) {
				end++;
				start++;
				continue;
			}
		}

		int[] array = {inizioNodo,fineNodo};

		return array;

	}	
	
//	public String getTextContent(List<String> s, String tag) {
//
//	    StringBuilder sb = new StringBuilder();
//
//	    for(String x : s) {
//	        if(x.contains(tag)) {
//	          sb.append(x);
//	          break;
//	        }
//	      }
//
//	      String result = sb.subSequence(sb.indexOf(">"),sb.lastIndexOf("<", 1)).toString();
//
//	    return result;
//	  }

	public void xmlParser(String pathFile) throws IOException{

		FileReader fr = new FileReader(pathFile);

		List<String> listOfRows = new ArrayList<String>(); // creazione lista da creare

		BufferedReader reader = new BufferedReader(fr);

		try {
			String firstRow = null;

			firstRow = reader.readLine();

			if(firstRow.indexOf("<?xml") != 0 && !pathFile.substring(pathFile.length()-4, pathFile.length()).equals(".xml")) {
				throw new Exception();
			} else {
				listOfRows.add(firstRow);
			}
		} catch(Exception e) {
			System.out.println("il file non è xml"); //Se non è un file XML ritorna un errore.
			return;
		}

		while(reader.ready()) {
			listOfRows.add(reader.readLine());
		}

		boolean rowToCheck = listOfRows.get(0).indexOf("<?xml") != 0 ? true : false;

		String root = getRootElement(listOfRows, rowToCheck);

		System.out.println("Root: " + root);
		
		List<String> elements =  getChildNodes(listOfRows, "contatto");

		for(String s: elements) {
			System.out.println(s);
		}
		
//		String textCo = getTextContent(listOfRows, "nome");
		
	}

	public static void main(String[] args) throws IOException {
		new Parser().xmlParser("/temp/testProva.xml");
	}

}
