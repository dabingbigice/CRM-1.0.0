package com.xsh.crm.settings.dao;

import com.xsh.crm.settings.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    Student userLogin(@Param("username")String username,@Param("password") String password);

    int password(String password);
    @Update("update tbl_user  set loginPwd = #{confirmPwd} where id=#{id}")
    int updatePassword(@Param("confirmPwd") String confirmPwd,@Param("id")String id);

}