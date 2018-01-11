package com.yahacode.yagami.base;

import com.yahacode.yagami.pd.model.People;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * framework base Action, recommend to extends this class to build your business Action.
 * provide session control functions.
 *
 * @author zengyongli
 */
public class BaseAction {

    public static final String PEOPLE_KEY = "peopleInfo";

    protected HttpSession getSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest httpServletRequest = requestAttributes.getRequest();
        return httpServletRequest.getSession();
    }

    protected People getLoginPeople() {
        return (People) getSession().getAttribute(PEOPLE_KEY);
    }

    public void setLoginPeople(People peopleInfo) {
        getSession().setAttribute(PEOPLE_KEY, peopleInfo);
    }

    public void removeLoginInfo(HttpServletRequest request) {
        request.getSession().removeAttribute(PEOPLE_KEY);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                true));
    }

}
