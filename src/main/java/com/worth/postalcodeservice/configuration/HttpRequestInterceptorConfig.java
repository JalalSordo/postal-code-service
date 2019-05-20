package com.worth.postalcodeservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.worth.postalcodeservice.service.ApiRequestsService;

@Configuration
public class HttpRequestInterceptorConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private ApiRequestsService apiRequestService;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new HttpRequestInterceptor(apiRequestService)).addPathPatterns("/**");
    }
    
    
}