package com.springboot.service.impl;

import com.springboot.constant.Constant;
import com.springboot.dao.VisitMapper;
import com.springboot.pojo.Visit;
import com.springboot.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.springboot.constant.Constant.SYS_DATA;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitMapper visitMapper;
    @Override
    public List<Map<String, String>> findVisitAll(HttpServletRequest request,String completeTime,String companyName) {
        Long uid = (Long) request.getSession().getAttribute("uid");
        return visitMapper.findVisitAll(uid,completeTime,companyName);
    }

    @Override
    public Map<String, String> findVisitById(Integer id) {
        return visitMapper.findVisitById(id);
    }


    @Override
    public int addVisit(Visit visit) {
        visit.setCheduleState(String.valueOf(0));//预约状态  0表示预约状态 1表示已完成预约  因为此处是添加一条预约  所以手动填充0
        visit.setVisitTime(new Timestamp(visit.getVisitTime().getTime()));
        return visitMapper.addVisit(visit);
    }

    @Override
    public List<Map<String, String>> findVisitStateIsChedule(HttpServletRequest request) {
        Object uid = request.getSession().getAttribute("uid");
        String scheduleTime = request.getParameter("scheduleTime");
        String customName = request.getParameter("customName");
        return visitMapper.findVisitStateIsChedule((Long) uid,scheduleTime,customName);
    }

    @Override
    public Boolean delVisitByIds(Integer[] visitArr) {
        return visitMapper.delVisitByIds(visitArr) > 0 ? true : false;
    }

    @Override
    public int updateVisit(Visit visits) {
        visits.setCheduleState(String.valueOf(0));
        return visitMapper.updateVisit(visits);
    }

    @Override
    public Map<String, String> findVisitByOneId(Integer visitId) {
        return visitMapper.findVisitByOneId(visitId);
    }

    @Override
    public Boolean updateCheduleState(Integer id) {
        Timestamp visitCompleteTime = Constant.SYS_DATA;
        int i = visitMapper.updateCheduleState(id, visitCompleteTime);
        if (i > 0){
            return true;
        }
        return false;
    }

    /**
     * 添加拜访记录
     * @param visit 实体
     * @return Boolean 是否成功
     */
    @Override
    public Boolean addVisitRecords(Visit visit) {
        visit.setCheduleState(String.valueOf(1));
        visit.setVisitCompleteTime(new Timestamp(visit.getVisitCompleteTime().getTime()));
        int i = visitMapper.addVisit(visit);
        if (i > 0){
            return true;
        }
        return false;
    }

}
