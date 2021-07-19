package com.xsh.crm.settings.dao;

import com.xsh.crm.settings.domain.DicType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
public interface DicTypeMapper {
    int deleteByPrimaryKey(String code);

    int insert(DicType record);

    int insertSelective(DicType record);

    DicType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(DicType record);

    int updateByPrimaryKey(DicType record);

    List<DicType> getTypeList();
}