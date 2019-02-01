package com.springboot.controller;

import com.springboot.dao.UserRoleMapper;
import com.springboot.pojo.Customer;
import com.springboot.pojo.CustomerType;
import com.springboot.pojo.SysRole;
import com.springboot.pojo.SysUser;
import com.springboot.service.CustomerTypeService;
import com.springboot.service.CustromerService;
import com.springboot.service.RoleService;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户管理控制器
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustromerService custromerService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerTypeService customerTypeService;

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping("/getCustomer")
    @ResponseBody
    public Object getCustomer(){
        List<Map<String, String>> custromer = custromerService.getCustromer();
        return custromer;
    }

    /**
     * 跳转到添加用户页面
     * 将角色信息添加到页面
     * @return
     */
    @RequestMapping("/toAdd")
    public String addCustomer(Model model){
        List<SysRole> roleAll = roleService.findRoleAll();
        model.addAttribute("roleAll",roleAll);
        return "user/person_tail";
    }

    @RequestMapping("/addCustomer")
    @ResponseBody
    public Boolean addCustomer(HttpServletRequest request){
        Boolean aBoolean = custromerService.addCustomer(request);
        return aBoolean;
    }

    /**
     * 删除用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delCustomer", method = RequestMethod.POST)
    public Boolean delCustomer(Integer[] uidArr){
        Boolean aBoolean = false;
        if (uidArr != null && uidArr.length > 0){
            aBoolean = userService.delCustomer(uidArr);
        }
        return aBoolean;
    }

    /**
     * 更新用户信息
     * @return
     */
    @RequestMapping("/toUpdate")
    public String UpdateCustomer(@RequestParam("id")Integer id,Model model){
        SysUser user = userService.findUserById(id);
        List<SysRole> roleAll = roleService.findRoleAll();
        model.addAttribute("user",user);
        model.addAttribute("roleAll",roleAll);
        return "user/person_update";
    }



    @RequestMapping("/toCustomerPage")
    public String toCustomerPage(){
        return"customer/customer";
    }


    @RequestMapping("/toAddCustomerPage")
    public String toAddCustomerPage(Model model){
        //查询客户类型
        List<CustomerType> customerAll = customerTypeService.findAll();
        model.addAttribute("customerAll",customerAll);
        return "customer/customer_tail";
    }

    @RequestMapping("/getCustomerAll")
    @ResponseBody
    public Object getCustomerAll(HttpServletRequest request,String customName,String time){
        List<Map<String, String>> customerAll = custromerService.findCustomerAllBySessionUId(request,customName,time);
        return customerAll;
    }


    @RequestMapping("/InsertCustomer")
    @ResponseBody
    public Boolean InsertCustomer(Customer customer,HttpServletRequest request){

        Long uid = (Long) request.getSession().getAttribute("uid");
        customer.setUserId(uid.intValue());
        int i = custromerService.insertCustomerByCustomer(customer);
        if (i > 0){
            return true;
        }
        return false;
    }


    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteCustomer(Integer[] cusArr,HttpServletRequest request){
        Boolean aBoolean = false;
        if (cusArr != null && cusArr.length > 0){
            aBoolean = custromerService.delCustomer(cusArr,request);
        }
        return aBoolean;
    }

    @RequestMapping("/findCustomerById")
    @ResponseBody
    public Map<String, Object> findVisitById(Integer id){
        Map<String, Object> customer = custromerService.findCustomerById(id);
        System.out.println(customer);
        return customer;
    }
}
