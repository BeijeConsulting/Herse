package pkgFileXMLins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		try {
			List<Utente> u = leggiFileXml("C:\\Users\\samue\\eclipse-workspace\\Beije\\src\\pkgFileXMLins\\file.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	public static List<Utente> leggiFileXml(String path) throws IOException {
		File file = new File(path);
		System.out.println("exists ? " + file.exists());
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String row = null;
		String stringaEncoding = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		
		List<Utente> listaUtenti = new ArrayList<Utente>();
		String nome ="";
		String cognome ="";
		int eta =-1;
		
		while (br.ready()) {
			row = br.readLine();
			row = row.trim();
			if (row.equals(stringaEncoding))
				continue;
			StringBuilder val = new StringBuilder(row);
			String tag = val.substring(val.indexOf("<") + 1, val.indexOf(">"));
					
			if (val.indexOf(">") == val.length() - 1) {
				if(tag.contains("/utente")) {
					listaUtenti.add(new Utente(nome, cognome, eta));
				}
				continue;
			} else {
				//if(tag.equals("/utente")) {
					String campo = val.substring(val.indexOf(">") + 1, val.lastIndexOf("<"));
					System.out.println(campo);
					switch (tag) {
					case "nome":
						nome = campo;
						break;
					case "cognome":
						cognome = campo;
						break;
					case "eta":
						eta = Integer.parseInt(campo);						
						break;
					}
				//}
			}
			
		}
		System.out.println(listaUtenti);
		return listaUtenti;
	}
}
