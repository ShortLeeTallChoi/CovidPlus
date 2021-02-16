package com.covidplus.util;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
	public static <T> T map2voConvert(Map<String, Object> aMap, Class<T> t) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.convertValue(aMap, objectMapper.getTypeFactory().constructType(t));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
