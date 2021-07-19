package com.xsh.crm.settings.service;

import com.xsh.crm.exception.LoginException;
import com.xsh.crm.settings.dao.DicTypeMapper;
import com.xsh.crm.settings.dao.StudentMapper;
import com.xsh.crm.settings.domain.DicType;
import com.xsh.crm.settings.domain.DicValue;
import com.xsh.crm.settings.domain.Student;
import com.xsh.crm.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student login(String username, String password,String ip) throws LoginException {
        Student reslut = studentMapper.userLogin(username, password);

        if (reslut==null){
        throw new LoginException("账号密码错误");
        }
        //如果成功执行则密码正确需要继续向下验证信息
        String expireTime=reslut.getExpiretime();
        String sysTime = DateTimeUtil.getSysTime();
        if(expireTime.compareTo(sysTime)<0){
            throw new LoginException("账号已失效");
        }

        String lockstate = reslut.getLockstate();
        if ("0".equals(lockstate)) {
                throw new LoginException("账号已锁定");
        }
        String url =reslut.getAllowips();
        if (url==""||url==null){
            throw new LoginException("ip地址受限制");
        }

        return reslut;
    }

    /**
     * 修改密码时验证密码是否正确，正确返回true
     * @param password
     * @return
     */
    @Override
    public boolean password(String password) throws LoginException {
        int reslut = studentMapper.password(password);
        if (reslut==0){
            throw new LoginException("原始密码输入错误");
        }
        return true;
    }

    @Override
    public int updatePassword(String confirmPwd,String id) {

        int i = studentMapper.updatePassword(confirmPwd, id);
        System.out.println("影响行数"+i);
        return i;
    }
}
