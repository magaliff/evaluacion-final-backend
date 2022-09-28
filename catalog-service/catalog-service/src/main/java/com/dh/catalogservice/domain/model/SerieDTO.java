package com.dh.catalogservice.domain.model;

public class SerieDTO {

    private String id;
    private String name;
    private String genre;

    public SerieDTO() {
        //No-args contructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
