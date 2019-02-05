package org.abondar.industrial.libsearch.data;

public class PublicationAuthor {

    private long id;

    private String publIsbn;

    private String authEmail;

    public PublicationAuthor(){}

    public PublicationAuthor(String isbn, String email) {
        this.publIsbn = isbn;
        this.authEmail = email;
    }

    public String getPublIsbn() {
        return publIsbn;
    }

    public void setPublIsbn(String publIsbn) {
        this.publIsbn = publIsbn;
    }

    public String getAuthEmail() {
        return authEmail;
    }

    public void setAuthEmail(String authEmail) {
        this.authEmail = authEmail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PublicationAuthor{" +
                "id=" + id +
                ", publIsbn='" + publIsbn + '\'' +
                ", authEmail='" + authEmail + '\'' +
                '}';
    }
}
