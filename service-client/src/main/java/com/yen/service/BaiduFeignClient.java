package com.yen.service;

import com.yen.dto.baidu.TranslateRequest;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 第三方百度接口
 * @author Yhx
 * @date 2024/6/14 0:02
 */
@FeignClient(value = "baidu-server",url = "http://api.fanyi.baidu.com",path = "/api")
public interface BaiduFeignClient {

    @PostMapping("trans/vip/translate")
    String translate(@RequestParam("q") String q,
                     @RequestParam("from") String from,
                     @RequestParam("to") String to,
                     @RequestParam("appid") String appid,
                     @RequestParam("salt") String salt,
                     @RequestParam("sign") String sign);

}
