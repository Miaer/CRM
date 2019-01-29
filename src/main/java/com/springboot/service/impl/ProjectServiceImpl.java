package com.springboot.service.impl;

import com.springboot.constant.Constant;
import com.springboot.dao.CustomerMapper;
import com.springboot.dao.ProjectMapper;
import com.springboot.pojo.Customer;
import com.springboot.pojo.Project;
import com.springboot.pojo.ProjectInvest;
import com.springboot.pojo.Projectview;
import com.springboot.service.CustromerService;
import com.springboot.service.ProjectService;
import org.apache.ibatis.annotations.Select;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    CustomerMapper customerMapper;
    /**
     *
     * @param request
     * @param projectName
     * @param person
     * @param time
     * @return
     */
    @Override
    public List<Map<String, String>> findProjectList(HttpServletRequest request,String projectName,String person,String time) {

        Long uid = (Long) request.getSession().getAttribute("uid");
        return projectMapper.findProjectList(uid,projectName,person,time);
    }

    /**
     * 添加project
     * @return 返回处理结果
     */
    @Override
    @Transactional
    public Boolean addProject(HttpServletRequest request, String projectName) {
        Project project = new Project();
        project.setName(projectName);
        project.setCreateTime(Constant.SYS_DATA);

        int i = projectMapper.addProject(project);
        Integer id = project.getId();
        Long uid = (Long) request.getSession().getAttribute("uid");
        int j = projectMapper.addProjectIdToInvest(id);
        if (i > 0 && j > 0){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteProjectById(Integer id) {
        Boolean var1 = false;
        int i = projectMapper.deleteProjectById(id);
        int j = projectMapper.deleteInvestById(id);
        if (i > 0 && j > 0){
            var1 = true;
        }
        return var1;
    }

    @Override
    public List<Map<String, String>> findProjectCustomerByProjectId(HttpServletRequest request,Integer projectid) {
        Long uid = (Long) request.getSession().getAttribute("uid");
        List<Map<String, String>> projectCustomerByProjectId = projectMapper.findProjectCustomerByProjectId(projectid,uid);
        return projectCustomerByProjectId;
    }

    @Override
    public Map<String, Object> findProjecatInvestByProId(Integer proId, Integer ciId) {
        return projectMapper.findProjectInvestByProId(proId,ciId);
    }


    /**
     * 添加或修改项目数据
     * @param request
     * @param projectview
     * @return
     */
    @Transactional
    @Override
    public Boolean updateProject(HttpServletRequest request, Projectview projectview) {

        Integer projectId = projectview.getProjectId();
        Integer customerId = projectview.getCustomerId();
        /**
         * 同一项目，同一客户id ，查 projectInvest 表
         * 如果 查得到记录 认为  需要更新  数据  反之 则是新增
         */
        ProjectInvest projectInvest1 =  projectMapper.findProjectInvestExistByProIdAndCustomerId(projectId,customerId);

        ProjectInvest projectInvest = packingProjectInvest(projectview);
        Customer customer1 = packingCustomer(projectview);


        Boolean var = false;
        if (projectInvest1 != null){ //更新
            Integer customerId1 = projectInvest1.getCustomerId();
            Integer projectId1 = projectInvest1.getProjectId();

            if (customerId1 != null && projectId1 != null){

                int i = projectMapper.updateProjectInvest(projectInvest);
                int i1 = customerMapper.updateCustromer(customer1);

                if (i > 0 && i1 > 0){
                    var = true;
                }
            }

        }else {//新增
            int i = projectMapper.insertProjectInvest(projectInvest);
            int i1 = customerMapper.updateCustromer(customer1);
            if (i > 0 && i1 > 0){
                var = true;
            }
        }


        return var;
    }

    @Override
    public Map<String, Object> findProjecatInvestUserFeeByUserName(String userName, Integer proId,Integer customerId) {
        return projectMapper.findProjecatInvestUserFeeByUserName(userName,proId,customerId);
    }

    @Override
    public Boolean deleteProjectInvestByProId(Integer id, Integer proId) {

        if (id != null && id > 0 && proId != null){
             int i = projectMapper.deleteProjectInvestByProId(id,proId);
             if (i > 0){
             return true;
             }
        }
        return false;
    }



    private Customer packingCustomer(Projectview projectview) {
        Customer customer = new Customer();
        Integer customerId = projectview.getCustomerId();
        String identification = projectview.getIdentification();
        String personPhone = projectview.getPersonPhone();
        String personPhone2 = projectview.getPersonPhone2();
        String homeAddress = projectview.getHomeAddress();
        Integer assertVolumn = projectview.getAssertVolumn();
        String personCompany = projectview.getPersonCompany();
        String personPositoin = projectview.getPersonPositoin();
        String cumemo = projectview.getCumemo();

        customer.setId(customerId);
        customer.setIdentification(identification);
        customer.setPersonPhone(personPhone);
        customer.setPersonPhone2(personPhone2);
        customer.setHomeAddress(homeAddress);
        customer.setAssertVolumn(assertVolumn);
        customer.setPersonCompany(personCompany);
        customer.setPersonPositoin(personPositoin);
        customer.setMemo(cumemo);
        return customer;
    }
    private ProjectInvest packingProjectInvest(Projectview projectview) {

        ProjectInvest projectInvest = new ProjectInvest();
        /**
         * 获取projectInvest需要的参数
         */
        Integer projectId = projectview.getProjectId();
        Integer customerId = projectview.getCustomerId();
        String investDate = projectview.getInvestDate();
        Double investAmount = projectview.getInvestAmount();
        Double investFee = projectview.getInvestFee();
        String user1name = projectview.getUser1name();
        String user2name = projectview.getUser2name();
        Double userFee1 = projectview.getUserFee1();
        Double userFee2 = projectview.getUserFee2();
        Double user2Fee1 = projectview.getUser2Fee1();
        Double user2Fee2 = projectview.getUser2Fee2();

        String projectMemo = projectview.getProjectMemo();
        String collect = projectview.getCollect();

        //封装到projectInvest对象中去
        projectInvest.setProjectId(projectId);
        projectInvest.setUser1Name(user1name);
        projectInvest.setCustomerId(customerId);

        /**
         * String to java.sql.Date
         */
        try {
            projectInvest.setInvestDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(investDate).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        projectInvest.setInvestAmount(investAmount);
        projectInvest.setInvestFee(investFee);

        projectInvest.setUser1Name(user1name);

        projectInvest.setUser2Name(user2name);
        projectInvest.setUserFee1(userFee1);
        projectInvest.setUserFee2(userFee2);
        projectInvest.setUser2Fee1(user2Fee1);
        projectInvest.setUser2Fee2(user2Fee2);

        projectInvest.setMemo(projectMemo);
        projectInvest.setCollect(collect);

        return projectInvest;
    }
}