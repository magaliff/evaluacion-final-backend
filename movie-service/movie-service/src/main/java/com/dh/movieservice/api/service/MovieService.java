package com.dh.movieservice.api.service;

import com.dh.movieservice.domain.model.Movie;
import com.dh.movieservice.domain.model.MovieInfo;
import com.dh.movieservice.domain.repository.MovieRepository;
import com.dh.movieservice.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository repository;
    private final RedisUtils redisUtils;

    @Autowired
    public MovieService(MovieRepository movieRepository, RedisUtils redisUtils) {
        this.repository = movieRepository;
        this.redisUtils = redisUtils;
    }

    public List<Movie> findByGenre(String genre){
        MovieInfo movieInfo = redisUtils.getMovieInfo(genre);
        if (Objects.nonNull(movieInfo)) {
            return movieInfo.getMovies();
        }
        List<Movie> movies = repository.findByGenre(genre);
        redisUtils.createMovieInfo(genre, movies);
        return movies;
    }

    public List<Movie> findByGenre(String genre, Boolean throwError) {
        LOG.info("buscando peliculas por genero");
        if (throwError) {
            LOG.error("Hubo un error al buscar las películas");
            throw new RuntimeException();
        }
        return repository.findByGenre(genre);
    }

    @RabbitListener(queues = "${queue.movie.name}")
    public Movie saveMovie(Movie movie) {
        LOG.info("Se recibio una pelicula a través de rabbit " + movie.toString());
        return repository.save(movie);
    }


}

