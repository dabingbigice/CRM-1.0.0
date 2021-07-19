package com.xsh.crm.workbench.domain;

public class Clue {
    private String id;

    private String fullname;

    private String appellation;

    private String owner;

    private String company;

    private String job;

    @Override
    public String toString() {
        return "Clue{" +
                "id='" + id + '\'' +
                ", fullname='" + fullname + '\'' +
                ", appellation='" + appellation + '\'' +
                ", owner='" + owner + '\'' +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", mphone='" + mphone + '\'' +
                ", state='" + state + '\'' +
                ", source='" + source + '\'' +
                ", createby='" + createby + '\'' +
                ", createtime='" + createtime + '\'' +
                ", editby='" + editby + '\'' +
                ", edittime='" + edittime + '\'' +
                ", description='" + description + '\'' +
                ", contactsummary='" + contactsummary + '\'' +
                ", nextcontacttime='" + nextcontacttime + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    private String email;

    private String phone;

    private String website;

    private String mphone;

    private String state;

    private String source;

    private String createby;

    private String createtime;

    private String editby;

    private String edittime;

    private String description;

    private String contactsummary;

    private String nextcontacttime;

    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getEditby() {
        return editby;
    }

    public void setEditby(String editby) {
        this.editby = editby;
    }

    public String getEdittime() {
        return edittime;
    }

    public void setEdittime(String edittime) {
        this.edittime = edittime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactsummary() {
        return contactsummary;
    }

    public void setContactsummary(String contactsummary) {
        this.contactsummary = contactsummary;
    }

    public String getNextcontacttime() {
        return nextcontacttime;
    }

    public void setNextcontacttime(String nextcontacttime) {
        this.nextcontacttime = nextcontacttime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}