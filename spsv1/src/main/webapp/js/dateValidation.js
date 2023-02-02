$(document).ready(function(){
	
	$(".datepicker").datepicker({
		dateFormat : 'yy-mm-dd'
	});
	
	$(".expman").click(function(){
		if($("input[name=sdate]").val()=='' || $("input[name=edate]").val()==''){
			alert("please select the date first");
			return false;
		}
		
		if($(".emp_choice1").val()==''){
			alert ("please select username first");
			return false;
		}
	});
	
	
});