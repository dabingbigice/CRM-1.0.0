package com.xsh.crm.settings.controller;

import com.xsh.crm.exception.LoginException;
import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.settings.service.UserService;
import com.xsh.crm.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/index")
    public String index(){

        return "index";
    }
    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    /**
     * 登录验证
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public String userLogin(HttpServletRequest request, String username, String password){
        String ip = request.getRemoteAddr();

        try {
            Student login = userService.login(username, password,ip);

            request.getSession().setAttribute("student",login);
            request.getSession().setAttribute("name",login.getName());
            return "true";

            //如果未抛出任何异常表登录成功
        } catch (Exception exception) {
            String message = exception.getMessage();
            System.out.println(message);
            return message;
            //如果抛出任何异常表登录失败

        }

    }
    @RequestMapping("/workbench")
    public String userIndex(){
        return "/workbench/index";
    }
    @RequestMapping("/main/index")
    public String mainIndex(){
        return "/workbench/main/index";
    }

    /**
     * 验证原始密码是否正确
     * @return
     */
    //password
    @RequestMapping("/oldPwd")
    @ResponseBody
    public String password(String oldPwd){
        System.out.println(oldPwd+"oldPwd");

        //解析密码
        try {
            userService.password(oldPwd);
        } catch (LoginException e) {
            return e.getMessage();
        }
        return "true";
    }
    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String confirmPwd,String id){
        int i = userService.updatePassword(confirmPwd,id);
        if (i>0){
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 用户注销登录
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("student");

        return "true";
    }

    /**
     * 处理静态资源如果不处理会当作为模板引擎处理所以404
     * @return
     */
    @RequestMapping("/activity/index")
    public String activityIndex(){
        return "/workbench/activity/index";
    }



    @RequestMapping("/clue/index")
    public String clueIndex(){
        return "/workbench/clue/index";
    }



    @RequestMapping("/customer/index")
    public String customerIndex(){
        return "/workbench/customer/index";
    }


    @RequestMapping("/contacts/index")
    public String contactsIndex(){
        return "/workbench/contacts/index";
    }

    @RequestMapping("/transaction/index")
    public String  transactionIndex(){
        return "/workbench/transaction/index";
    }

    @RequestMapping("/visit/index")
    public String  visitIndex(){
        return "/workbench/visit/index";
    }

    @RequestMapping("/chart/activity/index")
    public String  chartActivityIndex(){
        return "/workbench/chart/activity/index";
    }

    @RequestMapping("/chart/clue/index")
    public String  chartClueIndex(){
        return "/workbench/chart/clue/index";
    }


    @RequestMapping("/chart/customerAndContacts/index")
    public String   chartCustomerAndContactsIndex(){
        return "/workbench/chart/customerAndContacts/index";
    }
    @RequestMapping("/chart/transaction/index")
    public String   chartTransactionIndex(){
        return "/workbench/chart/transaction/index";
    }
}
