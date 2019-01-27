package com.springboot.service;

import com.springboot.pojo.Projectview;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ProjectService {

    List<Map<String,String>> findProjectList(HttpServletRequest request,String projectName,String person,String time);

    Boolean addProject(HttpServletRequest request,String projectName);

    Boolean deleteProjectById(Integer id);

    List<Map<String,String>> findProjectCustomerByProjectId(HttpServletRequest request,Integer projectid);

    Map<String,Object> findProjecatInvestByProId(Integer proId,Integer ciId);

    Boolean updateProject(HttpServletRequest request, Projectview projectview);

    Map<String, Object> findProjecatInvestUserFeeByUserName(String userName, Integer proId);

    Boolean deleteProjectInvestByProId(Integer id, Integer proId);
}
