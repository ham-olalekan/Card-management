package com.themint.cardmanagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    public static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

    private String redisUrl = "redis://127.0.0.1:6379"; //TODO this should be passed as env

    public RedisConfig() {
    }

    @Bean
    RedisConnectionFactory redisConnectionFactory() {
        final LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory();

        try {
            final URI redisUrlObj = new URI(redisUrl);
            final Optional<String> password =
                    Optional.of(redisUrlObj)
                            .map(URI::getUserInfo)
                            .map(url -> url.split(":"))
                            .map(Arrays::asList)
                            .map(list -> {
                                try {
                                    return list.get(1);
                                } catch (final IndexOutOfBoundsException e) {
                                    return null;
                                }
                            });
            final String host = redisUrlObj.getHost();
            final int port = redisUrlObj.getPort();

            log.info("Creating redis connection with params host [{}], port [{}], password [{}]", host, port, password.isPresent());

            redisConnectionFactory.setHostName(host);
            redisConnectionFactory.setPort(port);
            password.ifPresent(redisConnectionFactory::setPassword);
            return redisConnectionFactory;
        } catch (final Exception e) {
            // cannot start service without redis
            throw new RuntimeException(e);
        }
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(final RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}