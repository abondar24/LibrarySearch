package org.abondar.industrial.libsearch.data;


import java.util.List;

public class Publication {

    private String title;

    private String isbn;

    public List<PublicationAuthor> paList;

    public Publication(){}

    public Publication(String title, String isbn, List<PublicationAuthor> paList) {
        this.title = title;
        this.isbn = isbn;
        this.paList = paList;
    }

    public Publication(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<PublicationAuthor> getPaList() {
        return paList;
    }

    public void setPaList(List<PublicationAuthor> paList) {
        this.paList = paList;
    }
}
