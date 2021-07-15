package com.xsh.crm.workbench.dao;

import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.workbench.domain.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    Integer selectActivityCount(@Param("name") String name,@Param("owner") String owner,
                                @Param("startDate") String startDate,@Param("endDate") String endDate);

}