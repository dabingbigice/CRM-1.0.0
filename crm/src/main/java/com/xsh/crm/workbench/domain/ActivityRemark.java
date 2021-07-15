package com.xsh.crm.workbench.domain;

public class ActivityRemark {
    private String id;

    private String notecontent;

    private String createtime;

    private String createby;

    private String edittime;

    private String editby;

    private String editflag;

    private String activityid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotecontent() {
        return notecontent;
    }

    public void setNotecontent(String notecontent) {
        this.notecontent = notecontent;
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

    public String getEditflag() {
        return editflag;
    }

    public void setEditflag(String editflag) {
        this.editflag = editflag;
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    @Override
    public String toString() {
        return "ActivityRemark{" +
                "id='" + id + '\'' +
                ", notecontent='" + notecontent + '\'' +
                ", createtime='" + createtime + '\'' +
                ", createby='" + createby + '\'' +
                ", edittime='" + edittime + '\'' +
                ", editby='" + editby + '\'' +
                ", editflag='" + editflag + '\'' +
                ", activityid='" + activityid + '\'' +
                '}';
    }
}