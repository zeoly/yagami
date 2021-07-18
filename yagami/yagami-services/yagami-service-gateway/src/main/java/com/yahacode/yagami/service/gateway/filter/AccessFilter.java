package com.yahacode.yagami.service.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author zengyongli 2019-07-09
 */
public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return ;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
