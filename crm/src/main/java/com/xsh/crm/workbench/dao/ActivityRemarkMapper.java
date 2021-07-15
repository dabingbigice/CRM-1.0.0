package com.xsh.crm.workbench.dao;

import com.xsh.crm.workbench.domain.ActivityRemark;

public interface ActivityRemarkMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActivityRemark record);

    int insertSelective(ActivityRemark record);

    ActivityRemark selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);
}