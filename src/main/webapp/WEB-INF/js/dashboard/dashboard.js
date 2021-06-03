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
		$.ajax({
	         type: 'POST',
	         url: location.pathname+'/chartData.do',
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