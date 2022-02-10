package com.yahacode.yagami.base;

import com.yahacode.yagami.base.common.ServletContextHolder;
import com.yahacode.yagami.base.consts.SessionKeyConsts;
import com.yahacode.yagami.base.mvc.SessionService;
import com.yahacode.yagami.core.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * framework base controller, recommend extending this class to build your business controller.
 * provide session control functions.
 *
 * @author zengyongli
 */
public class BaseController {

    @Autowired
    private SessionService sessionService;

    protected Person getLoginPerson() {
        return sessionService.getLoginPerson();
    }

    public void setLoginPeople(Person peopleInfo) {
        getSession().setAttribute(SessionKeyConsts.LOGIN_PEOPLE, peopleInfo);
    }

    public HttpSession getSession() {
        return ServletContextHolder.getSession();
    }

    public void setSessionItem(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public void removeLoginInfo() {
        getSession().removeAttribute(SessionKeyConsts.LOGIN_PEOPLE);
    }

}
