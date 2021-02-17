package com.covidplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.covidplus.model.MenuVO;

@Mapper
public interface MenuDao {
	public List<MenuVO> menuList();
}
