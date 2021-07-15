package com.xsh.crm.settings.service;

import com.xsh.crm.exception.LoginException;
import com.xsh.crm.settings.domain.Student;

public interface UserService {
    Student login(String username, String password,String ip) throws LoginException;

    boolean password(String password) throws LoginException;

    int updatePassword(String confirmPwd,String id);
}
