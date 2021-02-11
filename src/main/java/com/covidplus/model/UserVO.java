package com.covidplus.model;

import java.util.Date;

import lombok.Data;

@Data
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
	
	private String auth_name;

	public UserVO(String member_id, String member_pass) {
		super();
		this.member_id = member_id;
		this.member_pass = member_pass;
	}
}
