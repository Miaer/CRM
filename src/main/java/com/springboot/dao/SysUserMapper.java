package com.springboot.dao;

import com.springboot.pojo.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {


    @Insert("insert into sys_user(name,password,create_by,create_date,update_by,update_date,node) values (#{name},#{password},#{createBy},#{createDate},#{updateBy},#{updateDate},#{node})")
    @Options(useGeneratedKeys = true)
    int insert(SysUser user);

    @Select("select * from sys_user where name = #{name}")
    SysUser findAllByName(String name);

    @Select("select su.id 'uid',su.name,sr.id 'rid',sr.name 'rname',su.create_date from sys_user_role sur left join sys_user su on sur.user_id = su.id left join sys_role sr on sur.role_id = sr.id")
    List<Map<String,String>> getCustromer();

    @Delete("<script> delete from sys_user where id in " +
                "<foreach collection='array' open='(' item='item' separator=',' close=')'>" +
                    " #{item}" +
                "</foreach> " +
            "</script>")
    int delUser(Integer[] uidArr);

    @Select("select * from sys_user where id = #{id}")
    SysUser findUserById(Integer id);
}