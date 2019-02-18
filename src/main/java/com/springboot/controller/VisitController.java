package com.springboot.controller;

import com.springboot.pojo.Customer;
import com.springboot.pojo.Visit;
import com.springboot.service.CustomerTypeService;
import com.springboot.service.CustromerService;
import com.springboot.service.VisitService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 拜访记录控制器
 */
@Controller
@RequestMapping("visit")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private CustromerService custromerService;

    @RequestMapping("/insertVisit")
    @ResponseBody
    public Boolean insertVisit(Visit visits){
        Boolean b = false;
        int i = visitService.addVisit(visits);
        if (i > 0){
            b = true;
        }
        return b;
    }

    @RequestMapping("/findVisitById")
    @ResponseBody
    public Object findVisitById(Integer id){
        Map<String, Object> visit = custromerService.findCustomerById(id);
        return visit;
    }

    @RequestMapping("/findCustomerByVisitId")
    @ResponseBody
    public Object findVisitByVisitId(Integer id){
        Map<String, String> visit = custromerService.findCustomerByVisitId(id);
        return visit;
    }

    @RequestMapping("/findVisitAll")
    @ResponseBody
    public Object findVisitAll(HttpServletRequest request,String completeTime,String companyName){
        List<Map<String, String>> visitAll = visitService.findVisitAll(request,completeTime,companyName);
        System.out.println(visitAll);
        return visitAll;
    }

    @RequestMapping("/findVisitStateIsChedule")
    @ResponseBody
    public Object findVisitStateIsChedule(HttpServletRequest request){
        List<Map<String, String>> visitStateIsChedule = visitService.findVisitStateIsChedule(request);
        System.out.println(visitStateIsChedule);
        return visitStateIsChedule;
    }

    @RequestMapping("/delVisitByIds")
    @ResponseBody
    public Boolean delVisitByIds(Integer[] visitArr){
        return visitService.delVisitByIds(visitArr);
    }

    @RequestMapping("/toSchedule_tail")
    public String schedule_tail(HttpServletRequest request, Model model){
        /*
            查询出当前用户的客户信息，以供用户=选择预约哪个客户
         */
        Object uid = request.getSession().getAttribute("uid");
        List<Customer> customerByUserId = custromerService.findCustomerByUserId((Long) uid);
        model.addAttribute("customerList",customerByUserId);
        System.out.println(customerByUserId);
        return "customer/schedule";
    }

    @RequestMapping("/findCustomer")
    @ResponseBody
    public Object findCustomer(HttpServletRequest request, Model model){
        /*
            查询出当前用户的客户信息，以供用户=选择预约哪个客户
         */
        Object uid = request.getSession().getAttribute("uid");
        List<Customer> customerByUserId = custromerService.findCustomerByUserId((Long) uid);
        model.addAttribute("customerList",customerByUserId);
        System.out.println(customerByUserId);
        return customerByUserId;
    }

    @RequestMapping("/toUpdataSchedule")
    public String toUpdataSchedule(Model model,Integer visitId){
        Map<String, String> visitMap = visitService.findVisitByOneId(visitId);
        System.out.println(visitMap);
        model.addAttribute("visitMap",visitMap);
        return "work/update_schedule";
    }

    @RequestMapping("/initLookSchedule")
    public String initLookSchedule(Model model,Integer visitId){
        Map<String, String> visitMap = visitService.findVisitByOneId(visitId);
        System.out.println(visitMap);
        model.addAttribute("visitMap",visitMap);
        return "work/look";
    }

    @RequestMapping("/updateVisit")
    @ResponseBody
    public Boolean updateVisit(Visit visits){
        Boolean b = false;
        int i = visitService.updateVisit(visits);
        if (i > 0){
            b = true;
        }
        return b;
    }

    @RequestMapping("scheduleComplete")
    @ResponseBody
    public Boolean scheduleComplete(Integer id){
        Boolean aBoolean = visitService.updateCheduleState(id);
        return aBoolean;
    }

    /**
     * 添加拜访记录控制器
     */
    @RequestMapping("/addVisitRecords")
    @ResponseBody
    public Boolean addVisitRecords(Visit visit){
        Boolean aBoolean = visitService.addVisitRecords(visit);

        return aBoolean;
    }
}
