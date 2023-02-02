
$(document).ready(function(){
	$("#myInput").keyup(function(){
	    var inputValue = $("#myInput").val();
	    //alert(inputValue);
	    
	    $.get("/ddentry/cheque/getSearchData", { "inputValue": inputValue }, function(data){
	    	$("#print").empty();
			$.each(data,function(index,element) 
			{
				$("#print").append("<tr>");
				$("#print").append("<td><input type='checkbox'></td>");
				$("#print").append("<td></td>");
				$("#print").append("<td></td>");
				$("#print").append("<td> <br> Policy No: <a href='/ddentry/cheque/displayDescPolicyDetails?policyNo="+ element.policyno +"&clientId="+ element.clientId +"&custName="+ element.custName +"'>"+ element.policyno + "</a><br> Client Id: " + element.clientId + "<br> Customer Name:"+ element.custName + "</td>");
				$("#print").append("<td><a href='/ddentry/cheque/deleteToDoList?policyNo="+element.policyno+"'>Delete</a></td>");
				$("#print").append("</tr>");
	        });
		});
	    
	});
});

