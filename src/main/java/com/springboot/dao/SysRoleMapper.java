package com.springboot.dao;

import com.springboot.pojo.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper {

    @Select("select * from sys_role")
    List<SysRole> findRoleAll();

    @Insert("insert into sys_role(name,create_by,create_date,update_by,update_date) values(#{name},#{createBy},#{createDate},#{updateBy},#{updateDate})")
    int insertSelective(SysRole sysRole);


    @Delete("<script> delete from sys_role where id in " +
            "<foreach collection='array' open='(' item='item' separator=',' close=')'>" +
            " #{item}" +
            "</foreach> " +
            "</script>")
    int delRole(Integer[] ridArr);

    @Select("SELECT sys_role.`name` FROM sys_user_role,sys_user,sys_role WHERE sys_user.id = sys_user_role.user_id AND sys_user_role.role_id = sys_role.id AND sys_user.id = #{uid} ")
    String findRoleByUserId(Long uid);
}