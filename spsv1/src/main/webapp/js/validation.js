
$(document).ready(function(){
	 $("#adddiscr").hide();
		
		$("#othercheck").click(function(){
			//$("#othercheckbox").show();
			if($("input[name='othercheck']").is(":checked")){
				$("#adddiscr").show();
			}
			else{
				$("#adddiscr").hide();
			}
			
		});
	 
		
		 $("#newadddiscr").hide();
			
			$("#reverse").click(function(){
				//$("#othercheckbox").show();
				if($("input[name='checkName']").is(":checked")){
					$("#newadddiscr").show();
					 $("#adddiscr").hide();
						$("#Reason").hide();
				}
				else{
					$("#newadddiscr").hide();
				}
				
			});
	
			
			 $("#Reason").hide();
				
				$("#Namechecked").click(function(){
					//$("#othercheckbox").show();
					if($("input[name='checkName']").is(":checked")){
						$("#Reason").show();
						$("#newadddiscr").hide();
					}
					else{
						$("#Reason").hide();
					}
					
				});
				
				/*final remark partial ok readonly*/
	
					
				
	var n =new Date();
	document.getElementById("currentdate").innerHTML= n.toLocaleDateString(); 
	//document.getElementById("remarkDate").innerHTML= n.toLocaleDateString(); 
	$("#remarkDate").val(n.toLocaleDateString());
	
/*---------------------------------- if payment mode DC then fetch its related data -------------------------------*/
	$("#vPolicyNo").attr('maxlength','8');
	//$('#mifscCode').prop('readonly',true);
	//$('#paymentMode').change(function(){
		//alert($('#paymentMode').val());
		if($('#paymentMode').val()=="Direct Credit"){
			//alert($('#paymentMode').val());
			var dc = $('#paymentMode').val();
			$.get("/spsv1/cheque/getdcdetail", { "dc": dc }, function(data){
				if(!data){
					alert("invalid dc mode");
					document.getElementById("bankName").value = "";
					document.getElementById("mnmCode").value = "";
					return false;
				}
				else {
					//alert(data);
					$("#mAccountNumber").attr('maxlength','12');
					$("#vAccountNumber").attr('maxlength','12');
					document.getElementById("bankName").value = data.bankName;
					document.getElementById("mnmCode").value = data.mnmCode;
				}
			});	
		}
		else{
			$("#mAccountNumber").attr('maxlength','50');
			$("#vAccountNumber").attr('maxlength','50');
			//document.getElementById("mAccountNumber").value = "";
			//document.getElementById("vAccountNumber").value = "";
			//document.getElementById("bankName").value = "";
			//document.getElementById("mnmCode").value = "";
			//document.getElementById("mmicrCode").value = "";
			//document.getElementById("mifscCode").value = "";
		}
	/*------------------------------- //if payment mode DC then fetch its related data ------------------------------*/
		
	/*------------------------------- if payment mode DC then fetch its related data ------------------------------*/
		if($('#paymentMode').val()=="F"){
			//alert($('#paymentMode').val());
			$("#mAccountNumber").attr('maxlength','12');
			$("#vAccountNumber").attr('maxlength','12');
			var dc = $('#paymentMode').val();
			$.get("/spsv1/cheque/getdcdetail", { "dc": dc }, function(data){
				if(!data){
					alert("invalid dc mode");
					document.getElementById("bankName").value = "";
					document.getElementById("mnmCode").value = "";
					document.getElementById("instructions").value = "";
					return false;
				}
				else {
					//alert(data);
					document.getElementById("bankName").value = data.bankName;
					document.getElementById("mnmCode").value = data.mnmCode;
					document.getElementById("instructions").value = "I BANK NRE REPAT CASE";
				}
			});	
		}
		else{
			$("#mAccountNumber").attr('maxlength','50');
			$("#vAccountNumber").attr('maxlength','50');
			/*document.getElementById("mAccountNumber").value = "";
			document.getElementById("vAccountNumber").value = "";*/
			//document.getElementById("bankName").value = "";
			//document.getElementById("mnmCode").value = "";
			//document.getElementById("mmicrCode").value = "";
			//document.getElementById("mifscCode").value = "";
		}
	/*------------------------------- //if payment mode DC then fetch its related data ------------------------------*/
		
		
	/*------------------------------ if payment mode NEFT then IFSC Code Input visible ---------------------------*/
		if($('#paymentMode').val()=="NEFT" || $('#paymentMode').val()=="G" || $('#paymentMode').val()=="RTGS" ){
			$('#mifscCode').prop('readonly',false);
		}
		else{
			$('#mifscCode').prop('readonly',true);
		}
	/*------------------------------ //if payment mode NEFT then IFSC Code Input visible ---------------------------*/
		
	/*------------------------------- if payment mode ECS then check MICR Code fill or not --------------------------*/
		if($('#paymentMode').val()=="ECS") {
			//alert($('#paymentMode').val());
			$('#mmicrCode').prop('readonly',false);
			$("#mmicrCode").attr('maxlength',"ECS");
		}
		else{
			//$("#mmicrCode").attr('maxlength','50');
			$('#mmicrCode').prop('readonly',true);
			/*document.getElementById("mAccountNumber").value = "";
			document.getElementById("vAccountNumber").value = "";*/
			//document.getElementById("bankName").value = "";
			//document.getElementById("mnmCode").value = "";
			//document.getElementById("mmicrCode").value = "";
			//document.getElementById("mifscCode").value = "";
		}
	/*----------------------------- //if payment mode ECS then check MICR Code fill or not --------------------------*/
		
	/*------------------------------- if payment mode CHEQUE then A/C field readonly --------------------------*/
		if($('#paymentMode').val()=='1'){
			$('#mAccountNumber').prop('readonly',true);
			$('#vAccountNumber').prop('readonly',true);
		}
		else{
			$('#mAccountNumber').prop('readonly',false);
			$('#vAccountNumber').prop('readonly',false);
		}
	/*------------------------------- //if payment mode CHEQUE then A/C field readonly --------------------------*/
		
	/*------------------------------- if payment mode NEFT-NRE then set Message in instruction field --------------------------*/
		if($('#paymentMode').val()=="G") {
			document.getElementById("instructions").value = "NEFT NRE REPAT CASE";
		}
		else {
			document.getElementById("instructions").value = "";
		}
	/*------------------------------- //if payment mode NEFT-NRE then set Message in instruction field --------------------------*/
		
	//});
	
	
	/*------------------- if discrepency yes then clear all field & no then checked checkbox uncheck ---------------------*/ 
		/*document.getElementById("remarks").value = "Process";*/
		//$("#remarks").val("Process");
		$("#selectchoice").change(function(){
			if($("#selectchoice").val() == "yes"){
				$("#mAccountNumber").val("");
				$("#vAccountNumber").val("");
				$("#mifscCode").val("");
				$("#mmicrCode").val("");
				$("#bankName").val("");
				//$("#paymentMode").val("select");
				$("#nrenroind").val("select");
				$("#mnmCode").val("");
				$("#Reason").val("");
				$("#assigneeId").val("");
				$("#instructions").val("");
				$('#mifscCode').prop('readonly',true);
				$('#mmicrCode').prop('readonly',true);
				$('#mAccountNumber').prop('readonly',true);
				$('#vAccountNumber').prop('readonly',true);
				$("#nrenroind").prop( "disabled", true );
				$('#instructions').prop('readonly',true);
				//alert("radio");
				$('input[type="radio"]').prop('checked',false);
				document.getElementById("remarks").value = "Discrepancy";
			}
			else if($('#selectchoice').val() == 'no'){
				$('#mAccountNumber').prop('readonly',false);
				$('#vAccountNumber').prop('readonly',false);
				$('input[type=checkbox]').prop('checked',false);
				$("#nrenroind").prop( "disabled", false );
				$('#instructions').prop('readonly',false);
				document.getElementById("remarks").value = "Process";
				$("#adddiscr").hide();
				$("#adddiscr").val("");
			 }
		});
		
		
		if($("#finalremark").val() == "Partial Ok"){
			$("#mAccountNumber").val("");
			$("#vAccountNumber").val("");
			$("#mifscCode").val("");
			$("#mmicrCode").val("");
			$("#bankName").val("");
			//$("#paymentMode").val("select");
			$("#nrenroind").val("select");
			$("#mnmCode").val("");
			$("#assigneeId").val("");
			$("#instructions").val("");
			$('#mifscCode').prop('readonly',true);
			$('#mmicrCode').prop('readonly',true);
			$('#vPolicyNo').prop('readonly',true);
			$('#mAccountNumber').prop('readonly',true);
			$('#vAccountNumber').prop('readonly',true);
			$("#nrenroind").prop( "disabled", true );
			$('#instructions').prop('readonly',true);
			
			
		/*	$('input[type="radio"]').prop('checked',false);*/
			/*document.getElementById("remarks").value = "Discrepancy";*/
		}
		else if($('#finalremark').val() == 'Need To Check'){
			$('#mAccountNumber').prop('readonly',false);
			$('#vAccountNumber').prop('readonly',false);
			$('input[type=checkbox]').prop('checked',false);
			$("#nrenroind").prop( "disabled", false );
			$("#onersifscCode").val("");
			$("#onersAccountNumber").val("");
			$('#instructions').prop('readonly',false);
			$("#adddiscr").hide();
			$("#adddiscr").val("");
			$('.save1').hide();
		 }
	/*------------------- //if discrepency yes then clear all field & no then checked checkbox uncheck ---------------------*/
		
	/*----------------------------------------------- cheque save button validation -------------------------------------*/
		$(".save").click(function(){
			
		/*	if($("input[name='checkName']").is(":checked")){
				alert("in newdis");
				$("#adddiscr").val($("#newadddiscr").val());
			}*/
			
			/*if($("#vAccountNumber").val()==""){
				alert("account number should not be empty");
				
			}*/
			
				    if($("#selectchoice").val()=='yes'){
				    	$("#mAccountNumber").val("");
						$("#vAccountNumber").val("");
						$("#mifscCode").val("");
						$("#mmicrCode").val("");
						$("#bankName").val("");
						//$("#paymentMode").val("");
						$("#nrenroind").val("");
						$("#mnmCode").val("");
						$("#assigneeId").val("");
					 }
		/*---------------------------- if v policy no field empty then show error  ----------------------------------*/
			var policyempty=$("#vPolicyNo").val();
			//alert("policyempty = "+policyempty);
			if(policyempty==""){
				//alert("policy number should not be empty");
				$.confirm({
		    	    title: 'Error!',
		    	    content: 'Policy Number Should Not Be Empty!!!',
		    	    buttons: {
		    	        OK: function () {
		    	        	return true;
		    	        }
		    	    }
		    	});
				return false;
			}
		/*---------------------------- //if v policy no field empty then show error  -----------------------------------*/
			
		/*-------------------------- if v policy no not match with policy no then show error  -------------------------*/
			var rpolicy = $("#rpolicyno").val();
			//alert("rpolicy = "+rpolicy);
			if(policyempty.length != 8) {
				$.confirm({
		    	    title: 'Error!',
		    	    content: 'Policy Number should be 8 Digit!!!',
		    	    buttons: {
		    	        OK: function () {
		    	        	return true;
		    	        }
		    	    }
		    	});
				return false;
			}
		/*-------------------------- //if v policy no not match with policy no then show error  -------------------------*/
			
			/*if(parseInt(policyempty) != parseInt(rpolicy))
			{
				alert("innnnnn");
				$.confirm({
		    	    title: 'Error!',
		    	    content: 'Policy Number is Wrong!!!',
		    	    buttons: {
		    	        OK: function () {
		    	        	return true;
		    	        }
		    	    }
		    	});
				return false;
			}*/
			
			if(policyempty != rpolicy)
			{
				$.confirm({
		    	    title: 'Error!',
		    	    content: 'Policy Number is Wrong!!!',
		    	    buttons: {
		    	        OK: function () {
		    	        	return true;
		    	        }
		    	    }
		    	});
				return false;
			}
			
			/*------------------------- SRU CASE ALERT VALIDATION ---------------------------*/	
			
			var prn = $('#prnNo').val();
			//alert("prn" +prn );
			$.get("/spsv1/cheque/getSruCase", { "prnNo": prn }, function(data){
				
				var descr = (data.descr);
					if (descr.match(/SRU CASE.*/)) {
					
					alert(data.descr + "Do you want to continue?");
					
					}
					 });
			
		/*------------------------- if discrepency yes then check discrepency checked or not ---------------------------*/
			 if($('#selectchoice').val() == 'yes'){
				 
				 if($("input[name='discrepancyTag']").is(":checked") || $("input[name='othercheck']").is(":checked")){
			           // return true;
			        }else{
			            //alert("error");
			        	alert("Please select Discrepancy");
			        	 return false;
			         }
			        
				 
			 } 
			 
			 if($("#remarks").val()=='Process'){
			 if($("input[id='Namechecked']").is(":checked")){
		           if($("#Reason").val()==""){
		        	   alert("Reason cant be empty");
		        	   return false;
		           }
		           
		        }
			 }
			 
			 if($("#remarks").val()=='Process'){
			 if($("input[id='reverse']").is(":checked")){
		           if($("#newadddiscr").val()==""){
		        	   alert("Reason cant be empty");
		        	   return false;
		           }
		           
		        }
			 }
		 	
			 if($("#remarks").val()=='Process'){
				 if($("input[name='checkName']").is(":checked")){
			           
			        }
			 	else{
			            //alert("error");
			        	alert("Please select  checkbox");
			        	return false;
			         }
			 }
			 	
			         
				 
			 
			 if($("input[name='discrepancyTag']").is(":checked") || $("input[name='othercheck']").is(":checked")){
				 if($('#selectchoice').val() == 'yes'){
					 //return true;
				 }
				 else{
					 alert("select discrepancy indication");
					 return false;
				 }
				
			 }
			 
			 /*else if($("input[name='othercheck']").is(":checked")){
				 if($('#selectchoice').val() == 'yes'){
					 return true;
				 }
				 else{
					 alert("select discrepancy indication");
				 }
				 return false;
			 }*/
			 
		/*------------------------- //if discrepency yes then check discrepency checked or not ---------------------------*/
			 
		/*---------------------------- if mAccountNo & vAccountNo not match then show error ------------------------------*/ 
			 var x = document.getElementById("mAccountNumber");
			    var y = document.getElementById("vAccountNumber");
			   
			    
			    if(!$("input[id='reverse']").is(":checked") && $("#remarks").val()=='Process'){
			    if (x.value == y.value) 
			    {
			    	
			        x.type = "password";
			    } 
			    else 
			    {
			    	
			    	$.confirm({
			    	    title: 'Error!',
			    	    content: 'Account Number is not matched!!!',
			    	    buttons: {
			    	        OK: function () {
			    	            //alert('Confirmed!');
			    	        	x.type = "text";
			    	        	//$("#mAccountNumber").val("");
			    	        	//$("#vAccountNumber").val("");
			    	        }
			    	    }
			    	});
			    	// x.type = "text";
			    	 return false;
			    }
			    }
			    	   
		/*---------------------------- //if mAccountNo & vAccountNo not match then show error ------------------------------*/ 
			    if($("#remarks").val()=='Process'){
			    	/*alert("remarks in");*/
			    	if($("#paymentMode").val()=='select'){
			    		alert("payment mode should not be empty");
			    		return false;
			    	}
			    	/*if($("#mAccountNumber").val()==''){
			    		alert("m account number/v account number should not be empty");
			    		return false;
			    	}*/
			    } 
			    
			    if($("#remarks").val()=='Process' && $('#paymentMode').val() !='1' && !$("input[id='reverse']").is(":checked")){
			    	if($("#mAccountNumber").val()==''){
			    		alert("m account number/v account number should not be empty");
			    		return false;
			    	}
			    } 
			    
			    
			    if($('#paymentMode').val()=="Direct Credit" || $('#paymentMode').val()=="F"){
					if($("#mAccountNumber").val().length != 12 && $("#remarks").val() == 'Process' && !$("input[id='reverse']").is(":checked")){
						alert("Account No. must be 12 digit.");
						document.getElementById("mAccountNumber").value = "";
						document.getElementById("vAccountNumber").value = "";
						return false;
					}
					return true;
				}
			    
			    
				if($('#paymentMode').val()=="ECS") {
					if($("#mmicrCode").val() == '' && $("#remarks").val() == 'Process' && !$("input[id='reverse']").is(":checked")){
						alert("M-MICR Code Should Not Be Empty!!!");
						return false;
					}
					 //if(!$("input[id='reverse']").is(":checked")){
					if($("#mmicrCode").val().length != 9 && $("#remarks").val() == 'Process' && !$("input[id='reverse']").is(":checked")){
						alert("M-MICR Code Should be 9 digit!!!");
						document.getElementById("mifscCode").value = "";
						document.getElementById("mmicrCode").value = "";
						document.getElementById("bankName").value = "";
						document.getElementById("mnmCode").value = "";
						return false;
					}
					return true;
				}
				
				if($('#paymentMode').val()=="NEFT" || $('#paymentMode').val()=="G" || $('#paymentMode').val()=="RTGS"){
					if($("#mifscCode").val() == '' && $("#remarks").val() == 'Process' && !$("input[id='reverse']").is(":checked")){
						alert("IFSC Code Should Not Be Empty!!!");
						return false;
					}
					
					if($("#mifscCode").val().length != 11 && $("#remarks").val() == 'Process' && !$("input[id='reverse']").is(":checked")){
						alert("IFSC Code Should be 11 digit!!!");
						document.getElementById("mifscCode").value = "";
						document.getElementById("mmicrCode").value = "";
						document.getElementById("bankName").value = "";
						document.getElementById("mnmCode").value = "";
						return false;
					}
					return true;
				}	
			
			return true;
		});	
		
		
	/*	-----------------------------------------------------------------------------------*/
		/*$('#mifscCode').on("keypress", function(x) {
			
			var ifsc =($('#mifscCode').val());
			
			if(ifsc.length==11)
			{
				if($('#paymentMode').val()=="NEFT" || $('#paymentMode').val()=="NEFT-NRE") {
				$.get("/mainapp/srucheque/getIfscDetails", { "ifsc": ifsc }, function(data){
					alert(data);
					if(!data){
						alert("invalid IFSC code");
						document.getElementById("mifscCode").value = "";
						document.getElementById("mmicrCode").value = "";
						document.getElementById("bankName").value = "";
						document.getElementById("mnmCode").value = "";
						return false;
					}
					else {
						$('#mmicrCode').prop('readonly',true);
						document.getElementById("mmicrCode").value = data.mmicrCode;
						document.getElementById("bankName").value = data.bankName;
						document.getElementById("mnmCode").value = data.mnmCode;
					}
					
				});
				}
			}
			
		});*/
		
		/*-----------------------------------------------------------------------------------*/
		
		 $("#onsubmit").submit(function(){
			  var ifscempty=$("#mifscCode").val();
		
				if(ifscempty==""){
					alert("IFSC should not be empty");
				}
		    });
	/*----------------------------------------------- //cheque save button validation -------------------------------------*/
		 $(".checkbtn").click(function(){
			 //alert("is in....");
			if(!$("input[name='checkName']").is(":checked"))
				{
				//alert("in confirm");
					$.confirm({
			    	    title: 'Error!',
			    	    content: 'Please Check Remark!!!',
			    	    buttons: {
			    	        OK: function () {
			    	        	 
			    	            }
			    	        }
			    	   });
					return false;
				}
			 return true;
		 });
				
		
		
});

 
 
 
 function clearUp(x){
		if(x.value != ""){
			x.nextElementSibling.innerHTML = "";
		}
	}
 
 
