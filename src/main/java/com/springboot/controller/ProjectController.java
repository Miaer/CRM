package com.springboot.controller;

import com.springboot.dao.SysUserMapper;
import com.springboot.pojo.Customer;
import com.springboot.pojo.ProjectInvest;
import com.springboot.pojo.Projectview;
import com.springboot.pojo.SysUser;
import com.springboot.service.CustromerService;
import com.springboot.service.ProjectService;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("project")
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CustromerService custromerService;

    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     *
     * @param request
     * @param projectName   项目名称
     * @param person        发布项目人
     * @param time          发布时间
     * @return              List<Map<String,String>
     */
    @RequestMapping("/findProjectList")
    @ResponseBody
    public Object findProjectList(HttpServletRequest request,String projectName,String person,String time){
        List<Map<String, String>> projectList = projectService.findProjectList(request,projectName,person,time);
        return projectList;
    }


    /**
     * 查询 该项目id下的客户信息
     * @param projectid
     * @return
     */
    @RequestMapping("/initProject")
    @ResponseBody
    public Object findProjectList(HttpServletRequest request,Integer projectid){
        List<Map<String, String>> projectCustomerByProjectId = projectService.findProjectCustomerByProjectId(request,projectid);
        System.out.println(projectCustomerByProjectId);
        return projectCustomerByProjectId;
    }

    @RequestMapping("/toAddProjectCutomerPage")
    public String toAddProjectCutomerPage(HttpServletRequest request,Model model){
        Long uid = (Long)request.getSession().getAttribute("uid");
        List<Customer> customerList = custromerService.findCustomerByUserId(uid);
        List<SysUser> userList = sysUserMapper.findAllUser();

        model.addAttribute("customerList",customerList);
        model.addAttribute("userList",userList);

        return "project/project_update";
    }


    /**
     *
     * @return
     */
    @RequestMapping("/findProjecatInvestByProId")
    @ResponseBody
    public Map<String, Object> findProjecatInvestByProId(Integer proId,Integer ciId){

        Map<String, Object> maps = projectService.findProjecatInvestByProId(proId,ciId);
        return maps;
    }


    @RequestMapping("/addProject")
    @ResponseBody
    public Boolean addProject(HttpServletRequest request,String projectname){
        Boolean aBoolean = projectService.addProject(request,projectname);
        return aBoolean;
    }

    @RequestMapping("/deleteProject")
    @ResponseBody
    public Boolean deleteProject(Integer id){
        return projectService.deleteProjectById(id);
    }

    @RequestMapping("/deleteProjectInvestByProId")
    @ResponseBody
    public Boolean deleteProjectInvestByProId(Integer id,Integer proId){
        return projectService.deleteProjectInvestByProId(id,proId);
    }

    @RequestMapping("/updateProject")
    @ResponseBody
    public Boolean updateProject(HttpServletRequest request,Projectview projectview){
        return projectService.updateProject(request, projectview);
    }


    @RequestMapping("/findProjecatInvestUser1NameByUserId")
    @ResponseBody
    public Object findProjecatInvestUserNameByUserId(String userName,Integer proId,Integer customerId){
        System.out.println(userName);
        System.out.println(proId);
        Map<String,Object> proUserName =  projectService.findProjecatInvestUserFeeByUserName(userName,proId,customerId);
        return proUserName;
    }
}
