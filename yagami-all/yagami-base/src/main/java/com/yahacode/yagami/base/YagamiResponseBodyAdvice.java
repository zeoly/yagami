package com.yahacode.yagami.base;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@SuppressWarnings("rawtypes")
@Order(2)
@ControllerAdvice
public class YagamiResponseBodyAdvice implements ResponseBodyAdvice {

	private static final String SUCCESS_CODE = "999999";

	private static final String SUCCESS_MSG = "成功";

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if (body instanceof YagamiResponse) {
			return body;
		}
		YagamiResponse yagamiResponse = new YagamiResponse();
		yagamiResponse.setCode(SUCCESS_CODE);
		yagamiResponse.setMsg(SUCCESS_MSG);
		yagamiResponse.setData(body);
		return yagamiResponse;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

}
