package com.covidplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.covidplus.model.ParseOptionVO;

@Mapper
public interface OptionDao {
	public List<ParseOptionVO> selectParseOptionList(ParseOptionVO option);
	public ParseOptionVO selectParseOptionOne(ParseOptionVO option);
}
