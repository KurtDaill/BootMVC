package com.tcs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import com.tcs.interceptor.GuestInterceptor;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc // it will enable the mvc env (@requestbaody, controller , response body, requestmapping, @valid
@ComponentScan("com.tcs.mvcdemo")
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new GuestInterceptor()).addPathPatterns("/");
		super.addInterceptors(registry);
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource mS = new ResourceBundleMessageSource();
		mS.addBasenames("validation");
		return mS;
	}

	@Bean 
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfig = new TilesConfigurer();
		tilesConfig.setDefinitions("/WEB-INF/layouts/tiles.xml");
		return tilesConfig;
	}

	@Bean
	public InternalResourceViewResolver	 resolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
