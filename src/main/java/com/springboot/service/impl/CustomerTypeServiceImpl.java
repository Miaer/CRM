package com.springboot.service.impl;


import com.springboot.dao.CustomerMapper;
import com.springboot.dao.CustomerTypeMapper;
import com.springboot.pojo.Customer;
import com.springboot.pojo.CustomerType;
import com.springboot.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    private CustomerTypeMapper customerTypeMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerType> findAll() {
        return customerTypeMapper.findAll();
    }

    @Override
    public Boolean deleteCustomerTypeByArr(Integer[] cuTypeArr) {
        /**
         * 在删除客户类型的时候，先去客户表中查看该类型是否被引用了，如果被引用了，不能直接删除。
         */
        List<Customer> customerByCustomerTypeArr = customerMapper.findCustomerByCustomerTypeArr(cuTypeArr);

        int i = 0;
        if (customerByCustomerTypeArr == null || customerByCustomerTypeArr.size() == 0){
            i = customerTypeMapper.deleteCustomerTypeByArr(cuTypeArr);
        }

        if (i > 0){
            return true;
        }

        return false;
    }

    /**
     * 添加客户类型实现
     * @param type
     * @return
     */
    @Override
    public Boolean addCustomerType(String type) {
        int i = customerTypeMapper.addCustomerType(type);
        return i > 0 ? true : false;
    }
}
