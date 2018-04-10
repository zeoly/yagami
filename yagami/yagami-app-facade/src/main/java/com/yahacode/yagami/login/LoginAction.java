package com.yahacode.yagami.login;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.LogUtils;
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.common.TokenUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.consts.SessionKeyConsts;
import com.yahacode.yagami.login.vo.SessionVO;
import com.yahacode.yagami.pd.model.People;
import com.yahacode.yagami.pd.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * login controller
 *
 * @author zengyongli
 * @date 2018-02-09
 */
@RestController
@RequestMapping("/session")
public class LoginAction extends BaseAction {

    private static final int DEFAULT_lOCK_COUNT = Integer.parseInt(PropertiesUtils.getSysConfig("pwd.error.count"));

    PeopleService peopleService;

    /**
     * user login
     *
     * @param username
     *         client user name
     * @param password
     *         md5 string
     * @return session entity
     * @throws BizfwServiceException
     */
    @GetMapping
    public SessionVO login(String username, String password) throws BizfwServiceException {
        People peopleInfo = peopleService.getByCode(username);
        if (peopleInfo == null || People.STATUS_INVALID.equals(peopleInfo.getStatus())) {
            throw new BizfwServiceException(ErrorCode.Auth.Login.ACCOUNT_NOT_EXISTS);
        } else if (People.STATUS_LOCKED.equals(peopleInfo.getStatus())) {
            throw new BizfwServiceException(ErrorCode.Auth.Login.ACCOUNT_LOCKED);
        } else if (People.STATUS_UNCHECK.equals(peopleInfo.getStatus())) {
            throw new BizfwServiceException(ErrorCode.Auth.Login.ACCOUNT_INVALID);
        } else if (!password.equals(peopleInfo.getPassword())) {
            peopleInfo.setErrorCount(peopleInfo.getErrorCount() + 1);
            if (peopleInfo.getErrorCount() >= DEFAULT_lOCK_COUNT) {
                peopleInfo.setStatus(People.STATUS_LOCKED);
            }
            LogUtils.info("{}尝试登录失败，密码错误次数{}", peopleInfo.getCode(), peopleInfo.getErrorCount());
            peopleService.update(peopleInfo);
            int remainTrials = DEFAULT_lOCK_COUNT - peopleInfo.getErrorCount() + 1;
            throw new BizfwServiceException(ErrorCode.Auth.Login.PASSWORD_ERROR, remainTrials);
        } else if (password.equals(peopleInfo.getPassword())) {
            LogUtils.info("{}登录系统", peopleInfo.getCode());
            setLoginPeople(peopleInfo);
            peopleInfo.setErrorCount(0);
            peopleService.update(peopleInfo);

            String token = TokenUtils.generateToken();
            setSessionItem(SessionKeyConsts.AUTHORIZATION, token);
            LogUtils.info("{} login success, token {}", username, token);
            return new SessionVO(peopleInfo, token);
        }
        throw new BizfwServiceException(ErrorCode.DEFAULT_ERROR);
    }

    /**
     * user logout
     *
     * @param request
     *         http request
     * @param response
     *         http response
     * @return logout result
     * @throws IOException
     *         exception
     */
    @DeleteMapping
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        People people = getLoginPeople();
        LogUtils.info("{}登出系统", people.getCode());
        removeLoginInfo();
        request.getSession().invalidate();
        return "Y";
    }

    @Autowired
    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

}
