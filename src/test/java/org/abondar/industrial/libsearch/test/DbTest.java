package org.abondar.industrial.libsearch.test;


import org.abondar.industrial.libsearch.data.*;
import org.abondar.industrial.libsearch.db.DataMapper;
import org.abondar.industrial.libsearch.db.DbConnection;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;

public class DbTest {

    private static DataMapper mapper;
    private static DbConnection dbConn;

    @BeforeClass
    public static void setUp() throws Exception {
        dbConn = new DbConnection();
        mapper = dbConn.getMapper();
    }

    @Test
    public void insertAuthorsTest() {
        String email = "email@test.com";
        var author1 = new Author(email, "test", "author");
        mapper.insertUpdateAuthors(List.of(author1));

        var author = mapper.getAuthorByEmail(email);

        assertEquals(author1.getFirstName(), author.getFirstName());
        assertEquals(author1.getLastName(), author.getLastName());

        mapper.deleteAuthors();
        dbConn.commitSession();

    }

    @Test
    public void insertPublicationTest() {
        var publication = new Book("iiiii", "1111-1111", "dfdfd");
        var insMap = Map.of("publ_type", 1, "publ", publication);

        mapper.insertUpdatePublications(List.of(insMap));

        var insRes = mapper.getPublicationRows();
        System.out.println(insRes);

        assertEquals(1L, insRes.size());
        assertEquals("1111-1111", insRes.get(0).get("isbn"));

        mapper.deletePublications();
        dbConn.commitSession();
    }

    @Test
    public void insertBookTest() {
        var publication = new Book("iiiii", "1111-1111", "dfdfd");
        var insMap = Map.of("publ_type", 1, "publ", publication);

        mapper.insertUpdatePublications(List.of(insMap));
        mapper.insertUpdateBooks(List.of(publication));

        var insRes = mapper.getBookRows();
        System.out.println(insRes);

        assertEquals(1L, insRes.size());
        assertEquals("1111-1111", insRes.get(0).get("book_isbn"));
        assertEquals("dfdfd", insRes.get(0).get("description"));

        mapper.deleteBooks();
        mapper.deletePublications();
        dbConn.commitSession();
    }


    @Test
    public void insertMagazineTest() {
        var date = getDate("02.02.1124");
        var publication = new Magazine("iiiii", "1111-1111", date);
        var insMap = Map.of("publ_type", 2, "publ", publication);

        mapper.insertUpdatePublications(List.of(insMap));
        mapper.insertUpdateMagazines(List.of(publication));

        var insRes = mapper.getMagazineRows();
        System.out.println(insRes);

        assertEquals(1L, insRes.size());
        assertEquals("1111-1111", insRes.get(0).get("mag_isbn"));
        assertEquals(date, insRes.get(0).get("pub_date"));

        mapper.deleteMagazines();
        mapper.deletePublications();
        dbConn.commitSession();
    }


    @Test
    public void insertPublicationAuthorsTest() {
        var author = new Author("email@test.com", "test", "author");
        mapper.insertUpdateAuthors(List.of(author));

        var publication = new Book("iiiii", "1111-1111", "dfdfd");
        var insMap = Map.of("publ_type", 1, "publ", publication);

        mapper.insertUpdatePublications(List.of(insMap));
        mapper.insertUpdateBooks(List.of(publication));

        var pa = new PublicationAuthor("1111-1111", "email@test.com");

        mapper.insertUpdatePublicationAuthors(List.of(pa));
        dbConn.commitSession();
        assertTrue(pa.getId() > 0);

        mapper.deleteBooks();
        mapper.deletePublicationAuthors();
        mapper.deletePublications();
        mapper.deleteAuthors();

        dbConn.commitSession();

    }


    @Test
    public void getAllPublicationsTest() {
        var date = getDate("02.02.1124");
        var magazine = new Magazine("magazine", "1111-1111", date);
        var book = new Book("book", "2222-2222", "dfdfd");

        var bookMap = Map.of("publ_type", 1, "publ", book);
        var magMap = Map.of("publ_type", 2, "publ", magazine);

        mapper.insertUpdatePublications(List.of(bookMap,magMap));
        mapper.insertUpdateMagazines(List.of(magazine));
        mapper.insertUpdateBooks(List.of(book));

        var res = mapper.getAllPublications();
        System.out.println(res);

        assertEquals(2L, res.size());
        assertNotNull(res.get(0));
        assertNotNull(res.get(1));

        mapper.deleteMagazines();
        mapper.deleteBooks();
        mapper.deletePublications();
        dbConn.commitSession();
    }

