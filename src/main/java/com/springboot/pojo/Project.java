package com.springboot.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Project {

    private Integer id;

    private String name;

    private Timestamp createTime;
}
