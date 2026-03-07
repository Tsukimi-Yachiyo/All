package com.yachiyo.Config;

import com.yachiyo.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FastMethodConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {
        return new RedisSerializer<Object>() {
            @Override
            public byte[] serialize(@Nullable Object value) throws SerializationException {
                return new byte[0];
            }

            @Override
            public @Nullable Object deserialize(byte @Nullable [] bytes) throws SerializationException {
                return null;
            }
        };
    }

    /**
     * 从token中获取用户
     */
    public User getUserFromToken(String token) {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
