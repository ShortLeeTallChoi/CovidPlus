package com.covidplus.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.covidplus.model.TotalCase;
import com.covidplus.model.UserVO;

@Mapper
public interface DashboardDao {
	public List<Map<String, Object>> selectSidoCaseData(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectGenAgeCaseData(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectNatCaseData(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> selectSidoCaseChartData(Map<String, Object> paramMap);
	
	public TotalCase selectTotalCaseData(Map<String, Object> paramMap);
	public TotalCase selectTotalCaseData_distance(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectTotalCaseWeekData(Map<String, Object> paramMap);	
}
