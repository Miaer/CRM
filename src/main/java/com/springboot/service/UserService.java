package com.springboot.service;

import com.springboot.pojo.SysUser;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    SysUser getAllByUserName(String name);

    Boolean delCustomer(Integer[] uidArr);

    SysUser findUserById(Integer id);

    Boolean updateUserInfo(HttpServletRequest request);
}
