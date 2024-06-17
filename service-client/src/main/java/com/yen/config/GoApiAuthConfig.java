package com.yen.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @author Yhx
 * @date 2024/6/17 19:14
 */
public class GoApiAuthConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // 添加 Cookie 头部信息，这里假设 Cookie 是 "myCookie=value"
                template.header("Cookie", "token=iamsupervip");
            }
        };
    }
}
