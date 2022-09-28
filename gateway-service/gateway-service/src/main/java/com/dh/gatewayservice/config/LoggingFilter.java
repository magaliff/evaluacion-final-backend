package com.dh.gatewayservice.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.ObjectInputFilter;

@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config>{

    private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

    public LoggingFilter() {
        super(Config.class);
    }



    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            LOG.info("Ha ingresado una request para el path " + exchange.getRequest().getPath());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                LOG.info("Ha finalizado el procesamiento de la request");
            }));
        });
    }

    public static class Config{}


}