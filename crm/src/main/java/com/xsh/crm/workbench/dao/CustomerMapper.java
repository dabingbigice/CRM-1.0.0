package com.xsh.crm.workbench.dao;

import com.xsh.crm.workbench.domain.Contacts;
import com.xsh.crm.workbench.domain.Customer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    @Select("SELECT c.id,c.name,c.website,c.phone,c.createBy,c.contactSummary,c.nextContactTime,c.description,c.address\n" +
            ",u.name AS \"owner\"\n" +
            " FROM `tbl_user` AS u RIGHT JOIN (SELECT * FROM tbl_customer LIMIT #{pageNo},#{pageSize}) AS c ON c.owner=u.id")
    List<Customer> selectCustomers(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
    @Select("select count(*) from tbl_customer")
    int countCustomer();
    @Select("SELECT c.id,c.name,c.website,c.phone,c.createBy,c.contactSummary,c.nextContactTime,c.description,c.address\n" +
            ",u.name AS \"owner\"\n" +
            " FROM `tbl_user` AS u RIGHT JOIN (SELECT * FROM tbl_customer where id=#{id}) AS c ON c.owner=u.id")

    Customer selectByPrimaryKeyConvertOwner(String id);
    @Select("    SELECT * FROM `tbl_contacts` WHERE id " +
            "IN(SELECT contactsId FROM `tbl_contacts_customer_relation` " +
            "WHERE customerId=#{id})")
    List<Contacts> getContact(String id);
}