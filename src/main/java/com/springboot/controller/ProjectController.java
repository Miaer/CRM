package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("project")
public class ProjectController {

    @RequestMapping("/findProjectList")
    @ResponseBody
    public String findProjectList(HttpServletRequest request){
        Object recodeTitle = request.getAttribute("recodeTitle");
        return "";
    }
}
