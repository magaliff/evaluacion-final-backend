package com.dh.movieservice.api.controller;


import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;
    @Autowired
    public MovieController(MovieService movieService) {

        this.service = movieService;
    }
    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(service.findByGenre(genre));
    }

    @GetMapping("/withErrors/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre, @RequestParam("throwError") boolean throwError) {
        return ResponseEntity.ok().body(service.findByGenre(genre, throwError));
    }

    @PostMapping
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(service.saveMovie(movie));
    }










}
