package com.covidplus.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.covidplus.model.ParseOptionVO;

@Mapper
public interface OptionDao {
	public List<ParseOptionVO> selectParseOptionList(ParseOptionVO option);
	public ParseOptionVO selectParseOptionOne(ParseOptionVO option);
	public void replaceParseOption(Map<String, Object> map);
}
