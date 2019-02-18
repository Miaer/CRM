package com.springboot.dao;

import com.springboot.dao.Provider.UserReovider;
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

    @Select("<script>"+
            "select su.id 'uid',su.name,sr.id 'rid',sr.name 'rname',su.create_date 'createDate' from sys_user_role sur left join sys_user su on sur.user_id = su.id left join sys_role sr on sur.role_id = sr.id WHERE su.`name` is not null " +
            "<if test='user != \"\" and user != null'>" +
            "   AND su.`name` = #{user}" +
            "</if> " +
            "<if test='role != \"\" and role != null'>" +
            "   AND sr.id = #{role}" +
            "</if>" +
            "</script>")
    List<Map<String,String>> getCustromer(@Param("user") String user,@Param("role") String role);

    @Delete("<script> delete from sys_user where id in " +
                "<foreach collection='array' open='(' item='item' separator=',' close=')'>" +
                    " #{item}" +
                "</foreach> " +
            "</script>")
    int delUser(Integer[] uidArr);

    @Select("select * from sys_user where id = #{id}")
    SysUser findUserById(Integer id);

    @UpdateProvider(type = UserReovider.class,method = "updateUser")
    int updateUserInfo(SysUser user);

    @Select("SELECT sys_user.* FROM sys_user,sys_role,sys_user_role WHERE sys_user.id = sys_user_role.user_id AND sys_role.id = sys_user_role.role_id AND sys_role.`name` = '理财师'")
    List<SysUser> findAllUser();
}