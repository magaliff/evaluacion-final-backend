package com.dh.gatewayservice.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @CircuitBreaker(name = "moviesServie")
    @GetMapping("/movies")
    public ResponseEntity<String> seriesFallBack(){
        return new ResponseEntity<>("Servidor de movie no disponible", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CircuitBreaker(name = "CatalogService")
    @GetMapping("/catalogs")
    public ResponseEntity<String> catalogsFallack(){
        return new ResponseEntity<>("Servidor de catalogs no disponible", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CircuitBreaker(name = "seriesService")
    @GetMapping("/series")
    public ResponseEntity<String> seriesFallack(){
        return new ResponseEntity<>("Servidor de series no disponible", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
