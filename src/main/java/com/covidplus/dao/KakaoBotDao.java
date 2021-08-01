package com.covidplus.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface KakaoBotDao {
    public List<Map<String,String>> selectCoronaSido(Map<String,String> paramMap);
}
