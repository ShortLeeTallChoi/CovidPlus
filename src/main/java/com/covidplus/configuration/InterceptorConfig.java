package com.covidplus.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.covidplus.interceptor.MenuWriteInterceptor;

@Configuration
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		List<String> EXCLUDE_PATTERNS = Arrays.asList("/js/**", "/css/**", "/assets/**", "/scripts/**", "/pug/**",
				"/scss/**", "/less/**", "/sprites/**", "/svgs/**", "/webfonts/**");
		registry.addInterceptor(new MenuWriteInterceptor()).excludePathPatterns(EXCLUDE_PATTERNS);
	}
}
