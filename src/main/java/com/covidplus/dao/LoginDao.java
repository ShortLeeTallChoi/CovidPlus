package com.covidplus.dao;

import org.apache.ibatis.annotations.Mapper;

import com.covidplus.model.UserVO;

@Mapper
public interface LoginDao {
	public UserVO memberInfoOne(UserVO vo);
}
