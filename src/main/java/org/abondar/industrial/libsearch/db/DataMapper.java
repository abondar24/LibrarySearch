package org.abondar.industrial.libsearch.db;

import org.abondar.industrial.libsearch.data.Author;
import org.abondar.industrial.libsearch.data.Publication;
import org.abondar.industrial.libsearch.data.PublicationAuthor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataMapper {

    void insertUpdateAuthors(@Param("authorList") List<Author> authorList);

    void insertUpdatePublications(@Param("pubList") List<Map<String,Object>> pubList);

    void insertUpdateBooks(@Param("bookList") List<Publication> bookList);

    void insertUpdateMagazines(@Param("magList") List<Publication> magList);

    void insertUpdatePublicationAuthors(@Param("paList") List<PublicationAuthor> paList);

    Author getAuthorByEmail(@Param("email") String email);

    List<Map<String,Object>> getPublicationRows();

    List<Map<String,Object>> getBookRows();

    List<Map<String,Object>> getMagazineRows();

    List<Publication> getAllPublications();

    List<Publication> getSortedPublications();

    Publication getPublicationByIsbn(@Param("isbn") String isbn);

    List<Publication> getPublicationsByAuthor(@Param("email") String email);

    void deleteAuthors();

    void deletePublications();

    void deleteBooks();

    void deleteMagazines();

    void deletePublicationAuthors();
}
