package com.springboot.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Visit {

    private Integer visitId;

    private Integer customerId;

    private String visitMatters;

    private String visitMemo;

    private String cheduleState;

    @DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss")
    private Date visitTime;
}