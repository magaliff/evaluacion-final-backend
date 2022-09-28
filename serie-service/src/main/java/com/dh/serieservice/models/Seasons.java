package com.dh.serieservice.models;

import java.util.List;


public class Seasons {

    private Integer id;
    private Integer seasonNumber;
    private String genre;
    private List<Chapter> chapters;


    public Seasons() {
        //No-args constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", seasonNumber=" + seasonNumber +
                ", genre='" + genre + '\'' +
                ", chapters=" + chapters +
                '}';
    }

}
