package com.yen.config;

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author Yhx
 * @date 2024/6/6 1:05
 * 将HttpSession中的数据序列化为JSON格式
 */
//@Configuration
public class HttpSessionConfig {

    // TODO 开启后将改变session的序列化方式
//    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        // 使用Jackson2JsonRedisSerializer序列化会话数据为JSON格式
        return new Jackson2JsonRedisSerializer<>(Object.class);
    }

    // TODO 开启后将改变session给浏览器设置Cookie时是否使用Base64编码,但开启后需要手动设置过期时间
//    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        // 不使用Base64编码
        serializer.setUseBase64Encoding(false);
        serializer.setCookieMaxAge(2592000);
        return serializer;
    }
}

