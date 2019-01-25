package com.springboot.security.Shiro;

import com.springboot.pojo.SysUser;
import com.springboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)Token;
        SysUser user = userService.getAllByUserName(token.getUsername());
        if (user == null){//用户名不存在
            return null;
        }

        //判断密码
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
