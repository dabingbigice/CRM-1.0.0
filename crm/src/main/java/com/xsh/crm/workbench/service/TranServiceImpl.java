package com.xsh.crm.workbench.service;

import com.xsh.crm.workbench.dao.TranMapper;
import com.xsh.crm.workbench.domain.Tran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranServiceImpl implements TranService {
    @Autowired
    private TranMapper tranMapper;
    @Override
    public Object clueConvertToTran(Tran tran) {
        tranMapper.insertSelective(tran);
        return null;
    }
}
