package com.springboot.service.impl;

import com.springboot.constant.Constant;
import com.springboot.dao.SysRoleMapper;
import com.springboot.pojo.SysRole;
import com.springboot.pojo.SysUser;
import com.springboot.service.RoleService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.SessionScope;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

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
        int i = sysRoleMapper.delRole(ridArr);
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
