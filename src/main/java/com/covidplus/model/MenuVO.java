package com.covidplus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuVO {
	String menu_name;
	String menu_id;
	String parent_menu_id;
	int menu_depth;
	int menu_order;
	String menu_path;
}
