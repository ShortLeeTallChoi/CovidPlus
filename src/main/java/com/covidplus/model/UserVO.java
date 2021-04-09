package com.covidplus.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {
	private String member_id;
	private String auth_id;
	private String member_pass;
	private String member_name;
	private String member_addr;
	private String member_phone;
	private Date join_date;
	private Date last_login;
	private int fail_count;
	private String member_email;
	
	private String auth_name;

	public UserVO() {
		super();
	}
	
	public UserVO(String member_id) {
		super();
		this.member_id = member_id;
	}
}
