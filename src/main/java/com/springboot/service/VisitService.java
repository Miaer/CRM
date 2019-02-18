package com.springboot.service;

import com.springboot.pojo.Visit;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface VisitService {

    List<Map<String, String>> findVisitAll(HttpServletRequest request,String completeTime,String companyName);

    Map<String,String> findVisitById(Integer id);

    int addVisit(Visit visits);

    List<Map<String,String>> findVisitStateIsChedule(HttpServletRequest request);

    Boolean delVisitByIds(Integer[] visitArr);

    int updateVisit(Visit visits);

    Map<String,String> findVisitByOneId(Integer visitId);

    Boolean updateCheduleState(Integer id);

    Boolean addVisitRecords(Visit visit);
}
