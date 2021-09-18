package com.xsh.crm.workbench.controller;

import com.xsh.crm.workbench.domain.Activity;
import com.xsh.crm.workbench.domain.Contacts;
import com.xsh.crm.workbench.domain.Customer;
import com.xsh.crm.workbench.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(description = "联系人模块")
@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/delete")
    public String delete(String activityId,String contactId){
        int delete = contactService.delete(activityId);
        return "forward:/contact/detail?id="+contactId;

    }
    @ApiOperation("成批关联市场活动")
    @PostMapping("/addActivitys")
    @ResponseBody
    public String addActivitys(@ApiParam("市场活动的ids") String [] activityIds,@ApiParam("联系人id")String contactid){
        int i = contactService.addActivitys(activityIds, contactid);
        if (i>0)
        return  "true";
        else
            return "false";
    }
    @ApiOperation("获取未关联的市场活动")
    @GetMapping("/getActivitys")
    @ResponseBody
    public String getActivitys(@ApiParam(value = "联系人id" ,required = true) String concatId,HttpSession session){
        List<Activity> activities=contactService.getActivitys(concatId);
        session.setAttribute("getActivitys",activities);
        return "true";
    }
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request){
        String id = request.getParameter("id");
        Contacts contacts = contactService.selectContactByPrimaryId(id);
        request.getSession().setAttribute("contactsBean",contacts);
        List<Activity> activities = contactService.selectActivitiesByContactId(id);
        request.getSession().setAttribute("contactsActivities",activities);
        getActivitys(contacts.getId(),request.getSession());
        return "workbench/contacts/detail";
    }
    @RequestMapping("/contacts")
    @ResponseBody
    public List<Contacts> contacts(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "2")int pageSize, HttpSession session){
        List<Contacts> contacts = contactService.selectConcats((pageNo-1)*pageSize,pageSize);
        return contacts;
    }

    /**
     * 获取总长
     * @return
     */
    @RequestMapping("/size")
    @ResponseBody
    public int size(){
        return contactService.countContact();
    }
}
