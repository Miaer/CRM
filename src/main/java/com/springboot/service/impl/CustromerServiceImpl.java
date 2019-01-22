package com.springboot.service.impl;

import com.springboot.constant.Constant;
import com.springboot.dao.CustromerMapper;
import com.springboot.dao.SysUserMapper;
import com.springboot.dao.UserRoleMapper;
import com.springboot.dao.VisitMapper;
import com.springboot.pojo.Customer;
import com.springboot.pojo.SysUser;
import com.springboot.service.CustromerService;
import com.springboot.service.VisitService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustromerServiceImpl implements CustromerService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private CustromerMapper custromerMapper;

    @Autowired
    private VisitMapper visitMapper;

    @Override
    public List<Map<String, String>> getCustromer() {
        return userMapper.getCustromer();
    }

    @Override
    @NonNull
    @Transactional
    public Boolean addCustomer(HttpServletRequest request) {

        String name = request.getParameter("name");
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
        user.setName(name);
        user.setPassword(password);
        user.setCreateBy(uid);
        user.setCreateDate(Constant.SYS_DATA);
        //user.setDelFlag("0");
        user.setUpdateBy(uid);
        user.setUpdateDate(Constant.SYS_DATA);
        user.setNode(note);
        int insert1 = userMapper.insert(user);

        String userId = user.getId().toString();
        int insert = userRoleMapper.insert(userId, role);

        if (insert > 0 && insert1 > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, String>> findCustomerAllBySessionUId(HttpServletRequest request,String customName,String time) {
        Long uid1 = (Long) request.getSession().getAttribute("uid");
        Integer uid = uid1.intValue();
        return custromerMapper.findCustomerAllBySessionUId(uid,customName,time);
    }

    @Override
    @Transactional
    public int insertCustomerByCustomer(Customer customer) {
        customer.setCreateTime(Constant.SYS_DATA);
        int i = custromerMapper.insertCustomerByCustomer(customer);
        return i;
    }

    @Override
    @Transactional
    public Boolean delCustomer(Integer[] cusArr,HttpServletRequest request) {
        Object uid = request.getSession().getAttribute("uid");
        visitMapper.delVisitByIdArr(cusArr);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("cusArr",cusArr);
        map.put("uid",uid);

        int i = custromerMapper.delCustomers(map);
        return i > 0 ? true : false;
    }

    @Override
    public Map<String, String> findCustomerById(Integer id) {
        return custromerMapper.findCustomerById(id);
    }

    @Override
    public List<Customer> findCustomerByUserId(Long uid) {
        return custromerMapper.findCustomerByUserId(uid);
    }
}
