package com.yahacode.yagami.base;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.consts.ErrorCode;

@ControllerAdvice
public class YagamiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	private static final String SUCCESS_CODE = "999999";

	private static final String SUCCESS_MSG = "成功";

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		try {
			boolean isYagamiHandler = BaseAction.class.isAssignableFrom(returnType.getMethod().getDeclaringClass());
			boolean isYagamiResponse = body instanceof YagamiResponse;
			if (isYagamiHandler && !isYagamiResponse) {
				if (body instanceof String) {
					YagamiResponse yagamiResponse = new YagamiResponse();
					yagamiResponse.setCode(SUCCESS_CODE);
					yagamiResponse.setMsg(SUCCESS_MSG);
					yagamiResponse.setData(body);
					ObjectMapper mapper = new ObjectMapper();
					return mapper.writeValueAsString(yagamiResponse);
				}
				YagamiResponse yagamiResponse = new YagamiResponse();
				yagamiResponse.setCode(SUCCESS_CODE);
				yagamiResponse.setMsg(SUCCESS_MSG);
				yagamiResponse.setData(body);
				return yagamiResponse;
			}
		} catch (JsonProcessingException e) {
			YagamiResponse yagamiResponse = new YagamiResponse();
			yagamiResponse.setCode(ErrorCode.DEFAULT_ERROR);
			yagamiResponse.setMsg(PropertiesUtils.getErrorMsg(ErrorCode.DEFAULT_ERROR));
			return yagamiResponse;
		}
		return body;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

}
