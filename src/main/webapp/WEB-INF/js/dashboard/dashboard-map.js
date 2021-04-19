 var polygonTemplate;

function createMapChart(data) {
	var htmlSource='';
	for ( var value in data.sidoCase) {
		var gubun = data.sidoCase[value].gubun;
		var inc_dec =  data.sidoCase[value].inc_dec;
		var def_cnt =  data.sidoCase[value].def_cnt;
		var inc_dec_distance =  data.sidoCase[value].inc_dec_distance;
		var def_cnt_distance =  data.sidoCase[value].def_cnt_distance;
		
		$('#'+data.sidoCase[value].country_id).html(inc_dec+distance_value_map(inc_dec_distance));
	}
}

function distance_value_map(value){
	var html = "<br><span class='small_font'>(<span class='"
	if(value > 0){
		html = html+"small_red'>+"+value
	}else if(value < 0){
		html = html+"small_blue'>"+value
	}else{
		html = html+"small_black'>"+value
	}
	html = html+"</span>)</span>";
	return html;
}