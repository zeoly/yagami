package com.yahacode.yagami.base;

import com.yahacode.yagami.base.common.ServletContextHolder;
import com.yahacode.yagami.base.consts.SessionKeyConsts;
import com.yahacode.yagami.pd.model.People;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

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

    protected People getLoginPeople() {
        return (People) getSession().getAttribute(SessionKeyConsts.LOGIN_PEOPLE);
    }

    public void setLoginPeople(People peopleInfo) {
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                true));
    }

}
