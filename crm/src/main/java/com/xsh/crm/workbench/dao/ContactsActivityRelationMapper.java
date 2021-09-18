package com.xsh.crm.workbench.dao;

import com.xsh.crm.workbench.domain.ContactsActivityRelation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsActivityRelationMapper {
    int deleteByPrimaryKey(String id);

    int insert(ContactsActivityRelation record);

    int insertSelective(ContactsActivityRelation record);

    ContactsActivityRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ContactsActivityRelation record);

    int updateByPrimaryKey(ContactsActivityRelation record);
    @Delete("delete from tbl_contacts_activity_relation where activityId =#{activityId}")
    int deleteByActivityId(String activityId);
}