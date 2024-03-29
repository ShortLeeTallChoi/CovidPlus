package com.covidplus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
public class ResourceConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
		registry.addResourceHandler("/assets/**").addResourceLocations("/WEB-INF/assets/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("/WEB-INF/scripts/");
		registry.addResourceHandler("/pug/**").addResourceLocations("/WEB-INF/pug/");
		registry.addResourceHandler("/scss/**").addResourceLocations("/WEB-INF/scss/");
		registry.addResourceHandler("/less/**").addResourceLocations("/WEB-INF/less/");
		registry.addResourceHandler("/sprites/**").addResourceLocations("/WEB-INF/sprites/");
		registry.addResourceHandler("/svgs/**").addResourceLocations("/WEB-INF/svgs/");
		registry.addResourceHandler("/webfonts/**").addResourceLocations("/WEB-INF/webfonts/");
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
		registry.order(2);
	}
	
	@Bean
	public BeanNameViewResolver vr() {
		BeanNameViewResolver bnvResolver= new BeanNameViewResolver();
		bnvResolver.setOrder(0);
		return bnvResolver;
	}
	
	@Bean(name = "jsonView")
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/").setViewName("forward:/dashboard");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}
