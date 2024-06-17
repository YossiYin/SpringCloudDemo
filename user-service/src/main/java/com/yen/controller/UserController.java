package com.yen.controller;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.nacos.common.utils.StringUtils;
import com.yen.common.BaseResponse;
import com.yen.common.ErrorCode;
import com.yen.common.ResultUtils;
import com.yen.dto.other.TestResponse;
import com.yen.dto.user.UserLoginRequest;
import com.yen.dto.user.UserRegisterRequest;
import com.yen.entity.User;
import com.yen.exception.BusinessException;
import com.yen.service.BaiduFeignClient;
import com.yen.service.TestFeignClient;
import com.yen.service.UserService;
import com.yen.vo.LoginUserVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author Yhx
 * @date 2024/3/10 15:46
 */

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TestFeignClient testFeignClient;

    @Resource
    private BaiduFeignClient baiduFeignClient;

    /**
     * 你好
     *
     * @return {@link BaseResponse}<{@link String}>
     */
    @GetMapping("/hello")
    public BaseResponse<String> hello(){
        String q = "i like apples";
        String from = "en";
        String to = "zh";
        String appid = "20230910001811291";
        String salt = "1678350285";
        String key = "VFjHVYrt_Y5uI5nbI_ye";
        // appid+q+salt+密钥拼接成的字符串做MD5得到32位小写的sign
        String s = appid + q + salt + key;
        String sign = MD5.create().digestHex(s);
        System.out.println("s =" + s);
        System.out.println("sign = " + sign);

        // 测试feign调用第三方接口
        String resultGet = baiduFeignClient.translate(q, from, to, appid, salt,sign);
        System.out.println("resultGet = " + resultGet);

        return ResultUtils.success(resultGet);
    }

    @GetMapping("/hello2")
    public BaseResponse<Object> hello2(){

        // 测试feign调用第三方接口
        TestResponse testResponse = testFeignClient.test2("36");
        System.out.println("resultGet = " + testResponse);

        return ResultUtils.success(testResponse);
    }

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(user));
    }

}
