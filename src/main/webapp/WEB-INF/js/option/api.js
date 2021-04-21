var insertorupdate = "insert";
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

var urlParam = new Map();
var dateParam = new Map();

function parseInfoSetting(data){						//수집목록 선택시 우측 상세 정보 채움
	$('input[name=parsing_key]').val(data.parsing_key);	//수집ID
	$('input[name=parsing_key]').prop('readonly',true);	//수집ID 수정 막기
	if(data.activity=='true'){							//수집활성화
		$('#activity_true').prop('checked',true);
	}else{
		$('#activity_false').prop('checked',true);
	}
	$('input[name=service_url]').val(data.service_url);	//URL
	$('input[name=service_key]').val(data.service_key);	//Key
	

	
	if(data.dateParameter.Today=='true'){				//날짜 설정 오늘/지정날짜 선택
		$('#date_param_today_true').prop('checked',true);
	}else{
		$('#date_param_today_false').prop('checked',true);
	}
	
	if(data.last_to_today == 'true'){
		$('input[name=last_to_today]').prop('checked',true);
	}else{
		$('input[name=last_to_today]').prop('checked',false);
	}
	
	dateParam = new Map();
	for(var key in data.dateParameter.DateParam){
		var paramName = data.dateParameter.DateParam[key];
		dateParam.set(paramName,paramName);
	}	
	urlParam=new Map();
	for(var key in data.urlParameter){
		urlParam.set(key,data.urlParameter[key]);
	}

	drawDateparam();
	drawURLparam();
	
	$('input[name=table_name]').val(data.table_name);
	$('input[name=comment]').val(data.comment);
	$('textarea[name=insert_query]').val(data.insert_query);
	
	insertorupdate = "update";
	$('#confirmBtn').text('수정하기');
}

function parseInfoReset(){
	$('.selectList').removeClass('selectOne');
	$('input[name=parsing_key]').val('');	//수집ID
	$('input[name=parsing_key]').prop('readonly',false);
	$('input[name=service_url]').val('');	//URL
	$('input[name=service_key]').val('');	//Key
	$('input[name=activity]').prop('checked',false);	
	$('input[name=date_param_today]').prop('checked',false);	
	$('input[name=last_to_today]').prop('checked',false);	
	$('#url_parameter').html('');
	var $option = $('<option>----</option>');			//날짜변수 추가 부분 초기화
	$('#parameterList').html($option);	
	urlParam = new Map();
	dateParam = new Map();
	$('#DateParam').html('');
	$('input[name=table_name]').val('');
	$('textarea[name=insert_query]').val('');
	$('#urlParamName').val('');
	$('#urlParamValue').val('');
	$('input[name=comment]').val('');
	
	insertorupdate = "insert";
	$('#confirmBtn').text('추가하기');
}

function removeURLparam(key){
	urlParam.delete(key);
	removeDateparam(key);
}

function removeDateparam(key){
	dateParam.delete(key);
	drawURLparam();
	drawDateparam();
}

function drawURLparam(){
	$('#url_parameter').html('');						//Parameter 내용 비우기
	var $option = $('<option>----</option>');			//날짜변수 추가 부분 초기화
	$('#parameterList').html($option);					//Parameter중 날짜변수로 쓸 변수를 추가하기 위함
	urlParam.forEach((value,key,map)=>{			//Parameter 내용 채우기
		var $indiv = $('<div class="inrow selectList"></div>');
		var $div = $('<div class="col-md-11" style="padding:0px"></div>');
		var $minusdiv = $('<div class="col-md-1" style="padding:0px"><i class="far fa-minus-square"></i></div>');
		
		$div.text(key+':'+value);
		$div.attr('onclick','loadURLparam("'+key+'")');
		$minusdiv.attr('onclick','removeURLparam("'+key+'")');
		$indiv.append($div).append($minusdiv);
		$('#url_parameter').append($indiv);
		
		if(!dateParam.has(key)){
			$option = $('<option></option>');				//날짜 변수 추가 내용 채우기
			$option.addClass('selectList');
			$option.val(key);
			$option.text(key);
			$('#parameterList').append($option);
		}
	});
}

function drawDateparam(){
	$('#DateParam').html('');
	dateParam.forEach((value,key,map)=>{	//날짜 변수 채우기
		var $indiv = $('<div class="inrow selectList"></div>');
		var $div = $('<div class="col-md-10" style="padding:0px"></div>');
		var $minusdiv = $('<div class="col-md-2" style="padding:0px"><i class="far fa-minus-square"></i></div>');
		
		$div.text(value);
		$minusdiv.attr('onclick','removeDateparam("'+value+'")');
		$indiv.append($div).append($minusdiv);
		$('#DateParam').append($indiv);
	});
}

function addUrlParam(){
	var urlParamName = $('#urlParamName').val();
	var urlParamValue = $('#urlParamValue').val();
	$('#urlParamName').val('');
	$('#urlParamValue').val('');
	if(urlParamName.length<1){
		alert('변수명은 필수 입니다.');
		return false;
	}

	urlParam.set(urlParamName,urlParamValue);
	drawURLparam();
	drawDateparam();
}

function loadURLparam(key){
	$('#urlParamName').val(key);
	$('#urlParamValue').val(urlParam.get(key));
}

function addDateParam(tag){
	var paramName = $(tag).val();
	if(paramName!=null){
		dateParam.set(paramName, paramName);
		drawURLparam();
		drawDateparam();
	}
}

function processOption(){
	var urlJsonData = '{';
	urlParam.forEach((value,key,map)=>{
		urlJsonData = urlJsonData+'"'+key+'":"'+value+'",';
	});
	urlJsonData = urlJsonData.slice(0,-1)+'}';
	
	var dateJsonData = '{"Today":"'+$('input[name=date_param_today]').val()+'","DateParam":[';
	dateParam.forEach((value,key,map)=>{
		dateJsonData = dateJsonData+'"'+key+'"'+',';
	});
	if(dateParam.size>0){
		dateJsonData = dateJsonData.slice(0,-1);
	}
	dateJsonData = dateJsonData+']}';
	
	var option = {
			parsing_key : $('input[name=parsing_key]').val(),
			service_url : $('input[name=service_url]').val(),
			service_key : $('input[name=service_key]').val(),
			url_parameter : urlJsonData,
			date_parameter : dateJsonData,
			table_name : $('input[name=table_name]').val(),
			insert_query : $('textarea[name=insert_query]').val(),
			comment : $('input[name=comment]').val(),
			last_to_today : $('input[name=last_to_today]').prop('checked'),
			activity : $('input[name=activity]').val()
	}
	
	$.ajax({
        type: 'POST',
        url: rootPath+'/option/processOption',
        data:{
       	 '_csrf':callToken,
       	 'option':JSON.stringify(option)
       		 },
		success: function(data){
			if(insertorupdate == "insert"){
				alert('수집 설정 추가에 성공하였습니다.')
				location.reload();
			}else if(insertorupdate == "update"){
				alert('수집 설정 수정에 성공하였습니다.')
			}
		},
		failure:function(){
			alert('에러');
		}
    });
}