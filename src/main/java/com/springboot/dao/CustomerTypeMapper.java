package com.springboot.dao;

import com.springboot.pojo.CustomerType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerTypeMapper {


    @Select("select * from customer_type")
    List<CustomerType> findAll();
}
