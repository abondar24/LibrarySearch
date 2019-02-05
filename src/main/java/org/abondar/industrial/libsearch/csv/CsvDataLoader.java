package org.abondar.industrial.libsearch.csv;

import org.abondar.industrial.libsearch.data.Author;
import org.abondar.industrial.libsearch.db.DAO;
import java.util.List;

public class CsvDataLoader {

    private CsvParser csvParser;
    private DAO dao;

    public CsvDataLoader(DAO dao) {
        csvParser = new CsvParser();
        this.dao = dao;
    }

    public void loadData() {
        List<Author> authors = csvParser.readAuthors(InputFiles.Authors.getFileName());

        var books = csvParser.readPublications(InputFiles.Books.getFileName(), true);
        var magazines = csvParser.readPublications(InputFiles.Magazines.getFileName(), false);

        dao.insertAuthors(authors);
        dao.insertPublications(books, magazines);
    }

}
