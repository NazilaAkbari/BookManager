package com.nazi.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/allLendBooks").setViewName("lendIndex");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/friend").setViewName("friendIndex");
        registry.addViewController("/login").setViewName("login");
        
    }

}