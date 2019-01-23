package com.springboot.dao.Provider;

import com.springboot.pojo.SysUser;
import com.springboot.pojo.Visit;
import org.apache.ibatis.jdbc.SQL;

public class UserReovider {

    public String updateUser(SysUser user){
        return new SQL(){
            {
                UPDATE("sys_user");
                if(user.getPassword()!=null) {
                    SET("password = #{password}");
                }
                if (user.getName()!=null){
                    SET("name = #{name}");
                }
                if(user.getMobile()!=null) {
                    SET("mobile = #{mobile}");
                }
                if(user.getPostion()!=null) {
                    SET("postion = #{postion}");
                }
                if(user.getAreaId()!=null) {
                    SET("area_id = #{areaId}");
                }
                if(user.getLoginIp()!=null) {
                    SET("login_ip = #{loginIp}");
                }
                if(user.getLoginDate()!=null) {
                    SET("login_date = #{loginDate}");
                }
                if(user.getLoginFlag()!=null) {
                    SET("login_flag = #{loginFlag}");
                }

                if(user.getCreateBy()!=null) {
                    SET("create_by = #{createBy}");
                }
                if(user.getCreateDate()!=null) {
                    SET("create_date = #{createDate}");
                }
                if(user.getUpdateBy()!=null) {
                    SET("update_by = #{updateBy}");
                }
                if(user.getUpdateDate()!=null) {
                    SET("update_date = #{updateDate}");
                }
                if(user.getNode()!=null) {
                    SET("node = #{node}");
                }
                if(user.getDelFlag()!=null) {
                    SET("del_flag = #{delFlag}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
