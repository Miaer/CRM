package com.springboot.service.impl;


import com.springboot.dao.CustomerTypeMapper;
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

    @Override
    public List<CustomerType> findAll() {
        return customerTypeMapper.findAll();
    }
}
