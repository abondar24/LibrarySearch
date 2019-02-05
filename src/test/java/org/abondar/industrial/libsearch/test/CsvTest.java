package org.abondar.industrial.libsearch.test;

import org.abondar.industrial.libsearch.csv.CsvParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CsvTest {

    private static CsvParser parser;

    @BeforeClass
    public static void setUp(){
        parser = new CsvParser();
    }

    @Test
    public void readAuthorsTest() {
        var res = parser.readAuthors("authorsTest.csv");

        assertEquals(2L,res.size());
        assertEquals("pr-walter@optivo.de",res.get(0).getEmail());
        assertEquals("Paul",res.get(0).getFirstName());
        assertEquals("Walter",res.get(0).getLastName());
    }


    @Test
    public void readBooksTest() {
        var res = parser.readPublications("booksTest.csv",true);
        assertEquals(2L,res.size());
        assertEquals("2145-8548-3325",res.get(0).getIsbn());
    }


    @Test
    public void readMagazinesTest() {
        var res = parser.readPublications("magTest.csv",false);
        assertEquals(2L,res.size());
        assertEquals("4545-8541-2012",res.get(0).getIsbn());
    }
}
