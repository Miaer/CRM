package com.springboot.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class ProjectInvest {

    private Integer projectId;

    private String user1Name;

    private Integer customerId;

    private Date investDate;

    private Double investAmount;

    private Double investFee;

    private String user2Name;

    private Double userFee1;

    private Double userFee2;

    private Double user2Fee1;

    private Double user2Fee2;

    private String memo;

    private String collect;
}