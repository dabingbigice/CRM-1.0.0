package com.xsh.crm.workbench.dao;

import com.xsh.crm.workbench.domain.Clue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClueMapper {
    int deleteByPrimaryKey(String id);

    int insert(Clue record);

    int insertSelective(Clue record);

    Clue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);
    @Select("select count(*) from tbl_clue")
    int getAllClueCount();
    @Select("select * from tbl_clue limit #{offset},#{rows}")
    List<Clue> getClueToPage(@Param("offset") int n,@Param("rows") int s);
}