package com.dh.serieservice.config;

import com.dh.serieservice.models.SerieInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private String port;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(this.host);
        configuration.setPort(Integer.parseInt(this.port));

        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, SerieInfo> movieRedisTemplate() {
        RedisTemplate<String, SerieInfo> template = new RedisTemplate<String, SerieInfo>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
