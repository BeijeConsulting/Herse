package com.beije;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rubrica {
    public List<Contatto> loadRubricaFromCSV(String pathFile, String separator) throws IOException {

        FileReader fileReader = new FileReader(pathFile);

        BufferedReader reader = new BufferedReader(fileReader);
        String row = reader.readLine();
        String separator1 = new StringBuilder("\'").append(separator).append('\'').toString();
        String separator2 = new StringBuilder("\"").append(separator).append('\"').toString();
        String separator3 = null;
        if (row.contains(separator1)) {
            separator = separator1;
        } else if (row.contains(separator2)) {
            separator = separator2;
        } else {
            separator = separator;
        }
        System.out.println("separator: " + separator);

        String[] title = row.split(separator);
        int last = title.length - 1;
        if (separator.length() > 1) {
            title[0] = title[0].substring(1);
            title[last] = title[last].substring(0, title[last].length()-1);
        }

        List<Contatto> contatti = new ArrayList<Contatto>();
        Contatto contatto = null;
        String[] cols = null;
        while (reader.ready()) {
            row = reader.readLine();
            System.out.println(row);

            cols = row.split(separator);
            if (separator.length() > 1) {
                cols[0] = cols[0].substring(1);
                cols[last] = cols[last].substring(0, cols[last].length()-1);
            }

            contatto = new Contatto();
            for (int i = 0; i <= last; i++) {
                System.out.println(title[i] + ": " + cols[i]);
                switch (title[i].toUpperCase()) {
                    case "NOME":
                        contatto.setNome(cols[i]);
                        break;
                    case "COGNOME":
                        contatto.setCognome(cols[i]);
                        break;
                    case "TELEFONO":
                        contatto.setTelefono(cols[i]);
                        break;
                    case "EMAIL":
                        contatto.setEmail(cols[i]);
                        break;
                    case "NOTE":
                        contatto.setNote(cols[i]);
                        break;
                }
            }

            contatti.add(contatto);
            System.out.println();
        }

        reader.close();

        return contatti;

    }
}
