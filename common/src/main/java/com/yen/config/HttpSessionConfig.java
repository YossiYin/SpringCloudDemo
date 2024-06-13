package com.yen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Yhx
 * @date 2024/6/6 1:05
 * 将HttpSession中的数据序列化为JSON格式
 */
// TODO 开启后将改变session的序列化方式
//@Configuration
public class HttpSessionConfig {

//    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        // 使用Jackson2JsonRedisSerializer序列化会话数据为JSON格式
        return new Jackson2JsonRedisSerializer<>(Object.class);
    }
}

