package com.worth.postalcodeservice.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.worth.postalcodeservice.model.ApiRequest;
import com.worth.postalcodeservice.service.ApiRequestsService;


public class HttpRequestInterceptor implements HandlerInterceptor {

	private ApiRequestsService apiRequestService;

	
	public HttpRequestInterceptor (ApiRequestsService apiRequestService) {
		this.apiRequestService=apiRequestService;
	};

	@Override
	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse response, Object handler)
			throws Exception {

		if ("/api/postal-codes/distance".equals(httpRequest.getRequestURI())) {

			ApiRequest apiRequest = new ApiRequest();
			apiRequest.setFromPostalCode(httpRequest.getParameter("from"));
			apiRequest.setToPostalCode(httpRequest.getParameter("to"));
			apiRequestService.save(apiRequest);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
