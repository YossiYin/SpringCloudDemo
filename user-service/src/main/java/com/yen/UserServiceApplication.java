package com.yen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Yhx
 * @date 2024/3/10 16:21
 */
@SpringBootApplication
@MapperScan("com.yen.mapper")
@EnableScheduling // 开启定时任务
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true) // 开启AOP
@EnableDiscoveryClient // 服务发现
@EnableFeignClients(basePackages = {"com.yen.service"}) // 声明feign客户端
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
