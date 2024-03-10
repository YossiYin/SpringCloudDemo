# 项目介绍

微服务框架模板

# 部署教程

## 环境部署

1. JDK 17

2. MySQL 8

3. Nacos 2.2.0

   ```
   下载地址: https://github.com/alibaba/nacos/releases/tag/2.2.0
   部署时记住nacos的端口
   ```

4. redis

   ```
   记录redis的端口和密码
   ```

## 模块配置

1. 网关gateway配置

   ```yml
   spring:
     cloud:
       nacos:
         discovery:
           server-addr: 127.0.0.1:8848 # nacos地址
   ```

2. Feign客户端service-client

   ```yml
   spring:
     cloud:
       nacos:
         discovery:
           server-addr: 127.0.0.1:8848 # nacos地址
   ```

   

3. 用户服务user-service配置

   ```yml
   spring:
     # 数据库配置
     datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/数据库名
       username: root
       password: 数据库密码
     cloud:
     nacos:
       discovery:
         server-addr: 127.0.0.1:8848 # nacos服务地址	
     data:
     # redis配置
     redis:
       database: 1
       host: localhost 
       port: 6379 
       password: 123456 
   ```
   
    

## 启动顺序

1. nacos + mysql + redis
2. gateway
3. 其他服务service

# 依赖介绍

## 父工程
* Nacos 服务发现：spring-cloud-starter-alibaba-nacos-discovery
* 公共依赖Spring Cloud：spring-cloud-dependencies
* 负载均衡：spring-cloud-loadbalancer
* Sentinel 网关限流：spring-cloud-alibaba-sentinel-gateway
* Sentinel轻量级高可用流量控制组件：spring-cloud-starter-alibaba-sentinel
* MyBatis-Plus3.0：mybatis-plus-spring-boot3-starter
* redis：spring-boot-starter-data-redis
* 实现分布式session：spring-session-data-redis
* Hutool工具类：hutool-all
* 集合工具类库：commons-collections4
* 开发用热部署工具：spring-boot-devtools

### 网关模块gateway
* spring-boot-starter-webflux：为 Web 应用提供响应式编程支持。非阻塞编程

### 服务客户端模块service-client
* spring-cloud-starter-openfeign：声明式服务调用

