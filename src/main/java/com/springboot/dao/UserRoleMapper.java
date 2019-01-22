package com.springboot.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {

    @Insert("insert into sys_user_role(user_id,role_id) values(#{userId},#{rid})")
    int insert(@Param("userId") String userId,@Param("rid") String rid);

    @Delete("<script> delete from sys_user_role where user_id in " +
            "<foreach collection='array' open='(' item='item' separator=',' close=')'>" +
            " #{item}" +
            "</foreach> " +
            "</script>")
    int delUser(Integer[] uidArr);
}