function loginvalidation(){
	 var y = document.getElementsByClassName("validlogin");
		var count = 0;
		
		for(var i=0; i<y.length; i++){
			if(y[i].value == ""){
				y[i].nextElementSibling.innerHTML = "Fields can't be empty";
				y[i].nextElementSibling.style.color = "red";
				count++;
			}
		}
		
		if(count > 0){
			return false;
		}
		
		return true;
 }

function getIfscDetails(x) {
	var ifsc = x.value;
	 var prnNo = document.getElementById("prnNo").value;
	 
	
	if(ifsc.length==11)
	{
		if($('#paymentMode').val()=="NEFT" || $('#paymentMode').val()=="G" || $('#paymentMode').val()=="RTGS" ) {
		$.get("/spsv1/cheque/getIfscDetails", { "ifsc": ifsc ,"prnNo":prnNo }, function(data){
			//alert(data.mifscCode);
			if(!data){
				alert("IFSC Code Mismatched");
				document.getElementById("mifscCode").value = "";
				document.getElementById("mmicrCode").value = "";
				document.getElementById("bankName").value = "";
				document.getElementById("mnmCode").value = "";
				return false;
			}
			else {
				$('#mmicrCode').prop('readonly',true);
				document.getElementById("mmicrCode").value = data.mmicrCode;
				document.getElementById("bankName").value = data.bankName;
				document.getElementById("mnmCode").value = data.mnmCode;
				//document.getElementById("mifscCode").value = data.mifscCode;
			}
			
		});
		}
	}
}


