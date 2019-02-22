package com.springboot.service.impl;

import com.springboot.constant.Constant;
import com.springboot.dao.SysRoleMapper;
import com.springboot.dao.UserRoleMapper;
import com.springboot.pojo.SysRole;
import com.springboot.pojo.SysUser;
import com.springboot.pojo.SysUserRole;
import com.springboot.service.RoleService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.SessionScope;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<SysRole> findRoleAll() {
        return sysRoleMapper.findRoleAll();
    }

    @Override
    @Transactional
    public Boolean insertRoleByName(HttpServletRequest request) {
        SysRole sysRole = new SysRole();

        String name = request.getParameter("name");
        sysRole.setName(name);
        Object uid = request.getSession().getAttribute("uid");

        sysRole.setCreateBy((Long) uid);
        sysRole.setCreateDate(Constant.SYS_DATA);
        sysRole.setUpdateBy((Long) uid);
        sysRole.setUpdateDate(Constant.SYS_DATA);
        int i = sysRoleMapper.insertSelective(sysRole);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean delRole(Integer[] ridArr) {


        List<SysUserRole> userRoleByArr = userRoleMapper.findUserRoleByArr(ridArr);

        int i = 0;

        // 如果角色id没有在角色用户表中 说明没有用户对该角色有对应，可以删除，否则不能删除
        if (userRoleByArr == null || userRoleByArr.size() == 0){
             i = sysRoleMapper.delRole(ridArr);
        }

        /**
         * 可以更细粒度一点，将有用户使用的角色返回
         */

        if (i > 0){
            return true;
        }

        return false;
    }

    @Override
    public String findRoleByUserId(HttpServletRequest request) {
        Long uid = (Long) request.getSession().getAttribute("uid");
        String role = sysRoleMapper.findRoleByUserId(uid);
        return role;
    }

}
