package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.MovieDTO;
import com.dh.catalogservice.domain.model.SerieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

	private final CatalogService catalogService;

	@Autowired
	public CatalogController(CatalogService catalogService){
		this.catalogService = catalogService;
	}

	@GetMapping("/{genre}")
	public ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre){
		return catalogService.findMovieByGenre(genre);
	}

	@GetMapping("/withErrors/{genre}")
	public ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre, @RequestParam("throwError") Boolean throwError){
		return catalogService.findMovieByGenre(genre, throwError);
	}


	@PostMapping("/save")
	public ResponseEntity<String> saveMovie(@RequestBody MovieDTO movieDTO){
		catalogService.saveMovie(movieDTO);
		return ResponseEntity.ok("Pelicula enviada a la cola");
	}

	@PostMapping("/save/series")
	public ResponseEntity<String> saveSeries(@RequestBody SerieDTO serieDTO){
		catalogService.saveSeries(serieDTO);
		return ResponseEntity.ok("Serie enviada a la cola");
	}











}
