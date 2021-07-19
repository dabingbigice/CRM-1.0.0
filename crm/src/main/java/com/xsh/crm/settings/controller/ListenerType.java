package com.xsh.crm.settings.controller;

import com.xsh.crm.settings.domain.DicType;
import com.xsh.crm.settings.domain.DicValue;
import com.xsh.crm.settings.service.DicService;
import com.xsh.crm.settings.service.DicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@WebListener
public class ListenerType implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    @Autowired
    DicService dicService ;
    public ListenerType() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        //获取到全局对象存储字典值
        ServletContext servletContext = sce.getServletContext();
        //获取map对象,获取所有字典值
        if (dicService!=null){
            Map<String, List<DicValue>> all = dicService.getAll();
            Set<String> strings = all.keySet();
            for (String key:strings){
                //拿到所有的value
                List<DicValue> dicValues = all.get(key);
                //设置到全局域对象
                servletContext.setAttribute(key,dicValues);
            }
        }
        //分type存储

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
