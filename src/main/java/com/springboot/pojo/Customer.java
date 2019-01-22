package com.springboot.pojo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Customer {

    private Integer id;

    private String companyName;

    private String companyAlias;

    private String area;

    private String address;

    private String majorBusiness;

    private String prejudice;

    private String personName;

    private String personCompany;

    private String personPositoin;

    private String personPhone;

    private String personPhone2;

    private String identification;

    private String homeAddress;

    private Integer assertVolumn;

    private Integer customerType;

    private Integer userId;

    private String memo;

    private Timestamp createTime;
}
