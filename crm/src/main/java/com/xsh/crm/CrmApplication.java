package com.xsh.crm;

import jdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan({"com.xsh.crm.settings.dao","com.xsh.crm.workbench.dao"} )
public class CrmApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CrmApplication.class);
    }
}
