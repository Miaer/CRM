package com.springboot.dao;

import com.springboot.dao.Provider.CustromerRrovider;
import com.springboot.pojo.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface CustromerMapper {


    @Select("<script>" +
            "select * from customer,customer_type " +
            "WHERE customer.customer_type = customer_type.id and customer.user_id = #{uid} " +
            "<if test='customName != \"\" and customName != null'>" +
            "   AND POSITION(#{customName} in customer.company_name)" +
            "</if> " +
            "<if test='time != \"\" and time != null'>" +
            "   AND POSITION(#{time} in customer.create_time)" +
            "</if> " +
            "Order By customer.create_time DESC"+
            "</script>"
    )
    List<Map<String, String>> findCustomerAllBySessionUId(@Param("uid") Integer uid,@Param("customName") String customName,@Param("time") String time);

    @InsertProvider(type =  CustromerRrovider.class ,method = "insertEmployeeSql")
    @Options(useGeneratedKeys=true)
    int insertCustomerByCustomer(Customer customer);


    @Delete("<script> delete from customer where id in " +
            "<foreach collection='cusArr' open='(' item='item' separator=',' close=')'>" +
            " #{item}" +
            "</foreach> " +
            " and user_id = #{uid}"+
            "</script>")
    int delCustomers(Map map);


    @Select("select * from customer WHERE id = #{id}")
    Map<String, String> findCustomerById(Integer id);

    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "companyName", column = "company_name"),
            @Result(property = "companyAlias", column = "company_alias"),
            @Result(property = "area", column = "area"),
            @Result(property = "address", column = "address"),
            @Result(property = "majorBusiness", column = "major_business"),
            @Result(property = "prejudice", column = "prejudice"),
            @Result(property = "personName", column = "person_name"),
            @Result(property = "personCompany", column = "person_company"),
            @Result(property = "personPositoin", column = "person_positoin"),
            @Result(property = "personPhone", column = "person_phone"),
            @Result(property = "personPhone2", column = "person_phone2"),
            @Result(property = "identification", column = "identification"),
            @Result(property = "homeAddress", column = "home_address"),
            @Result(property = "assertVolumn", column = "assert_volumn"),
            @Result(property = "memo", column = "memo"),
            @Result(property = "customerType", column = "customer_type"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select * from customer where customer.user_id = #{uid}")
    List<Customer> findCustomerByUserId(Long uid);

    @Select("select * from customer where id = (select customer_id FROM visit WHERE visit_id = #{id})")
    Map<String, String> findCustomerByVisitId(Integer id);
}
