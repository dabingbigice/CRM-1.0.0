package com.xsh.crm.workbench.controller;

import com.xsh.crm.settings.dao.StudentMapper;
import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.utils.DateTimeUtil;
import com.xsh.crm.utils.UUIDUtil;
import com.xsh.crm.workbench.dao.ActivityMapper;
import com.xsh.crm.workbench.domain.Activity;
import com.xsh.crm.workbench.service.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ActivityController {
    @Autowired
  ActivityServiceImpl activityServiceImpl;
    @Autowired
    StudentMapper studentMapper;

        @RequestMapping("/indexActivity")
        public String indexActivity(){
            return "workbench/activity/index";
        }
    @RequestMapping("/deleteMsg")
    @ResponseBody
    public String deleteMsg(String id){
         activityServiceImpl.deleteByPrimaryKey(id);
        return "true";
    }
    /**
     * 修改活动信息
     * @param id
     * @param updateName
     * @param updateStartTime
     * @param updateEndTime
     * @param updateDescribe
     * @param updateCost
     * @return
     */
    @RequestMapping("/updateActivity")
    @ResponseBody
    public String updateActivity(HttpServletRequest request,
                                 String id,String ownerId,String updateName,
                                 String updateStartTime,String updateEndTime,
                                 String updateDescribe,String updateCost){
        Student student = (Student) request.getSession().getAttribute("student");
        String name = student.getName();
        int i = activityServiceImpl.updateActivity(id,name,ownerId, updateName, updateStartTime, updateEndTime, updateDescribe, updateCost);
            if (i>0) {
                return "true";
            }else {
                return "false";
            }
    }


    /**
     * 回显数据给更新表单
     * @param id
     * @return
     */
    @RequestMapping("/echoData")
        @ResponseBody
        public Object echoData(String id){
            Activity activity = activityServiceImpl.selectByPrimaryKey(id);
            return activity;
        }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String[] id){
        int reslut=0;
        for (int i = 0; i < id.length; i++) {
            int temp = activityServiceImpl.deleteByPrimaryKey(id[i]);
            reslut+=temp;
        }
        String s = "删除"+String.valueOf(reslut) + "条用户信息成功!";
        return s;
    }

    /**
     * 查询出所有用户名
     * @return
     */
    @RequestMapping("/selectNames")
    @ResponseBody
    public Object selectNames(){
        List<Student> students = activityServiceImpl.selectNames();
        return students;
    }

    /**
     * 处理thymeleaf模板并回显数据
     * @return
     */
    @RequestMapping("/detail")
    public String detail(String id, HttpServletRequest request){
        Activity activity = activityServiceImpl.selectByPrimaryKey(id);
        Student student = studentMapper.selectByPrimaryKey(activity.getOwner());
        //在controller层处理owner
        activity.setOwner(student.getName());
        request.getSession().setAttribute("activityMessage",activity);

        return "workbench/activity/detail";
    }
    /**
     * 修改活动信息
     * @param request
     * @param name
     * @param startdate
     * @param enddate
     * @param cost
     * @param description
     * @return
     */
    @RequestMapping("/activitySave")
    @ResponseBody
    public String activitySave( HttpServletRequest request,String name,String owner ,String startdate, String enddate, String cost, String description){
        Activity activity = new Activity();

        Student student = (Student) request.getSession().getAttribute("student");
        activity.setId(UUIDUtil.getUUID());
        activity.setName(student.getName());
        activity.setOwner(owner);
        activity.setCost(cost);
        activity.setEdittime(DateTimeUtil.getSysTime());
        activity.setCreateby(student.getName());
        activity.setCreatetime(DateTimeUtil.getSysTime());
        activity.setDescription(description);
        activity.setEnddate(enddate);
        activity.setStartdate(startdate);
        activity.setName(name);
        int i = activityServiceImpl.insertSelective(activity);
        if (i>0) {
            return "true";
        }
        return "false";
    }

    /**
     * 查询所有活动
     * @return
     */
    @RequestMapping("/selectActivity")
    @ResponseBody
    public List<Activity> selectActivity(Integer pageNo,Integer pageSize,String searchName
                                        ,String searchOwner,String searchStartTime,String searchEndTime){
        List<Activity> activities =new ArrayList();
        HashMap<String, Object> activitiesMap = new HashMap<>();
        Integer first=(pageNo-1)*pageSize;
        //判断是否为首次加载页面
        if (searchName==""&&searchOwner==""&&searchStartTime==""&&searchEndTime==""){
            Integer total =   activityServiceImpl.selects();
            activities = activityServiceImpl.selectActivity(first, pageSize, searchName, searchOwner, searchStartTime, searchEndTime);
            //设置第一个对象的总页数为total
            activities.get(0).setTotal(total);
            activitiesMap.put("activity",activities);
            activitiesMap.put("total",total);

        }else {
            activities = activityServiceImpl.selectActivity(first, pageSize, searchName, searchOwner, searchStartTime, searchEndTime);
            //计算总条数
            Integer total = activityServiceImpl.selectActivityCount(searchName, searchOwner, searchStartTime, searchEndTime);
            if (activities.size()==0){
                total=0;
            }else {
                activities.get(0).setTotal(total);
            }
            activitiesMap.put("activity",activities);
            activitiesMap.put("total",total);
        }


        return activities;
    }

    /**
     * 查询所者的name
     * @param id
     * @return
     */
    @RequestMapping("/selectActivityNames")
    @ResponseBody
    public  String selectActivityName(String id){
        Student student = studentMapper.selectByPrimaryKey(id);
        return student.getName();
    }

}
