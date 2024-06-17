package com.yen.service;

import com.yen.config.GoApiAuthConfig;
import com.yen.dto.other.TestResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 实现带Header调用第三方接口
 * 使用自定义配置拦截请求设置cookie
 *
 * @author Yhx
 * @date 2024/6/17 15:58
 */


@FeignClient(name = "test-server",url = "http://192.168.0.180:8600",path = "newpay-go/api",configuration = GoApiAuthConfig.class)
public interface TestFeignClient {

    @PostMapping(value = "/order/signList")
    TestResponse test2(@RequestParam("storeId") String storeId);


}
