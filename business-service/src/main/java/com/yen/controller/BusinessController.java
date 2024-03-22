package com.yen.controller;

import com.yen.common.BaseResponse;
import com.yen.common.ResultUtils;
import com.yen.entity.User;
import com.yen.service.BusinessService;
import com.yen.service.UserFeignClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 业务控制器
 *
 * @author Yhx
 * @date 2024/3/22 15:54
 */
@RestController
@RequestMapping("/")
@Slf4j
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @Resource
    private UserFeignClient userFeignClient;


    /**
     * 测试内部调用
     *
     * @param id 用户Id
     * @return {@link BaseResponse}<{@link String}>
     */
    @RequestMapping("/inner-test")
    public BaseResponse<String> innerTest(@RequestParam Long id) {
        User byId = userFeignClient.getById(id);
        System.out.println(byId);

        return ResultUtils.success("成功进行服务内部调用，获取用户信息根据id获取用户信息");
    }
}
