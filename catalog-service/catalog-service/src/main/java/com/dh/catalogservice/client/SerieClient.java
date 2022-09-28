package com.dh.catalogservice.client;

import com.dh.catalogservice.domain.model.SerieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "serie-service")
public interface SerieClient {

    @GetMapping("/series/{genre}")
    ResponseEntity<List<SerieDTO>> getSerieByGenre(@PathVariable("genre") String genre);

    //probar el CircuitBreaker
    @GetMapping("/series/withErrors/{genre}")
    ResponseEntity<List<SerieDTO>> getSerieByGenreWithThrowError(@PathVariable(value = "genre") String genre,
                                                         @RequestParam("throwError") boolean throwError);
}