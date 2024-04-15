package com.aykacltd.sec.demo.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcViewConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/user").setViewName("user");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/success").setViewName("success");
		registry.addViewController("/error").setViewName("error");
		registry.addViewController("/exeception").setViewName("exception");
	}

}
