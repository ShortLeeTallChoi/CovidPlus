package com.covidplus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
public class ParsingLogVO extends Pagination{
	private String log_no;
	private String parsing_key;
	private String service_url;
	private String log_count;
	private String parsing_date;
	private String parameter_info;
	private String comment;
	private String success;
	private String fail;
}
