package org.abondar.industrial.libsearch.data;


import java.util.Date;
import java.util.List;

public class Magazine extends Publication {

    private Date pubDate;

    public Magazine() {
    }

    public Magazine(String title, String isbn, Date pubDate, List<PublicationAuthor> paList) {
        super(title, isbn, paList);
        this.pubDate = pubDate;
    }

    public Magazine(String title, String isbn, Date pubDate) {
        super(title, isbn);
        this.pubDate = pubDate;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "isbn: " + getIsbn() + ", " +
                "title: " + getTitle() + ", " +
                "publication data:" + pubDate + "\n";
    }

}
