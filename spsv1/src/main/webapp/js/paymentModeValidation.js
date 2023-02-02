$(document).ready(function(){
	$('#nrenroind').change(function(){
		//alert($("#nrenroind").val());
		
		$('#mAccountNumber').prop('readonly',true);
		

		if($("#nrenroind").val() == 'select') {
			document.getElementById("instructions").value = "";
		}
		
		if($("#nrenroind").val() == 'NRE') {
			document.getElementById("instructions").value = "I BANK NRE REPAT CASE";
		}
		
		if($("#nrenroind").val() == 'NRO') {
			document.getElementById("instructions").value = "";
		}
		//$('#vAccountNumber').prop('readonly',true);
		/*
		if($("#nrenroind").val() == 'select') {
			document.getElementById("mAccountNumber").value = "";
			document.getElementById("vAccountNumber").value = "";
			$("#paymentMode option[value='select']").remove();
			$("#paymentMode option[value='1']").remove();
			$("#paymentMode option[value='B']").remove();
			$("#paymentMode option[value='5']").remove();
			$("#paymentMode option[value='E']").remove();
			$("#paymentMode option[value='9']").remove();
			$("#paymentMode option[value='F']").remove();
			$("#paymentMode option[value='G']").remove();
			$('#paymentMode').append('<option value="select">Select</option>');
		}
		
		if($("#nrenroind").val() == 'NRE') {
			//alert('ssssssssssssssssssssssss');
			document.getElementById("instructions").value = "";
			document.getElementById("mAccountNumber").value = "";
			document.getElementById("vAccountNumber").value = "";
			document.getElementById("bankName").value = "";
			document.getElementById("mnmCode").value = "";
			document.getElementById("mmicrCode").value = "";
			$("#paymentMode option[value='1']").remove();
			$("#paymentMode option[value='B']").remove();
			$("#paymentMode option[value='5']").remove();
			$("#paymentMode option[value='E']").remove();
			$("#paymentMode option[value='9']").remove();
			$("#paymentMode option[value='F']").remove();
			$("#paymentMode option[value='G']").remove();
			$("#paymentMode option[value='select']").remove();
			$('#paymentMode').append('<option value="select">Select</option>');
			$('#paymentMode').append('<option value="F">DC-NRE</option>');
			$('#paymentMode').append('<option value="G">NEFT-NRE</option>');
		}
		
		if($("#nrenroind").val() == 'NRO') {
			//alert('ssssssssssssssssssssssss');
			document.getElementById("instructions").value = "";
			document.getElementById("mAccountNumber").value = "";
			document.getElementById("vAccountNumber").value = "";
			document.getElementById("bankName").value = "";
			document.getElementById("mnmCode").value = "";
			document.getElementById("mmicrCode").value = "";
			$('#mifscCode').prop('readonly',true);
			$("#paymentMode option[value='F']").remove();
			$("#paymentMode option[value='G']").remove();
			$("#paymentMode option[value='1']").remove();
			$("#paymentMode option[value='B']").remove();
			$("#paymentMode option[value='5']").remove();
			$("#paymentMode option[value='E']").remove();
			$("#paymentMode option[value='9']").remove();
			$("#paymentMode option[value='select']").remove();
			$('#paymentMode').append('<option value="select">Select</option>');
			$('#paymentMode').append('<option value="1">CHEQUE</option>');
			$('#paymentMode').append('<option value="B">NEFT</option>');
			$('#paymentMode').append('<option value="5">Direct Credit</option>');
			$('#paymentMode').append('<option value="E">AADHAR</option>');
			$('#paymentMode').append('<option value="9">ECS</option>');
		}*/
	});
	
	$('#paymentMode').click(function(){
		//alert("sssss");
		if($("#nrenroind").val() == 'select') {
			alert("Please select NRE/NRO Indication.");
			return false;
		}
	});
	
	$("#instructions").blur(function(){
	   
	    
	    if($("#instructions").val().startsWith("I BANK") || $("#instructions").val().startsWith("i bank")) {
			$("#nrenroind").val("NRE");
		}
	    
	});

	
	/*if($('#paymentMode').val() !='select') {
		$('#mAccountNumber').prop('readonly',false);
		$('#vAccountNumber').prop('readonly',false);
	}
	else {
		$('#mAccountNumber').prop('readonly',true);
		$('#vAccountNumber').prop('readonly',true);
	}*/
	
	
/*	$("#mAccountNumber").focus(function(){
		if($("#vAccountNumber").val() != "") {
			document.getElementById("mAccountNumber").value = "";
			document.getElementById("vAccountNumber").value = "";
		}
	});
	*/
});