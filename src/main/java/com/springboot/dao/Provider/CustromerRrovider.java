package com.springboot.dao.Provider;

import com.springboot.pojo.Customer;
import org.apache.ibatis.jdbc.SQL;

public class CustromerRrovider {
    public String insertEmployeeSql(Customer customer) {
        return new SQL() {
            {
                INSERT_INTO("customer");
                if(customer.getCompanyName()!=null) {
                    VALUES("company_name","#{companyName}");
                }
                if(customer.getCompanyAlias()!=null) {
                    VALUES("company_alias", "#{companyAlias}");
                }
                if(customer.getArea()!=null) {
                    VALUES("area", "#{area}");
                }
                if(customer.getAddress()!=null) {
                    VALUES("address", "#{address}");
                }
                if(customer.getMajorBusiness()!=null) {
                    VALUES("major_business", "#{majorBusiness}");
                }
                if(customer.getPrejudice()!=null) {
                    VALUES("prejudice", "#{prejudice}");
                }
                if(customer.getPersonName()!=null) {
                    VALUES("person_name", "#{personName}");
                }
                if(customer.getPersonCompany()!=null) {
                    VALUES("person_company", "#{personCompany}");
                }
                if(customer.getPersonPositoin()!=null) {
                    VALUES("person_positoin", "#{personPositoin}");
                }
                if(customer.getPersonPhone()!=null) {
                    VALUES("person_phone", "#{personPhone}");
                }
                if(customer.getPersonPhone2()!=null) {
                    VALUES("person_phone2", "#{personPhone2}");
                }
                if(customer.getIdentification()!=null) {
                    VALUES("identification", "#{identification}");
                }
                if(customer.getHomeAddress()!=null) {
                    VALUES("home_address", "#{homeAddress}");
                }
                if(customer.getAssertVolumn()!=null) {
                    VALUES("assert_volumn", "#{assertVolumn}");
                }
                if(customer.getMemo()!=null) {
                    VALUES("memo", "#{memo}");
                }
                if(customer.getCustomerType()!=null) {
                    VALUES("customer_type", "#{customerType}");
                }
                if (customer.getUserId() != null){
                    VALUES("user_id","#{userId}");
                }
                if (customer.getCreateTime() != null){
                    VALUES("create_time","#{createTime}");
                }
            }
        }.toString();
}
}
