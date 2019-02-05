package org.abondar.industrial.libsearch;

import org.abondar.industrial.libsearch.csv.CsvDataLoader;
import org.abondar.industrial.libsearch.db.DAO;

public class Main {

    public static void main(String[] args) throws Exception {
        var dao = DAO.getInstance();
        var daLoader = new CsvDataLoader(dao);
        var bookIsbn = "4545-8558-3232";
        var magIsbn = "2547-8548-2541";
        var authorEmail = "pr-walter@optivo.de";

        System.out.println("Loading data");
        daLoader.loadData();

        var pubs = dao.returnAllPublications();
        System.out.println("All publications");
        pubs.forEach(System.out::println);

        System.out.println("All publications with sorting");
        pubs = dao.returnSortedPublications();
        pubs.forEach(System.out::println);


        System.out.println("Book by ISBN: " + bookIsbn);
        System.out.println(dao.returnPublicationByIsbn(bookIsbn));

        System.out.println("Magazine by ISBN: "+magIsbn);
        System.out.println(dao.returnPublicationByIsbn(magIsbn));

        System.out.println("Publications by Author: "+authorEmail);
        pubs =dao.returnPublicationsByAuthor(authorEmail);
        pubs.forEach(System.out::println);



    }
}
