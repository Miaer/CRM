package com.springboot.controller;

import com.springboot.pojo.CustomerType;
import com.springboot.service.CustomerTypeService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import java.util.List;

/**
 * 客户类型前端控制器
 */
@Controller
@RequestMapping("/customerType")
public class CustomerTypeController {

    @Autowired
    private CustomerTypeService customerTypeService;

    /**
     * 跳转到客户类型列表页面
     * @return
     */
    @RequestMapping("/toCustomerType")
    public String toCustomerType(){
        return "customer/customerType";
    }


    /**
     * 请求客户类型数据
     * @return
     */
    @RequestMapping("getCustomerType")
    @ResponseBody
    public String getCustomerType(){
        List<CustomerType> all = customerTypeService.findAll();
        return JSON.toJSONString(all);
    }

    /**
     * 删除客户类型
     */
    @RequestMapping("/delCustomer")
    @ResponseBody
    public Boolean delCustomer(Integer[] cuTypeArr){
        return customerTypeService.deleteCustomerTypeByArr(cuTypeArr);
    }


    /**
     * 添加客户类型
     * @return
     */
    @RequestMapping("/addType")
    @ResponseBody
    public Boolean addCustomerType(String type){
        return customerTypeService.addCustomerType(type);
    }
}
