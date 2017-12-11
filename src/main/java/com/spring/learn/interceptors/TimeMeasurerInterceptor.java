package com.spring.learn.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TimeMeasurerInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(TimeMeasurerInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long time = System.currentTimeMillis();
		request.setAttribute("time", time);
		logger.info(request.getRequestURI()+" pre Handle time="+time);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long spendedTime = System.currentTimeMillis() - (Long) request.getAttribute("time");
		modelAndView.addObject("spendedTime", spendedTime);
		logger.info(request.getRequestURI()+" post handle - spendedTime:" + spendedTime+"ms");
	}
}
