package com.xsh.crm.workbench.dao;

import com.xsh.crm.workbench.domain.Activity;
import com.xsh.crm.workbench.domain.Contacts;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ContactsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    Contacts selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKey(Contacts record);
    @Select("select count(*) from tbl_contacts")
    int count();
    @Select("SELECT u.`name` AS 'owner', d.id,d.`fullname`,d.`source`,d.`birth`,d.customerId\n" +
            " AS customerId  FROM `tbl_user` u RIGHT JOIN (SELECT c.owner,c.id,c.`fullname`,c.`source`,c.`birth`,r.name AS customerId \n" +
            "FROM`tbl_contacts`  AS c LEFT JOIN `tbl_customer` \n" +
            "AS r ON c.`customerId` =r.`id`) AS d ON d.owner=u.`id`\n" +
            "\n limit #{pageNo},#{pageSize}")
    List<Contacts> selectContacts(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);

    @Select("SELECT u.name as owner,t.id,t.name,startDate,endDate FROM `tbl_user` AS u RIGHT JOIN (SELECT a.id,a.name,startDate,endDate,OWNER FROM `tbl_activity` AS a RIGHT JOIN \n" +
            "(SELECT * FROM `tbl_contacts_activity_relation` WHERE `contactsId` =#{id})  AS c\n" +
            "ON c.`activityId`=a.`id`\n" +
            ") AS t ON t.owner = u.id")
    List<Activity> selectActivitiesByContactId(String id);
}