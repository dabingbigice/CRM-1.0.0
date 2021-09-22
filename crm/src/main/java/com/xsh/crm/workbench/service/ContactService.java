package com.xsh.crm.workbench.service;

import com.xsh.crm.workbench.domain.Activity;
import com.xsh.crm.workbench.domain.Contacts;
import com.xsh.crm.workbench.domain.ContactsCustomerRelation;
import com.xsh.crm.workbench.domain.Customer;

import java.util.List;

public interface ContactService {
      List<Contacts> selectConcats(int pageNo,int pageSize);
     Object addContact(Contacts contacts);

      int countContact();

    Contacts selectContactByPrimaryId(String id);

    List<Activity> selectActivitiesByContactId(String id);

    List<Activity> getActivitys(String concatId);

    int addActivitys(String[] activityIds, String contactid);

    int delete(String activityId);
    int addContactsCustomerRelation(ContactsCustomerRelation customerRelation);

    int deleteContactCustomerRelation(String id);

    String selectCustomerId(String id);
}
