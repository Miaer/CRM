package com.springboot.pojo;


import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class SysUser{

    @Id
    private Long id;

    private String password;

    private String name;

    private String mobile;

    private String postion;

    private String photo;

    private Long areaId;

    private String loginIp;

    private Date loginDate;

    private String loginFlag;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    private String delFlag;

    private String node;

}