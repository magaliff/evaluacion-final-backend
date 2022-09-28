package com.dh.serieservice.models;


import java.util.List;

public class SerieInfo {


    private String createDate;
    private String genre;
    private List<Serie> series;

    public SerieInfo(){
        //No-args constructor
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }
}
