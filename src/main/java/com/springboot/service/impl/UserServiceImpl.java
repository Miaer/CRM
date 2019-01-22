package com.springboot.service.impl;

import com.springboot.dao.SysUserMapper;
import com.springboot.dao.UserRoleMapper;
import com.springboot.pojo.SysUser;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userDao;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public SysUser getAllByUserName(String name) {
        return userDao.findAllByName(name);
    }

    @Override
    @Transactional
    public Boolean delCustomer(Integer[] uidArr) {
        int i = userDao.delUser(uidArr);
        int i1 = userRoleMapper.delUser(uidArr);

        if (i > 0 && i1 > 0){
            return true;
        }
        return false;
    }

    @Override
    public SysUser findUserById(Integer id) {

        return userDao.findUserById(id);
    }

}
