package org.abondar.industrial.libsearch.db;

import org.abondar.industrial.libsearch.data.Author;
import org.abondar.industrial.libsearch.data.Publication;
import org.abondar.industrial.libsearch.data.PublicationAuthor;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;




public class DAO {

    private DbConnection dbConn;
    private DataMapper mapper;
    private static DAO instance;

    private DAO() throws Exception {
        dbConn = new DbConnection();
        mapper = dbConn.getMapper();

    }

    public static DAO getInstance() throws Exception{
        if (instance==null){
            instance = new DAO();
        }

        return instance;
    }

    public void insertAuthors(List<Author> authors) {
        mapper.insertUpdateAuthors(authors);
        dbConn.commitSession();
    }

    public void insertPublications(List<Publication>books, List<Publication> magazines){
        List<PublicationAuthor> paList = new ArrayList<>();
        List<Map<String, Object>> insMap = new ArrayList<>();
        final String PUBL_TYPE = "publ_type";
        final String PUBL = "publ";


        books.forEach(b -> {
            Map<String, Object> pubMap = new HashMap<>();
            pubMap.put(PUBL_TYPE, PubTypes.Book.getType());
            pubMap.put(PUBL, b);
            insMap.add(pubMap);
            paList.addAll(b.getPaList());
        });

        magazines.forEach(m -> {
            Map<String, Object> pubMap = new HashMap<>();
            pubMap.put(PUBL_TYPE, PubTypes.Magazine.getType());
            pubMap.put(PUBL, m);
            insMap.add(pubMap);
            paList.addAll(m.getPaList());
        });


        mapper.insertUpdatePublications(insMap);
        mapper.insertUpdateBooks(books);
        mapper.insertUpdateMagazines(magazines);
        mapper.insertUpdatePublicationAuthors(paList);
        dbConn.commitSession();

    }

    public List<Publication> returnAllPublications(){
        return mapper.getAllPublications();
    }


    public List<Publication> returnSortedPublications(){
        return mapper.getSortedPublications();
    }

    public Publication returnPublicationByIsbn(String isbn){
        return mapper.getPublicationByIsbn(isbn);
    }

    public List<Publication> returnPublicationsByAuthor(String authorEmail) {
        return mapper.getPublicationsByAuthor(authorEmail);
    }

    public void cleanData(){

        mapper.deleteMagazines();
        mapper.deleteBooks();
        mapper.deletePublicationAuthors();
        mapper.deletePublications();
        mapper.deleteAuthors();
        dbConn.commitSession();
    }


}
