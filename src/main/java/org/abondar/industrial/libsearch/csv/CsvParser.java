package org.abondar.industrial.libsearch.csv;


import org.abondar.industrial.libsearch.data.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvParser {
    private final String csvFolder = "/csv/";


    public List<Author> readAuthors(String fileName) {
        List<Author> authors = new ArrayList<>();
        var is = CsvParser.class.getResourceAsStream(csvFolder + fileName);

        var br = new BufferedReader(new InputStreamReader(is));
        br.lines()
                .forEach(l -> {
                    String[] data = l.split(";");

                    if (!"Emailadresse".equals(data[0])){
                        authors.add(new Author(data[0], data[1], data[2]));
                    }

                });
        return authors;
    }

    public List<Publication> readPublications(String fileName, boolean isBook) {
        List<Publication> publications = new ArrayList<>();
        var is = CsvParser.class.getResourceAsStream(csvFolder + fileName);

        var br = new BufferedReader(new InputStreamReader(is));
        br.lines()
                .forEach(l -> {
                    String[] data = l.split(";");
                    if (!"Titel".equals(data[0])) {
                        String[] authors = data[2].split(",");

                        Publication publication;
                        if (isBook) {
                            publication = new Book(data[0], data[1], data[3],getPubAuthors(data[1], authors));

                        } else {
                            publication = new Magazine(data[0], data[1], getDate(data[3]),getPubAuthors(data[1], authors));

                        }
                        publications.add(publication);
                    }
                });


        return publications;
    }


    private Date getDate(String date) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException ex) {
            return new Date();
        }

    }

    private List<PublicationAuthor> getPubAuthors(String isbn, String[] authors) {
        List<PublicationAuthor> res = new ArrayList<>();

        for (int i = 0; i < authors.length; i++) {
            var pa = new PublicationAuthor(isbn, authors[0]);
            res.add(pa);
        }

        return res;
    }


}
