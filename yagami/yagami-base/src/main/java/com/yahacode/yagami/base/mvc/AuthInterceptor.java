package com.yahacode.yagami.base.mvc;

import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zengyongli 2018-03-01
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final String AUTH = "authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        String authorization = request.getParameter(AUTH);
        if (StringUtils.isEmpty(authorization)) {
            throw new BizfwServiceException(ErrorCode.NEED_LOGIN);
        }
        return true;
    }
}
