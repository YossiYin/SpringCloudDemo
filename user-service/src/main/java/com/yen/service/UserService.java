package com.yen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yen.entity.User;
import com.yen.vo.LoginUserVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Yhx
 * @date 2024/3/10 16:02
 */
public interface UserService extends IService<User> {
    /**
     * 用户寄存器
     *
     * @param userAccount   用户帐户
     * @param userPassword  用户密码
     * @param checkPassword 检查密码
     * @return long
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户帐户
     * @param userPassword 用户密码
     * @param request      要求
     * @return {@link LoginUserVO}
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获取当前登录用户
     *
     * @param request 要求
     * @return {@link User}
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request 要求
     * @return boolean
     */
    boolean userLogout(HttpServletRequest request);
}
