package com.xsh.crm.workbench.service;

import com.xsh.crm.workbench.dao.CustomerMapper;
import com.xsh.crm.workbench.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public Object addCustomer(Customer customer) {
        int i = customerMapper.insertSelective(customer);
        return i>0?true:false;
    }

    @Override
    public List<Customer> selectCustomers(int pageNo,int Pagesize) {
        List<Customer> customers = customerMapper.selectCustomers(pageNo, Pagesize);
        return customers;
    }

    @Override
    public int countCustoemr() {
        return customerMapper.countCustomer();
    }

    @Override
    public Customer selectByPrimaryKeyConvertOwner(String id) {
        return      customerMapper.selectByPrimaryKeyConvertOwner(id);
    }
}