    @Test
    public void getSortedPublicationsTest() {
        var date = getDate("02.02.1124");
        var magazine = new Magazine("magazine", "1111-1111", date);
        var mag = new Magazine("a magazine", "1111-22", date);
        var book = new Book("book", "2222-2222", "dfdfd");

        var bookMap = Map.of("publ_type", 1, "publ", book);
        var magMap = Map.of("publ_type", 2, "publ", magazine);
        var mag1Map = Map.of("publ_type", 2, "publ", mag);


        mapper.insertUpdatePublications(List.of(bookMap,magMap,mag1Map));
        mapper.insertUpdateMagazines(List.of(magazine,mag));
        mapper.insertUpdateBooks(List.of(book));

        var res = mapper.getSortedPublications();

        assertEquals(3L, res.size());
        assertNotNull(mag.getTitle(),res.get(0).getTitle());
        assertEquals(book.getTitle(),res.get(1).getTitle());
        assertNotNull(magazine.getTitle(),res.get(2).getTitle());

        mapper.deleteMagazines();
        mapper.deleteBooks();
        mapper.deletePublications();
        dbConn.commitSession();
    }



    @Test
    public void getPublicationByIsbnTest() {
        var magDate = getDate("02.02.1124");
        var magIsbn = "1111-1111";
        var magTitle = "magazine";

        var magazine = new Magazine(magTitle, magIsbn, magDate);
        var book = new Book("book", "2222-2222", "dfdfd");

        var bookMap = Map.of("publ_type", 1, "publ", book);
        var magMap = Map.of("publ_type", 2, "publ", magazine);

        mapper.insertUpdatePublications(List.of(bookMap,magMap));
        mapper.insertUpdateMagazines(List.of(magazine));
        mapper.insertUpdateBooks(List.of(book));

        magazine = (Magazine) mapper.getPublicationByIsbn(magIsbn);


        assertEquals(magIsbn,magazine.getIsbn());
        assertEquals(magTitle,magazine.getTitle());
        assertEquals(magDate,magazine.getPubDate());


        mapper.deleteMagazines();
        mapper.deleteBooks();
        mapper.deletePublications();
        dbConn.commitSession();
    }


    @Test
    public void getPublicationsAuthorTest() {
        var magDate = getDate("02.02.1124");
        var bookDescr = "descr";
        var magTitle = "magazine";

        var magazine = new Magazine(magTitle, "1111-1111", magDate);
        var book = new Book("book", "2222-2222", bookDescr);
        var author = new Author("email@test.com", "test", "author");
        var paMag = new PublicationAuthor(magazine.getIsbn(), author.getEmail());
        var paBook = new PublicationAuthor(book.getIsbn(), author.getEmail());

        var bookMap = Map.of("publ_type", 1, "publ", book);
        var magMap = Map.of("publ_type", 2, "publ", magazine);

        mapper.insertUpdateAuthors(List.of(author));
        mapper.insertUpdatePublications(List.of(bookMap,magMap));
        mapper.insertUpdateMagazines(List.of(magazine));
        mapper.insertUpdateBooks(List.of(book));
        mapper.insertUpdatePublicationAuthors(List.of(paMag,paBook));


        var res = mapper.getPublicationsByAuthor(author.getEmail());
        System.out.println(res);

        assertEquals(2L, res.size());
        assertNotNull(res.get(0));
        assertNotNull(res.get(1));

        book = (Book) res.get(0);
        magazine = (Magazine) res.get(1);
        assertEquals(bookDescr,book.getDescription());
        assertEquals(magDate,magazine.getPubDate());

        mapper.deleteMagazines();
        mapper.deleteBooks();
        mapper.deletePublicationAuthors();
        mapper.deletePublications();
        dbConn.commitSession();
    }


    private Date getDate(String date) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException ex) {
            return new Date();
        }

    }

}
