package com.springboot.dao;

import com.springboot.dao.Provider.ProjectInvestProvider;
import com.springboot.pojo.Project;
import com.springboot.pojo.ProjectInvest;
import org.apache.ibatis.annotations.*;
import sun.reflect.CallerSensitive;

import java.util.List;
import java.util.Map;

public interface ProjectMapper {

    @Select("select * FROM project")
    List<Map<String, String>> findProjectList(Long uid,String projectName,String person,String time);

    @Insert("insert into project(name,create_time) values(#{name},#{createTime})")
    @Options(useGeneratedKeys=true)
    int addProject(Project project);

    @Delete("delete from project where id = #{id}")
    int deleteProjectById(Integer id);


    @Select("SELECT * FROM customer LEFT JOIN project_invest on project_invest.customer_id = customer.id LEFT JOIN project on project_invest.project_id = project.id WHERE project.id = #{id} AND customer.user_id = #{uid}")
    List<Map<String, String>> findProjectCustomerByProjectId(@Param("id") Integer projectid,@Param("uid")Long uid);


    @Select("SELECT \t project_id,\n" +
            "\tuser1_name,\n" +
            "\tcustomer_id,\n" +
            "\tinvest_date,\n" +
            "\tinvest_amount,\n" +
            "\tinvest_fee,\n" +
            "\tuser2_name,\n" +
            "\tuser_fee1,\n" +
            "\tuser_fee2,\n" +
            "\tuser2_fee1,\n" +
            "\tuser2_fee2,\n" +
            "\tmemo,\n" +
            "\tcollect FROM project_invest WHERE project_invest.project_id = #{proId} and customer_id = #{cuid}")
    Map<String, Object> findProjectInvestByProId(@Param("proId") Integer proId,@Param("cuid") Integer cuId);

    @Insert("INSERT INTO project_invest(project_id) VALUES(#{id})")
    int addProjectIdToInvest(@Param("id") Integer id);

    @Delete("delete from project_invest where project_id = #{id}")
    int deleteInvestById(Integer id);

    @Select("SELECT \n" +
            "  project_id 'projectId',\n" +
            "\tuser1_name 'user1_name',\n" +
            "\tcustomer_id 'customerId',\n" +
            "\tinvest_date 'investDate',\n" +
            "\tinvest_amount 'investAmount',\n" +
            "\tinvest_fee 'investFee',\n" +
            "\tuser2_name 'user2_name',\n" +
            "\tuser_fee1 'userFee1',\n" +
            "\tuser_fee2 'userFee2',\n" +
            "\tuser2_fee1 'user2Fee1',\n" +
            "\tuser2_fee2 'user2Fee2',\n" +
            "\tmemo 'memo',\n" +
            "\tcollect 'collect' \n" +
            "FROM\n" +
            "\tproject_invest \n" +
            "WHERE\n" +
            "\t project_invest.project_id = #{projectId} AND project_invest.customer_id = #{customerId}")
    ProjectInvest findProjectInvestExistByProIdAndCustomerId(@Param("projectId") Integer projectId,@Param("customerId")Integer customerId);

    @UpdateProvider(type = ProjectInvestProvider.class,method = "updateProjectInvest")
    int updateProjectInvest(ProjectInvest projectInvest);

    @InsertProvider(type = ProjectInvestProvider.class,method = "insertProjectInvest")
    int insertProjectInvest(ProjectInvest projectInvest);

    @Select("SELECT user_fee1,user_fee2,user2_fee1,user2_fee2 FROM project_invest WHERE project_invest.project_id = #{proId} AND project_invest.user1_name = #{userName}")
    Map<String, Object> findProjecatInvestUserFeeByUserName(@Param("userName") String userName,@Param("proId") Integer proId);

    @Delete("delete  from project_invest where project_invest.project_id = #{proId} AND project_invest.customer_id = #{cuId}")
    int deleteProjectInvestByProId(@Param("cuId") Integer id,@Param("proId") Integer proId);
}
