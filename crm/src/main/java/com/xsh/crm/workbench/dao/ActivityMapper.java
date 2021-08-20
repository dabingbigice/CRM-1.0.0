package com.xsh.crm.workbench.dao;

import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.workbench.domain.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    @Select("select * from tbl_user")
    List<Student> selectNames();

    List<Activity> selectActivity(@Param("first") Integer first,@Param("last") Integer last,
                                 @Param("name") String name,@Param("owner") String owner,
                                  @Param("startDate") String startDate,@Param("endDate") String endDate );
    @Select("select count(*) from tbl_activity")
    Integer selects();

    @Select("SELECT res.OWNER,res.id,res.startDate,res.endDate,u.name as ownerName,res.name  FROM (\n" +
            "SELECT OWNER,id,startDate,endDate,NAME FROM tbl_activity WHERE \n" +
            "id NOT IN(SELECT activityId FROM tbl_clue_activity_relation WHERE clueId=#{clueId})\n" +
            ") AS res LEFT JOIN `tbl_user` AS u ON res.owner=u.`id`")
    ArrayList<Activity> selectActivities(String clueId);
    Integer selectActivityCount(@Param("name") String name,@Param("owner") String owner,
                                @Param("startDate") String startDate,@Param("endDate") String endDate);
    @Select("SELECT u.name AS ownername ,act.id,act.name,act.startDate,endDate FROM `tbl_user`AS u RIGHT JOIN (SELECT *FROM `tbl_activity` )  AS act ON act.owner=u.`id`")
    ArrayList<Activity> clueConvert();
}