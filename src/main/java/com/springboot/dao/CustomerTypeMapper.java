package com.springboot.dao;

import com.springboot.pojo.CustomerType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerTypeMapper {


    @Select("select * from customer_type")
    List<CustomerType> findAll();

    @Delete("<script> delete from customer_type where id in " +
            "<foreach collection='array' open='(' item='item' separator=',' close=')'>" +
            " #{item}" +
            "</foreach> " +
            "</script>")
    int deleteCustomerTypeByArr(Integer[] cuTypeArr);

    @Insert("insert into customer_type(name) values(#{type})")
    int addCustomerType(String type);
}
