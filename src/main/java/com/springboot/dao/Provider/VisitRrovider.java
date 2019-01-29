package com.springboot.dao.Provider;

import com.springboot.pojo.Visit;
import org.apache.ibatis.jdbc.SQL;

public class VisitRrovider {

    public String insertSql(Visit visit) {
        return new SQL() {
            {
                INSERT_INTO("visit");
                if(visit.getCustomerId()!=null) {
                    VALUES("customer_id","#{customerId}");
                }
                if(visit.getVisitMatters()!=null) {
                    VALUES("visit_matters", "#{visitMatters}");
                }
                if(visit.getVisitMemo()!=null) {
                    VALUES("visit_memo", "#{visitMemo}");
                }
                if(visit.getCheduleState()!=null) {
                    VALUES("chedule_state", "#{cheduleState}");
                }
                if(visit.getVisitTime()!=null) {
                    VALUES("visit_time", "#{visitTime}");
                }
                if(visit.getVisitCompleteTime()!=null) {
                    VALUES("visit_complete_time", "#{visitCompleteTime}");
                }
            }
        }.toString();
    }

    public String updateSql(Visit visit){
        return new SQL(){
            {
                UPDATE("visit");
                if(visit.getVisitMatters()!=null) {
                    SET("visit_matters = #{visitMatters}");
                }
                if (visit.getCustomerId()!=null){
                    SET("customer_id = #{customerId}");
                }
                if(visit.getVisitMemo()!=null) {
                    SET("visit_memo = #{visitMemo}");
                }
                if(visit.getCheduleState()!=null) {
                    SET("chedule_state = #{cheduleState}");
                }
                if(visit.getVisitTime()!=null) {
                    SET("visit_time = #{visitTime}");
                }
                WHERE("visit_id = #{visitId}");
            }
        }.toString();
    }
}
