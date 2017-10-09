package com.yahacode.yagami.base.filter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.YagamiResponse;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.pd.model.People;

public class LoginFilter implements Filter {

	List<String> excludedUrls;

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		if (!isExcluded(url)) {
			HttpSession session = request.getSession();
			People mockPeople = new People("test");
			mockPeople.setCode("test");
			mockPeople.setIdBfPeople("8a808088579f5d5501579f5d99790000");
			mockPeople.setDepartmentId("8a808087583fa7b701583faadf300000");
			session.setAttribute(BaseAction.PEOPLE_KEY, mockPeople);
//			People peopleInfo = (People) session.getAttribute(BaseAction.PEOPLE_KEY);
//			if (peopleInfo == null) {
//				response.setCharacterEncoding("UTF-8");
//				response.setContentType("application/json; charset=utf-8");
//				YagamiResponse yagamiResponse = new YagamiResponse(ErrorCode.NEED_LOGIN);
//				ObjectMapper mapper = new ObjectMapper();
//				response.getWriter().write(mapper.writeValueAsString(yagamiResponse));
//				return;
//			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private boolean isExcluded(String url) {
		for (String str : excludedUrls) {
			Pattern pattern = Pattern.compile(str);
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				return true;
			}
		}
		return false;
	}

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}
}
