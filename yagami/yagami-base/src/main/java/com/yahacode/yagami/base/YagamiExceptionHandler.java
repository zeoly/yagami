package com.yahacode.yagami.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.consts.ErrorCode;

/**
 * convert framework business exception to YagamiResponse
 *
 * @author zengyongli
 */
@ControllerAdvice
public class YagamiExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<YagamiResponse> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        YagamiResponse response = new YagamiResponse();
        if (e instanceof BizfwServiceException) {
            BizfwServiceException serviceException = (BizfwServiceException) e;
            response.setCode(serviceException.getErrorCode());
            response.setMsg(serviceException.getErrorMsg());
        } else {
            logger.error("none biz error", e);
            response.setCode(ErrorCode.DEFAULT_ERROR);
            response.setMsg(PropertiesUtils.getErrorMsg(ErrorCode.DEFAULT_ERROR));
        }
        return new ResponseEntity<YagamiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
