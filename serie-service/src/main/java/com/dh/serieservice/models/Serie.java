package com.dh.serieservice.models;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
public class Serie  {

    public Long id;
    public String name;
    public String genre;
    public List<Seasons> seasons;

    public Serie(){
        //No-args constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Seasons> getSeasons() {
        return seasons;
    }

    public void ListSeasons(List<Seasons> seasons) {
        this.seasons = seasons;
    }



    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", seasons='" + seasons + '\'' +
                '}';
    }
}
