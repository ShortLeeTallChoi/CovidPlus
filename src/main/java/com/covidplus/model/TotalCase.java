package com.covidplus.model;

import lombok.Data;

@Data
public class TotalCase {
	String create_dt;
	String decide_cnt;
	String clear_cnt;
	String exam_cnt;
	String death_cnt;
	String care_cnt;
	String result_neg_cnt;
	String acc_exam_cnt;
	String acc_exam_comp_cnt;
	String acc_def_rate;
	String inc_dec;
}
