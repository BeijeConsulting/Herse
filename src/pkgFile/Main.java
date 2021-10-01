package pkgFile;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Users\\samue\\Desktop\\prova.txt");
		if(f.exists()) {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String row = null;
			int nn = 0;
			double bb = 0.0, ss = 0.0;
			while(br.ready()) {
				row = br.readLine();
				String[] val = row.split(" ");
				nn++;
				if(val[3].equalsIgnoreCase("B")) {
					bb = bb + (Double.parseDouble(val[1]) * Integer.parseInt(val[2]));
				}else if(val[3].equalsIgnoreCase("S")) {
					ss = ss + (Double.parseDouble(val[1]) * Integer.parseInt(val[2]));
				}
			}
			System.out.println("Op: " + nn + "; Buy: " + bb + "; Sell: " + ss);
		}
	}
}
