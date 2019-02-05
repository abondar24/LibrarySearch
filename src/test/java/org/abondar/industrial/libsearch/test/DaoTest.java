package org.abondar.industrial.libsearch.test;

import org.abondar.industrial.libsearch.data.*;
import org.abondar.industrial.libsearch.db.DAO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DaoTest {

    private static List<Publication> books;
    private static List<Publication> magazines;
    private static DAO dao;

    @BeforeClass
    public static void setUp() throws Exception{
        dao = DAO.getInstance();
        var author = new Author("email","first","last");
        dao.insertAuthors(List.of(author));

        var magazine = new Magazine("title", "1111-1111", getDate("01.01.1112"));
        var pam = new PublicationAuthor(magazine.getIsbn(),author.getEmail());
        magazine.setPaList(List.of(pam));

        var book = new Book("book", "2222-2222", "baasdasd");
        var pab = new PublicationAuthor(book.getIsbn(),author.getEmail());
        book.setPaList(List.of(pab));

        books = List.of(book);
        magazines = List.of(magazine);


    }

    @Test(expected = PersistenceException.class)
    public void testInsertPubsWrong(){
        dao.insertPublications(magazines,books);
    }

    @Test
    public void testInsertPubsCorrect(){
        dao.insertPublications(books,magazines);
    }


    private static Date getDate(String date) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException ex) {
            return new Date();
        }
    }

    @AfterClass
    public static void clean(){
        dao.cleanData();
    }
}
