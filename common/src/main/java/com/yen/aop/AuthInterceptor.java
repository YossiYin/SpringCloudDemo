package com.yen.aop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yen.annotation.AuthCheck;
import com.yen.common.ErrorCode;
import com.yen.exception.BusinessException;
import com.yen.exception.ThrowUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

import static com.yen.common.ErrorCode.NO_AUTH_ERROR;
import static com.yen.constant.UserConstant.BAN_ROLE;
import static com.yen.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 权限拦截器，用于校验用户权限
 *
 * @author Yhx
 * @date 2024/6/13 17:41
 */
@Aspect
@Component
public class AuthInterceptor {

    /**
     * jackson的转换器
     */
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 1.从session中获取登录用户信息
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String mustRole = authCheck.mustRole();
        String userRole = getLoginUserRole(request);
        // 无角色信息-无权限
        ThrowUtils.throwIf(StrUtil.isBlank(userRole),NO_AUTH_ERROR);

        // 2.判断角色
        if (BAN_ROLE.equals(userRole)){
            // 被Ban用户-无权限
            throw new BusinessException(NO_AUTH_ERROR);
        }

        if (!mustRole.equals(userRole)){
            // 非对应权限
            throw new BusinessException(NO_AUTH_ERROR);
        }

        // 3.放行
        return joinPoint.proceed();
    }

    /**
     * 获取登录用户
     *
     * @param request 要求
     * @return {@link String }
     */
    private String getLoginUserRole(HttpServletRequest request) throws JsonProcessingException {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        String json = objectMapper.writeValueAsString(userObj);
        Map<String, Object> currentUserMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        if (currentUserMap == null || currentUserMap.get("id") == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        return currentUserMap.get("userRole").toString();
    }
}
