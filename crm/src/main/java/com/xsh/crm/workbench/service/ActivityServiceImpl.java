package com.xsh.crm.workbench.service;

import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.utils.DateTimeUtil;
import com.xsh.crm.workbench.dao.ActivityMapper;
import com.xsh.crm.workbench.domain.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActivityServiceImpl implements ActivityService {
        @Autowired
    ActivityMapper activityMapper;

    @Override
    public List<Student> selectNames() {
        return activityMapper.selectNames();
    }

    @Override
    public int insertSelective(Activity activity) {
        int i = activityMapper.insertSelective(activity);

        return i;
    }

    @Override
    public List<Activity> selectActivity(Integer first,Integer last,String searchName,String searchOwner,
                                         String searchStartTime,String searchEndTime){

        List<Activity> activities = activityMapper.selectActivity(first,last,searchName,searchOwner,
                                                            searchStartTime,searchEndTime);

        return activities;
    }

    @Override
    public Integer selects() {
        Integer activities=  activityMapper.selects();
        return activities;
    }

    @Override
    public Integer selectActivityCount(String name,String owner,String startDate,String endDate) {
        return activityMapper.selectActivityCount(name,owner, startDate,endDate);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        int i = activityMapper.deleteByPrimaryKey(id);

        return i;
    }

    @Override
    public Activity selectByPrimaryKey(String id) {
        return activityMapper.selectByPrimaryKey(id);

    }

    @Override
    public int updateActivity(String id,String editeByName,String owner,String updateName, String updateStartTime,
                              String updateEndTime, String updateDescribe,
                              String updateCost) {
        Activity activity = new Activity();
        activity.setOwner(owner);
        activity.setName(updateName);
        activity.setStartdate(updateStartTime);
        activity.setEnddate(updateEndTime);
        activity.setDescription(updateDescribe);
        activity.setCost(updateCost);
        String sysTime = DateTimeUtil.getSysTime();
        activity.setEdittime(sysTime);
        activity.setEditby(editeByName);
        activity.setId(id);
        int i = activityMapper.updateByPrimaryKeySelective(activity);
        return i;
    }
}


