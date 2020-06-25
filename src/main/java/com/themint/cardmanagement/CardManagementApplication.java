package com.themint.cardmanagement;

import com.themint.cardmanagement.model.Payload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class CardManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardManagementApplication.class, args);
	}
}
