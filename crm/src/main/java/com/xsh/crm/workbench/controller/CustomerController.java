package com.xsh.crm.workbench.controller;

import com.xsh.crm.workbench.domain.Customer;
import com.xsh.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request){
        String id = request.getParameter("id");
        Customer customer = customerService.selectByPrimaryKeyConvertOwner(id);
        request.getSession().setAttribute("customer",customer);
        System.out.println(request.getSession().getAttribute("customer"));
        return "workbench/customer/detail";
    }

    /**
     * 获取对象客户
     * @param pageNo
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping("/customers")
    @ResponseBody
    public List<Customer> customers(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "2")int pageSize, HttpSession session){
        List<Customer> customers = customerService.selectCustomers((pageNo-1)*pageSize,pageSize);
        return customers;
    }

    /**
     * 获取总长
     * @return
     */
    @RequestMapping("/size")
    @ResponseBody
    public int size(){

        return customerService.countCustoemr();
    }
}
