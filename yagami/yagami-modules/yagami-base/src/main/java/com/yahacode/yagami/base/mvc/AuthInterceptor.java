package com.yahacode.yagami.base.mvc;

import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.common.LogUtils;
import com.yahacode.yagami.base.common.ServletContextHolder;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.consts.SessionKeyConsts;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zengyongli 2018-03-01
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final String[] excludedUrls = "/session,/boom".split(",");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        String authorization = request.getHeader(SessionKeyConsts.AUTHORIZATION);
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        LogUtils.info("url [{}], method [{}], session id [{}]", request.getRequestURI(), request.getMethod(), request
                .getSession().getId());
        if (!isExcluded(request.getRequestURI())) {
            LogUtils.info("url [{}], header token [{}]", request.getRequestURI(), authorization);
            String sessionToken = (String) ServletContextHolder.getSession().getAttribute(SessionKeyConsts
                    .AUTHORIZATION);
            LogUtils.info("url [{}], session token [{}]", request.getRequestURI(), sessionToken);
            if (StringUtils.isEmpty(authorization) || !authorization.equals(sessionToken)) {
                throw new ServiceException(ErrorCode.NEED_LOGIN);
            }
        }
        return true;
    }

    private boolean isExcluded(String url) {
        for (String str : excludedUrls) {
            Pattern pattern = Pattern.compile(str);
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }
}
