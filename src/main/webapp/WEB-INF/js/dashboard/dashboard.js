$(document).ready(function(){
	$('.distance_value').each(function(idx, val){
		var value = $(val).text();
		if(value > 0){
			$(this).css("color","red");
			$(this).text('+ '+value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","));
		}else if(value < 0){
			$(this).css("color","blue");
			$(this).text('- '+(Math.abs(value)).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","));
		}
	});
	
	$('button').click(function(){
		var country_id = $(this).attr('id').replace('-b','');
		var urlPath;
		console.log(location.pathname.split('/').length);
		if(location.pathname.split('/').length > 3){
			urlPath = 'chartData.do'; 
		}else{
			urlPath = 'dashboard/chartData.do';
		}
		$.ajax({
	         type: 'POST',
	         url: location.pathname+urlPath,
	         data:{
	        	 _csrf:callToken,
	        	 'country_id':country_id
	         },
			success: function(data){
				createTotalLineChart(data);
			}	         
	     });
	});
});