package org.abondar.industrial.libsearch.csv;

public enum InputFiles {

    Authors("autoren.csv"),
    Books("buecher.csv"),
    Magazines("zeitschriften.csv");

    private String fileName;

    InputFiles(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
