package org.abondar.industrial.libsearch.db;

public enum PubTypes {

    Book(1),
    Magazine(2);

    private int type;

    PubTypes(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