function getMicrDetails(x) {
	var micr = x.value;
	 var prnNo = document.getElementById("prnNo").value;
	if(micr.length==9)
	{
		if($('#paymentMode').val()=="ECS") {
		$.get("/spsv1/cheque/getMicrDetails", { "micr": micr,"prnNo":prnNo }, function(data){
			//alert(data.mnmCode);
			if(!data){
				alert("MICR code Mismatched");
				document.getElementById("mmicrCode").value = "";
				document.getElementById("bankName").value = "";
				document.getElementById("mnmCode").value = "";
				return false;
			}
			else {
				document.getElementById("bankName").value = data.bankName;
				document.getElementById("mnmCode").value = data.mnmCode;
				//document.getElementById("mmicrCode").value = data.mmicrCode;
				
			}
			
		});
		}
	}
}

function IsPassword() {
	var x = document.getElementById("mAccountNumber");
	if(x.type = "text") {
		x.type = "password";
	}
}


	/*------------------------- SRU CASE ALERT VALIDATION ---------------------------*/		
	function prn(){
		
		var prn = $('#prnNo').val();
			//alert("prn" +prn );
			
		var vPolicyNo = $('#vPolicyNo').val();
			//alert("vPolicyNo" +vPolicyNo );
			
			$.get("/spsv1/cheque/getSruCase", { "prnNo": prn }, function(data){
				
					
					
					//alert(data.descr);
					
					var descr = (data.descr);
						//if (descr == (data.descr) ) {
							if (descr.match(/SRU CASE.*/)) {
								 $("#descr").css("border", "1px solid red");
						$.confirm({
							title: 'Confirm!',
							content: descr + '&nbsp;' + 'Please Recheck again!!!' ,
							buttons: {
								OK: function() {
									
									
									return true;
									
								}
							}
						});
						return false;
					}
					
		 });

		
	}







	$(".save1").click(function(){
	
	var prn = $('#prnNo').val();
			//alert("prn" +prn );
			if($("#finalremark").val() == "Partial Ok"){
			$.get("/spsv1/cheque/getSruCase", { "prnNo": prn }, function(data){
				
				var descr = (data.descr);
					if (descr.match(/SRU CASE.*/)) {
					
					alert(data.descr + "Do you want to continue?");
					
					}
					 });
					 }
	
	});










