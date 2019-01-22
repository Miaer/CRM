package com.springboot.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    private Long id;

    private String name;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;
}