package com.springboot.dao;

import com.springboot.pojo.Projectview;

public interface ProjectviewMapper {
    int insert(Projectview record);

    int insertSelective(Projectview record);
}