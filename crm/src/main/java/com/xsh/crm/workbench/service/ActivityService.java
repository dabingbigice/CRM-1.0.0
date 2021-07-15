package com.xsh.crm.workbench.service;

import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.workbench.domain.Activity;

import java.util.List;

public interface ActivityService {
    List<Student> selectNames();

    int insertSelective(Activity activity);

    List<Activity> selectActivity(Integer first,Integer last,String searchName,String searchOwner,
                                  String searchStartTime,String searchEndTime);

    Integer selects();
    Integer selectActivityCount(String name,String owner, String startDate,String endDate);

    int deleteByPrimaryKey(String s);

    Activity selectByPrimaryKey(String id);

    int updateActivity(String id,String editeByName,String owner,String updateName, String updateStartTime, String updateEndTime, String updateDescribe, String updateCost);
}
