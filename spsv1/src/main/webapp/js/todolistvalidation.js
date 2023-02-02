$(document).ready(function(){
	$("#allocate").show();
	
	$("#allocate").click(function(){
		//alert("welcome....");
		if($("#newassignee").val()=='' || $("#transfer").val()=='' || $("#employee").val()=='')
			{
			alert("please enter all mandatory fields");
			return false;
			}
		else if($("#newassignee").val() == $("#employee").val()){
			alert("Employee field & New Assignee field can't be same.");
			return false;
		}
		else{
			$("#allocate").hide();
		}
	});
	
$("#upld").show();
	
	$("#upld").click(function(){
		
		$("#upld").hide();
		
		
	});
	
	
	
	$('ul.dropdown-menu [data-toggle=dropdown]').on('click', function(event) {
		//alert("hiii");
		event.preventDefault(); 
		event.stopPropagation(); 
		$(this).parent().siblings().removeClass('open');
		$(this).parent().toggleClass('open');
	});
	
	
});
