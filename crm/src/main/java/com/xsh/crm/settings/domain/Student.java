package com.xsh.crm.settings.domain;

public class Student {
    private String id;

    private String loginact;

    private String name;

    private String loginpwd;

    private String email;

    private String expiretime;

    private String lockstate;

    private String deptno;

    private String allowips;

    private String createtime;

    private String createby;

    private String edittime;

    private String editby;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginact() {
        return loginact;
    }

    public void setLoginact(String loginact) {
        this.loginact = loginact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(String expiretime) {
        this.expiretime = expiretime;
    }

    public String getLockstate() {
        return lockstate;
    }

    public void setLockstate(String lockstate) {
        this.lockstate = lockstate;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getAllowips() {
        return allowips;
    }

    public void setAllowips(String allowips) {
        this.allowips = allowips;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", loginact='" + loginact + '\'' +
                ", name='" + name + '\'' +
                ", loginpwd='" + loginpwd + '\'' +
                ", email='" + email + '\'' +
                ", expiretime='" + expiretime + '\'' +
                ", lockstate='" + lockstate + '\'' +
                ", deptno='" + deptno + '\'' +
                ", allowips='" + allowips + '\'' +
                ", createtime='" + createtime + '\'' +
                ", createby='" + createby + '\'' +
                ", edittime='" + edittime + '\'' +
                ", editby='" + editby + '\'' +
                '}';
    }

    public String getEdittime() {
        return edittime;
    }

    public void setEdittime(String edittime) {
        this.edittime = edittime;
    }

    public String getEditby() {
        return editby;
    }

    public void setEditby(String editby) {
        this.editby = editby;
    }
}