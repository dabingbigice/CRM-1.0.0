package com.xsh.crm.workbench.dao;

import com.xsh.crm.workbench.domain.ClueActivityRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ClueActivityRelationMapper {
    int deleteByPrimaryKey(String id);

    int insert(ClueActivityRelation record);

    int insertSelective(ClueActivityRelation record);

    ClueActivityRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ClueActivityRelation record);

    int updateByPrimaryKey(ClueActivityRelation record);
    @Select("select activityId from tbl_clue_activity_relation where clueId=#{clueId}")
    String[] selectActivityId(String clueId);
    @Delete("delete from tbl_clue_activity_relation where clueId=#{clueId} and activityId=#{activityId}")
    int remove(@Param("clueId") String clueId,@Param("activityId")String activityId );
    @Insert("insert into tbl_clue_activity_relation(clueId,activityId) values(#{id},#{acid})")
    int addActivityRelation(@Param("id") String id,@Param("acid") String acid);
}