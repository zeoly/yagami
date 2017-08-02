package com.yahacode.yagami.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.consts.ErrorCode;

@Order(1)
@ControllerAdvice
public class YagamiExceptionHandler {

	Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public YagamiResponse jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
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
		return response;
	}

}
