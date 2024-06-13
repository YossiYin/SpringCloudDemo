package com.yen.service;

import com.yen.common.ErrorCode;
import com.yen.entity.User;
import com.yen.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

import static com.yen.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务模块Feign客户端
 * 对应本项目的user-service模块
 *
 * @author Yhx
 * @date 2024/3/10 16:23
 */
@FeignClient(name = "user-service", path = "/api/user/inner")
public interface UserFeignClient {

    /**
     * 根据 id 获取用户
     * @param userId
     * @return
     */
    @GetMapping("/get/id")
    User getById(@RequestParam("userId") long userId);

    /**
     * 根据 id 获取用户列表
     * @param idList
     * @return
     */
    @GetMapping("/get/ids")
    List<User> listByIds(@RequestParam("idList") Collection<Long> idList);

    /**
     * 获取当前登录用户
     * 统一使用此方法进行登录验证
     *
     * @param request 要求
     * @return {@link User }
     */
    default User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 可以考虑在这里做全局权限校验
        return currentUser;
    }
}
