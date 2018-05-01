package com.pos.lotto.configuration;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.pos.lotto.viewResolver.ExcelViewResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public JSONArray jsonArray() {
		JSONArray jsonArray = new JSONArray();
		return jsonArray;
	}

	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**", "/imgages/**", "/css/**", "/js/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/", "classpath:/staitic/images/", "classpath:/static/css/",
				"classpath:/static/js/");
	}
}
