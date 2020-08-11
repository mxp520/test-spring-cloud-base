package com.objcat.servicea.intercept.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private InterceptConfig interceptConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptConfig)
                .addPathPatterns("/testIntercepter");
    }

}
