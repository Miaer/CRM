package com.springboot.dao;

import com.springboot.pojo.SysUserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper {

    @Insert("insert into sys_user_role(user_id,role_id) values(#{userId},#{rid})")
    int insert(@Param("userId") Long userId,@Param("rid") String rid);

    @Delete("<script> delete from sys_user_role where user_id in " +
            "<foreach collection='array' open='(' item='item' separator=',' close=')'>" +
            " #{item}" +
            "</foreach> " +
            "</script>")
    int delUser(Integer[] uidArr);

    @Select("select * from sys_user_role where user_id = #{id1}")
    Map<String,String> findRoleByUserId(Long id1);

    @Update("update sys_user_role set role_id = #{role} where user_id = #{userId}")
    int updateRole(@Param("userId") Long userId,@Param("role") Long role);

    @Select("<script>" +
            "select * from sys_user_role where role_id in " +
            "<foreach collection='array' open='(' item='item' separator=',' close=')'>" +
            " #{item}" +
            "</foreach> " +
            "</script>")
    List<SysUserRole> findUserRoleByArr(Integer[] ridArr);
}
