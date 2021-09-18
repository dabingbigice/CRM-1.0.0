package com.xsh.crm.settings.config;

import com.xsh.crm.settings.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] add={};
        String[] excluede={"/index","/userLogin","/login","/jquery/**","/image/**","/swagger-ui.html"};
        registry.addInterceptor(new MyInterceptor()).excludePathPatterns(excluede);
    }
}
