$(document).ready(function(){
	$('.selectList').click(function(){
		$('.selectList').removeClass('selectOne');
		$(this).addClass('selectOne');
		var parsing_key = $(this).text();
		$.ajax({
	         type: 'POST',
	         url: rootPath+'/option/selectParseInfo',
	         data:{
	        	 '_csrf':callToken,
	        	 'parsing_key':parsing_key
	        		 },
			success: function(data){
				console.log(data);
				parseInfoSetting(data.parseVO);
			}	         
	     });
	});
});

var urlParam = new Array();
var dateParam = new Array();
function parseInfoSetting(data){						//수집목록 선택시 우측 상세 정보 채움
	$('input[name=parsing_id]').val(data.parsing_key);	//수집ID
	$('input[name=parsing_id]').prop('readonly',true);	//수집ID 수정 막기
	if(data.activity=='true'){							//수집활성화
		$('#activity_true').prop('checked',true);
	}else{
		$('#activity_false').prop('checked',true);
	}
	$('input[name=service_url]').val(data.service_url);	//URL
	$('input[name=service_key]').val(data.service_key);	//Key
	
	$('#url_parameter').html('');						//Parameter 내용 비우기
	urlParam = data.urlParameter;
	var $option = $('<option>----</option>');			//날짜변수 추가 부분 초기화
	$('#parameterList').html($option);					//Parameter중 날짜변수로 쓸 변수를 추가하기 위함
	for ( var key in data.urlParameter) {				//Parameter 내용 채우기
		var $indiv = $('<div class="inrow selectList"></div>');
		var $div = $('<div class="col-md-11" style="padding:0px"></div>');
		var $minusdiv = $('<div class="col-md-1" style="padding:0px"><i class="far fa-minus-square"></i></div>');
		$div.text(key+':'+data.urlParameter[key]);
		$indiv.append($div).append($minusdiv);
		$('#url_parameter').append($indiv);
		
		$option = $('<option></option>');				//날짜 변수 추가 내용 채우기
		$option.addClass('selectList');
		$option.val(key);
		$option.text(key);
		$('#parameterList').append($option);
	}
	
	
	if(data.dateParameter.Today==true){				//날짜 설정 오늘/지정날짜 선택
		$('#date_param_today_true').prop('checked',true);
	}else{
		$('#date_param_today_false').prop('checked',true);
	}
	
	if(data.last_to_today == 'true'){
		$('input[name=last_to_today]').prop('checked',true);
	}else{
		$('input[name=last_to_today]').prop('checked',false);
	}
	
	dateParam = data.dateParameter.DateParam;
	$('#DateParam').html('');
	for ( var value in data.dateParameter.DateParam){	//날짜 변수 채우기
		var $indiv = $('<div class="inrow selectList"></div>');
		var $div = $('<div class="col-md-10" style="padding:0px"></div>');
		var $minusdiv = $('<div class="col-md-2" style="padding:0px"><i class="far fa-minus-square"></i></div>');
		
		$div.text(data.dateParameter.DateParam[value]);
		$indiv.append($div).append($minusdiv);
		$('#DateParam').append($indiv);
	}
	
	$('input[name=table_name]').val(data.table_name);
	$('textarea[name=insert_query]').val(data.insert_query);
	
	$('#confirmBtn').text('수정하기');
}

function parseInfoReset(){
	$('input[name=parsing_id]').val('');	//수집ID
	$('input[name=parsing_id]').prop('readonly',false);
	$('input[name=service_url]').val('');	//URL
	$('input[name=service_key]').val('');	//Key
	$('#url_parameter').html('');
	var $option = $('<option>----</option>');			//날짜변수 추가 부분 초기화
	$('#parameterList').html($option);	
	urlParam = new Array();
	dateParam = new Array();
	$('#DateParam').html('');
	$('input[name=table_name]').val('');
	$('textarea[name=insert_query]').val('');
	$('#confirmBtn').text('추가하기');
}