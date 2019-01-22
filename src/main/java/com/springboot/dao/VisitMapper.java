package com.springboot.dao;

import com.springboot.dao.Provider.VisitRrovider;
import com.springboot.pojo.Visit;
import org.apache.ibatis.annotations.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public interface VisitMapper {

    @Select("<script>"+
            "select * from " +
            "customer,visit,customer_type " +
            "WHERE customer.id = visit.customer_id " +
            "AND customer.customer_type = customer_type.id " +
            "AND visit.chedule_state = 1 " +
            "AND customer.user_id = #{0} " +
            "<if test='companyName != \"\" and companyName != null'>" +
            "   AND POSITION(#{companyName} in customer.company_name)" +
            "</if>" +
            "<if test='completeTime != \"\" and completeTime != null'>" +
            "   AND POSITION(#{completeTime} in visit.visit_complete_time)" +
            "</if>" +
            "Order By visit.visit_time DESC"+
            "</script>")
    List<Map<String, String>> findVisitAll(@Param("0") Long uid,@Param("completeTime")String completeTime,@Param("companyName") String companyName);

    @Select("select * from customer,visit WHERE customer.id = visit.customer_id and customer.id in (#{id})")
    Map<String,String> findVisitById(Integer id);

   /* @Insert("insert into visit(customer_id)  values(#{id})")
    int insertVisit(Integer id);*/


    @Delete("<script> delete from visit where customer_id in " +
            "<foreach collection='array' open='(' item='item' separator=',' close=')'>" +
            " #{item}" +
            "</foreach> " +
            "</script>")
    Boolean delVisitByIdArr(Integer[] cusArr);

    @InsertProvider(type = VisitRrovider.class,method = "insertSql")
    int addVisit(Visit visit);

    @Select("<script>"+
            "select * from customer,visit WHERE customer.id = visit.customer_id and  visit.chedule_state = 0 and customer.user_id = #{uid} " +
            "<if test='scheduleTime != \"\" and scheduleTime != null'>" +
            "   AND POSITION(#{scheduleTime} in visit.visit_time)" +
            "</if> " +
            "Order By visit.visit_time DESC"+
            "</script>")
    List<Map<String, String>> findVisitStateIsChedule(@Param("uid") Long uid,@Param("scheduleTime") String scheduleTime);

    @Delete("<script> delete from visit where visit_id in " +
            "<foreach collection='array' open='(' item='item' separator=',' close=')'>" +
            " #{item}" +
            "</foreach> " +
            "</script>")
    int delVisitByIds(Integer[] visitArr);

    /**
     * 更新预约记录mapper方法
     * @param visits
     * @return
     */
    @UpdateProvider(type = VisitRrovider.class,method = "updateSql")
    int updateVisit(Visit visits);

    @Select("select * from customer,visit WHERE customer.id = visit.customer_id and visit.visit_id = (#{id})")
    Map<String, String> findVisitByOneId(Integer visitId);

    @Update("update visit set chedule_state = 1,visit_complete_time = #{visitCompleteTime} where visit_id = #{id}")
    int updateCheduleState(@Param("id") Integer id, @Param("visitCompleteTime") Timestamp visitCompleteTime);
}
