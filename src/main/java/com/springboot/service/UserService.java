package com.springboot.service;

import com.springboot.pojo.SysUser;

public interface UserService {

    SysUser getAllByUserName(String name);

    Boolean delCustomer(Integer[] uidArr);

    SysUser findUserById(Integer id);
}
