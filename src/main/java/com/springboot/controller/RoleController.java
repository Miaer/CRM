package com.springboot.controller;

import com.springboot.pojo.SysRole;
import com.springboot.service.RoleService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色管理控制器
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService  roleService;

    @RequestMapping("/toRolePage")
    public String toRolePage() {
        return "role/role";
    }

    @RequestMapping("/getRolePage")
    @ResponseBody
    public Object getRolePage() {
        List<SysRole> roleAll = roleService.findRoleAll();
        return roleAll;
    }

    @RequestMapping("/toRole_Tail")
    public String toRole_Tail(){
        return "role/Role_Tail";
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public Boolean addRole(HttpServletRequest request){
        Boolean aBoolean = false;
        aBoolean = roleService.insertRoleByName(request);
        return aBoolean;
    }

    @RequestMapping(value = "/delRole", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delRole(Integer[] ridArr){

        Boolean aBoolean = false;
        if (ridArr != null && ridArr.length > 0){
            aBoolean = roleService.delRole(ridArr);
        }
        return aBoolean;
    }
}
