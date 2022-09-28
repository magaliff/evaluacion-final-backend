package com.dh.serieservice.controller;


import com.dh.serieservice.models.Serie;
import com.dh.serieservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService service;

    @Autowired
    public SerieController(SerieService serieService){
        this.service = serieService;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(service.findByGenre(genre));
    }

    @GetMapping("/withErrors/{genre}")
    ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre, @RequestParam("throwError") boolean throwError){
        return ResponseEntity.ok().body(service.findByGenre(genre, throwError));
    }



    @PostMapping
    ResponseEntity<Serie> saveSeries(@RequestBody Serie serie){
        return  ResponseEntity.ok().body(service.saveSeries(serie));
    }







}
