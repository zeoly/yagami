package com.yahacode.yagami.base.mvc;

import com.yahacode.yagami.base.common.ServletContextHolder;
import com.yahacode.yagami.base.consts.SessionKeyConsts;
import com.yahacode.yagami.core.model.Person;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public Person getLoginPerson() {
        return (Person) ServletContextHolder.getSession().getAttribute(SessionKeyConsts.LOGIN_PEOPLE);
    }
}
