package com.covidplus.model;

import lombok.Data;

@Data
public class ParsingLogVO extends Pagination{
	String log_no;
	String parsing_key;
	String service_url;
	String log_count;
	String parsing_date;
	String parameter_info;
	String comment;
	String success;
	String fail;
}
