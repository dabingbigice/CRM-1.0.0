package com.xsh.crm.workbench.service;

import com.xsh.crm.utils.UUIDUtil;
import com.xsh.crm.workbench.dao.ContactsActivityRelationMapper;
import com.xsh.crm.workbench.dao.ContactsMapper;
import com.xsh.crm.workbench.domain.Activity;
import com.xsh.crm.workbench.domain.Contacts;
import com.xsh.crm.workbench.domain.ContactsActivityRelation;
import com.xsh.crm.workbench.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Override
    public Object addContact(Contacts contacts) {
        contactsMapper.insertSelective(contacts);
        return null;
    }

    @Override
    public int countContact() {

        return contactsMapper.count();
    }

    @Override
    public Contacts selectContactByPrimaryId(String id) {
        return        contactsMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<Activity> selectActivitiesByContactId(String id) {
        return         contactsMapper.selectActivitiesByContactId(id);

    }

    @Override
    public int delete(String activityId) {
        int i = contactsActivityRelationMapper.deleteByActivityId(activityId);
        return i;
    }

    @Override
    public int addActivitys(String[] activityIds, String contactid) {
        ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
        contactsActivityRelation.setContactsid(contactid);
        int i=0;
        for (String activityId : activityIds) {
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityid(activityId);
            if (contactsActivityRelationMapper.insertSelective(contactsActivityRelation)>0);
           i++;
        }
        return i;
    }

    @Override
    public List<Activity> getActivitys(String concatId) {

        return  contactsMapper.getActivitys(concatId);
    }

    @Override
    public List<Contacts> selectConcats(int pageNo,int pageSize) {
        return   contactsMapper.selectContacts(pageNo,pageSize);
    }
}
