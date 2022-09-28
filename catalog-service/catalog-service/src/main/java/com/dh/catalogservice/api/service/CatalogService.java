package com.dh.catalogservice.api.service;

import com.dh.catalogservice.client.MovieClient;
import com.dh.catalogservice.client.SerieClient;
import com.dh.catalogservice.domain.model.MovieDTO;
import com.dh.catalogservice.domain.model.SerieDTO;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {

    @Value("${queue.movie.name}")
    private String movieQueue;
    @Value("${queue.series.name}")
    private String serieQueue;

    private final Logger LOG = LoggerFactory.getLogger(CatalogService.class);

    private final RabbitTemplate rabbitTemplate;
    private final MovieClient movieClient;
    private final SerieClient serieClient;

    @Autowired
    public CatalogService(MovieClient movieClient, RabbitTemplate rabbitTemplate, SerieClient serieClient){
        this.movieClient = movieClient;
        this.rabbitTemplate = rabbitTemplate;
        this.serieClient = serieClient;
    }

    //Obteniendo las peliculas  por genero
    public ResponseEntity<List<MovieDTO>> findMovieByGenre(String genre){
        LOG.info("Buscando peliculas por genero" + genre);
        return movieClient.getMovieByGenre(genre);
    }

    @CircuitBreaker(name = "movies", fallbackMethod = "moviesFallBackMethod")
    public ResponseEntity<List<MovieDTO>> findMovieByGenre(String genre, Boolean throwError){
        LOG.info("Buscando peliculas por genero" + genre);
        return movieClient.getMovieByGenreWithThrowError(genre, throwError);
    }

    //fallback
    public ResponseEntity<List<MovieDTO>> moviesFallBackMethod(CallNotPermittedException exception){
        LOG.info("Circuit breaker activado");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }


    //obteniendo series por genero
    public ResponseEntity<List<SerieDTO>> findSerieByGenre(String genre){
        LOG.info("Buscando series por genero" + genre);
        return serieClient.getSerieByGenre(genre);
    }

    @CircuitBreaker(name= "series", fallbackMethod = "seriesFallBackMethod")
    public ResponseEntity<List<SerieDTO>> findSerieByGenre(String genre, Boolean throwError){
        LOG.info("Buscando series por genero" + genre);
        return serieClient.getSerieByGenreWithThrowError(genre, throwError);
    }

    //fallBack
    public ResponseEntity<List<SerieDTO>> seriesFallBackMethod(CallNotPermittedException exception){
        LOG.info("Circuit breaker activado");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }


    //guardar  pelicula con rabbitMQ
    public void saveMovie(MovieDTO movieDTO){
        rabbitTemplate.convertAndSend(movieQueue, movieDTO);
    }

    //guardar serie con rabbitMQ
    public void saveSeries(SerieDTO serieDTO){
        rabbitTemplate.convertAndSend(serieQueue, serieDTO);
    }

}
