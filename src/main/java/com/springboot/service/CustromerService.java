package com.springboot.service;

import com.springboot.pojo.Customer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户管理接口
 */
public interface CustromerService {

    /**
     * 获取用户信息
     */
    List<Map<String, String>> getCustromer();

    Boolean addCustomer(HttpServletRequest request);

    List<Map<String,String>> findCustomerAllBySessionUId(HttpServletRequest request,String customName,String time);

    int insertCustomerByCustomer(Customer customer);

    Boolean delCustomer(Integer[] cusArr,HttpServletRequest request);

    Map<String, Object> findCustomerById(Integer id);

    List<Customer> findCustomerByUserId(Long uid);

    Map<String, String> findCustomerByVisitId(Integer id);

}
