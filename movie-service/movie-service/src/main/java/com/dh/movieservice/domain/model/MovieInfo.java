package com.dh.movieservice.domain.model;

import java.io.Serializable;
import java.util.List;

public class MovieInfo {


    private String createdDate;
    private String genre;
    private List<Movie> movies;

    public MovieInfo(){
        //No-args constructor
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
