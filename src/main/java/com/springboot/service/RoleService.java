package com.springboot.service;

import com.springboot.pojo.SysRole;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色信息业务管理器接口
 */
public interface RoleService {

    /**
     * 查询所有的角色信息
     */
    List<SysRole> findRoleAll();

    Boolean insertRoleByName(HttpServletRequest request);

    Boolean delRole(Integer[] ridArr);

    String findRoleByUserId(HttpServletRequest request);
}
