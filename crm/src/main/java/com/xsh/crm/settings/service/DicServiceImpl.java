package com.xsh.crm.settings.service;

import com.xsh.crm.settings.dao.DicTypeMapper;
import com.xsh.crm.settings.dao.DicValueMapper;
import com.xsh.crm.settings.dao.StudentMapper;
import com.xsh.crm.settings.domain.DicType;
import com.xsh.crm.settings.domain.DicValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DicServiceImpl implements DicService {
    @Autowired
    DicTypeMapper dicTypeMapper;
    @Autowired
    DicValueMapper dicValueMapper;


    @Override
    public Map<String,List<DicValue>> getAll() {
        HashMap<String, List<DicValue>> objectObjectHashMap = new HashMap<>();

        List<DicType> typeList = dicTypeMapper.getTypeList();
        for (DicType dic:typeList) {
                //取得每一种的编码类型
            String code = dic.getCode();
            //条件查询出类型编码的值
            List<DicValue> everTypeCodeValues = dicValueMapper.getEverTypeCodeValues(code);//根据dictype表中的结果查询dicvalue中的值
            //where typeCode=#{code}
            //将每种code分类 放入typeValue表中的值存入map
            objectObjectHashMap.put(code+"List",everTypeCodeValues);
        }
        return objectObjectHashMap;
    }
}
