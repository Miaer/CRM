package com.springboot.service;

import com.springboot.pojo.CustomerType;

import java.util.List;

/**
 * 客户类型接口
 */
public interface CustomerTypeService {

    List<CustomerType> findAll();

    Boolean deleteCustomerTypeByArr(Integer[] cuTypeArr);

    Boolean addCustomerType(String type);
}
