package com.covidplus.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParseOptionVO {
	String parsing_key;
	String service_url;
	String service_key;
	String url_parameter;
	String date_parameter;
	String table_name;
	String insert_query;
	String comment;
	String last_to_today;
	String activity;
	
	Map<String,Object> urlParameter;
	Map<String,Object> dateParameter;
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	Gson gson = new Gson();
		
	@SuppressWarnings("unchecked")
	public void setUrl_parameter(String url_parameter) {
		this.url_parameter = url_parameter;
		urlParameter = gson.fromJson(url_parameter, Map.class);
	}
	
	@SuppressWarnings("unchecked")
	public void setDate_parameter(String date_parameter) {
		this.date_parameter = date_parameter;
		dateParameter = gson.fromJson(date_parameter, Map.class);
	}
}
