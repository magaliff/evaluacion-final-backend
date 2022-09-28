package com.dh.serieservice.utils;

import com.dh.serieservice.models.Serie;
import com.dh.serieservice.models.SerieInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
public class RedisUtils {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(RedisUtils.class);

    private static final long TTL = 20L;

    private final RedisTemplate<String, SerieInfo> serieRedisTemplate;

    @Autowired
    public RedisUtils(RedisTemplate<String, SerieInfo> serieInfoRedisTemplate){
        this.serieRedisTemplate = serieInfoRedisTemplate;
    }

    public void createMovieInfo(String genre, List<Serie> series){

        if(!CollectionUtils.isEmpty(series)) {
            SerieInfo serieInfo = new SerieInfo();
            serieInfo.setSeries(series);
            serieInfo.setGenre(genre);
            serieInfo.setCreateDate(LocalDateTime.now().toString());

            serieRedisTemplate.opsForValue().set(genre.toUpperCase(), serieInfo, TTL, TimeUnit.SECONDS);
            LOG.info("Serie information created on Redis" + genre);
        }else{
            LOG.warning("Series was empty por genre" +  genre);
        }
    }

        public SerieInfo getSerieInfo(String genre){
            String cachekey = genre.toUpperCase();
            LOG.info("Serie information was foun on Redis" + genre);
            return serieRedisTemplate.opsForValue().get(cachekey);
        }



}
