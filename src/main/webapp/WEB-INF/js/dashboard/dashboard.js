$(document).ready(function(){
	$('.distance_value').each(function(idx, val){
		var value = $(val).text();
		if(value > 0){
			$(this).css("color","red");
			$(this).text('+ '+value);
		}else if(value < 0){
			$(this).css("color","blue");
			$(this).text('- '+value);
		}
	});
});