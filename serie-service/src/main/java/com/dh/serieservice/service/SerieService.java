package com.dh.serieservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.dh.serieservice.models.Serie;
import com.dh.serieservice.models.SerieInfo;
import com.dh.serieservice.repository.SerieRepository;
import com.dh.serieservice.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public class SerieService {

    private static final Logger LOG = LoggerFactory.getLogger(SerieService.class);

    @Value("${queue.series.name}")
    private String serieQueue;

    private final SerieRepository repository;
    private final RedisUtils redisUtils;

    @Autowired
    public SerieService(SerieRepository serieRepository, RedisUtils redisUtils){
        this.repository = serieRepository;
        this.redisUtils = redisUtils;
    }

    public List<Serie> findByGenre(String genre){
        SerieInfo serieInfo = redisUtils.getSerieInfo(genre);
        if(Objects.nonNull(serieInfo)){
            return serieInfo.getSeries();
        }
        List<Serie> series = repository.findByGenre(genre);
        redisUtils.createMovieInfo(genre, series);
        return series;
    }

    public List<Serie> findByGenre(String genre, Boolean throwError){
        LOG.info("Buscando series por genero");
        if (throwError){
            LOG.error("Hubo un error al buscar las series");
            throw new RuntimeException();
        }
        return repository.findByGenre(genre);
    }




    @RabbitListener(queues = "${queue.series.name}")
    public Serie saveSeries(Serie serie){
        LOG.info("Se recibio una serie a travez de rabbit" + serie.toString());
        return repository.save(serie);
    }








}
