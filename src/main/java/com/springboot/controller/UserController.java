package com.springboot.controller;

import com.springboot.pojo.SysRole;
import com.springboot.pojo.SysUser;
import com.springboot.service.RoleService;
import com.springboot.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private RoleService roleService;
    private SysUser user;

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String Login(String userName, String password, Model model){
        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            //执行登陆方法
            subject.login(token);
            //登陆成功
            user = userService.getAllByUserName(token.getUsername());

            //将用户id存入session
            subject.getSession().setAttribute("uid",user.getId());
            subject.getSession().setAttribute("uname",user.getName());
            return "redirect:toIndex";
        } catch (UnknownAccountException e) {
            //登陆失败:用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            //登陆失败:密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/toIndex")
    public String toIndex(Model model){
            model.addAttribute("user",user);
        /**
         * 登陆成功之后需要将数据带到后台
         */
        return "index";
    }


    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping("/toCustomerPage")
    public String customer(Model model){
        List<SysRole> roleAll = roleService.findRoleAll();
        model.addAttribute("roleAll",roleAll);
        return "user/person";
    }

    /**
     * toHome
     */
    @RequestMapping("/toHome")
    public String toHome() {
        return "user/home";
    }

    @RequestMapping("/exit")
    public String exit(HttpServletRequest request){
        request.getSession().removeAttribute("uid");
        return "login";
    }

    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request){
        Boolean aBoolean = userService.updateUserInfo(request);
        return "";
    }
}
