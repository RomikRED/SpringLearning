package com.spring.learn.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.learn.domain.User;

public class CheckUserInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(CheckUserInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(request.getRequestURI().contains("check-user")) {
			User user = (User) modelAndView.getModel().get("user");
			if (user == null || !user.isAdmin()) {
				logger.info(" user not found "+request.getContextPath());
				response.sendRedirect(request.getContextPath()+"/failed");
			}
		}
	}
}
