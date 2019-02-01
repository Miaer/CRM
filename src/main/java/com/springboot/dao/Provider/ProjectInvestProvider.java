package com.springboot.dao.Provider;

import com.springboot.pojo.ProjectInvest;
import org.apache.ibatis.jdbc.SQL;

public class ProjectInvestProvider {

    public String updateProjectInvest(ProjectInvest projectInvest){
        return new SQL(){
            {
                UPDATE("project_invest");
                if(projectInvest.getProjectId()!=null) {
                    SET("project_id = #{projectId}");
                }
                if(projectInvest.getUser1Name()!=null) {
                    SET("user1_name = #{user1Name}");
                }
                if(projectInvest.getCustomerId()!=null) {
                    SET("customer_id = #{customerId}");
                }
                if(projectInvest.getInvestDate()!=null) {
                    SET("invest_date = #{investDate}");
                }
                if(projectInvest.getInvestAmount()!=null) {
                    SET("invest_amount = #{investAmount}");
                }
                if(projectInvest.getInvestFee()!=null) {
                    SET("invest_fee = #{investFee}");
                }
                if(projectInvest.getUser2Name()!=null) {
                    SET("user2_name = #{user2Name}");
                }
                if(projectInvest.getUserFee1()!=null) {
                    SET("user_fee1 = #{userFee1}");
                }
                if(projectInvest.getUserFee2()!=null) {
                    SET("user_fee2 = #{userFee2}");
                }
                if(projectInvest.getUser2Fee1()!=null) {
                    SET("user2_fee1 = #{user2Fee1}");
                }
                if(projectInvest.getUser2Fee2()!=null) {
                    SET("user2_fee2 = #{user2Fee2}");
                }
                if(projectInvest.getMemo()!=null) {
                    SET("memo = #{memo}");
                }
                if(projectInvest.getCollect()!=null) {
                    SET("collect = #{collect}");
                }

                WHERE("project_id = #{projectId} and customer_id = #{customerId}");
            }
        }.toString();
    }


    public String insertProjectInvest(ProjectInvest projectInvest) {
        return new SQL() {
            {
                INSERT_INTO("project_invest");
                if(projectInvest.getProjectId()!=null) {
                    VALUES("project_id","#{projectId}");
                }
                if(projectInvest.getUser1Name()!=null) {
                    VALUES("user1_name","#{user1Name}");
                }
                if(projectInvest.getCustomerId()!=null) {
                    VALUES("customer_id","#{customerId}");
                }
                if(projectInvest.getInvestDate()!=null) {
                    VALUES("invest_date","#{investDate}");
                }
                if(projectInvest.getInvestAmount()!=null) {
                    VALUES("invest_amount","#{investAmount}");
                }
                if(projectInvest.getInvestFee()!=null) {
                    VALUES("invest_fee","#{investFee}");
                }
                if(projectInvest.getUser2Name()!=null) {
                    VALUES("user2_name","#{user2Name}");
                }
                if(projectInvest.getUserFee1()!=null) {
                    VALUES("user_fee1","#{userFee1}");
                }
                if(projectInvest.getUserFee2()!=null) {
                    VALUES("user_fee2","#{userFee2}");
                }
                if(projectInvest.getUser2Fee1()!=null) {
                    VALUES("user2_fee1","#{user2Fee1}");
                }
                if(projectInvest.getUser2Fee2()!=null) {
                    VALUES("user2_fee2","#{user2Fee2}");
                }
                if(projectInvest.getMemo()!=null) {
                    VALUES("memo","#{memo}");
                }
                if(projectInvest.getCollect()!=null) {
                    VALUES("collect ","#{collect}");
                }
            }
        }.toString();
    }
}
