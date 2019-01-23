package com.springboot.service.impl;

import com.springboot.constant.Constant;
import com.springboot.dao.SysUserMapper;
import com.springboot.dao.UserRoleMapper;
import com.springboot.pojo.SysUser;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

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

    @Override
    @Transactional
    public Boolean updateUserInfo(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        Long userid = Long.valueOf(request.getParameter("uid"));
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        //String againPassword = request.getParameter("againPassword");
        String note = request.getParameter("note");
        Long uid = (Long) request.getSession().getAttribute("uid");
        /**
         * 1.将用户名和密码存入用户表，获取到该条记录的id
         * 2.将角色id  和 上面获取新增用户id 存入 user_role关联表中
         */
        SysUser user = new SysUser();
        user.setId(userid);
        user.setName(userName);
        user.setPassword(password);
        user.setUpdateBy(uid);
        user.setUpdateDate(Constant.SYS_DATA);
        user.setNode(note);

        int updateUserInfo = userDao.updateUserInfo(user);

        String userId = uid.toString();
        int insert = userRoleMapper.updateRole(userId, role);

        if (insert > 0 && updateUserInfo > 0){
            return true;
        }
        return null;
    }

}
