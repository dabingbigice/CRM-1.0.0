package com.xsh.crm.workbench.service;

import com.xsh.crm.workbench.domain.Customer;

import java.util.List;

public interface CustomerService {
    Object addCustomer(Customer customer);

    List<Customer> selectCustomers(int pageNo,int pageSize);
    int countCustoemr();

    Customer selectByPrimaryKeyConvertOwner(String id);
}
