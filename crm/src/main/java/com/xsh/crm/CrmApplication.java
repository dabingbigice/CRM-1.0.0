package com.xsh.crm;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication()
@MapperScan({"com.xsh.crm.settings.dao","com.xsh.crm.workbench.dao"} )
@ServletComponentScan(basePackages = {"com.xsh.crm.settings.controller"})
public class CrmApplication  {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }


}
