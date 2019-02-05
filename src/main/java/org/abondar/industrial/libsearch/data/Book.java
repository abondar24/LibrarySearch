package org.abondar.industrial.libsearch.data;

import java.util.List;

public class Book extends Publication {


    private String description;


    public Book(){

    }

    public Book(String title, String isbn, String description, List<PublicationAuthor> pa) {
        super(title, isbn,pa);
        this.description = description;
    }

    public Book(String title, String isbn, String description) {
        super(title, isbn);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "isbn: "+getIsbn() +", " +
                "title: "+getTitle() +", " +
                "description:" + description + "\n";
    }
}
