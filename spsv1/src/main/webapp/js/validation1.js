$(document).ready(function(){
	/*----------------------------------------------- Discrepancy save button validation -------------------------------------*/

	$(".savedesc").click(function(){
		
			    if($("#selectchoice").val()=='yes'){
			    	$("#mAccountNumber").val("");
					$("#vAccountNumber").val("");
					$("#mifscCode").val("");
					$("#mmicrCode").val("");
					$("#bankName").val("");
					$("#paymentMode").val("");
					$("#nrenroind").val("");
					$("#mnmCode").val("");
					$("#assigneeId").val("");
				 }
			    
	/*---------------------------- if v policy no field empty then show error  ----------------------------------*/
		var policyempty=$("#vPolicyNo").val();
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
		
	/*-------------------------- if v policy no not match with policy no then show error  -------------------------*/
		var rpolicy = $("#rpolicyno").val();
		//alert("rpolicy = "+rpolicy);
		//alert("policyempty = "+policyempty);
		if(parseInt(rpolicy) != parseInt(policyempty)) {
			 
			$.confirm({
	    	    title: 'Error!',
	    	    content: 'Policy Number is Wrong!!!',
	    	    buttons:
	    	    {
	    	        OK: function ()
	    	        {
	    	        	return true;
	    	        }
			
	    	    }
			
	    	});
			return false;
		}
	/*-------------------------- //if v policy no not match with policy no then show error  -------------------------*/
		
	/*------------------------- if discrepency yes then check discrepency checked or not ---------------------------*/
		 if($('#selectchoice').val() == 'yes'){
			 
			 if($("input[name='discrepancyTag']").is(":checked")){
		            return true;
		        }else{
		            //alert("error");
		        	alert("Please select Discrepancy");
		         }
		         return false;
			 
		 } 
		 if($("input[name='discrepancyTag']").is(":checked")){
			 if($('#selectchoice').val() == 'yes'){
				 return true;
			 }
			 else{
				 alert("select discrepancy indication");
			 }
		 }
		 
	/*------------------------- //if discrepency yes then check discrepency checked or not ---------------------------*/
		 
	/*---------------------------- if mAccountNo & vAccountNo not match then show error ------------------------------*/ 
		    var x = document.getElementById("mAccountNumber");
		    var y = document.getElementById("vAccountNumber");
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
		    	            //$.alert('Confirmed!');
		    	        	//x.type = "text";
		    	        	$("#mAccountNumber").val("");
		    	        	$("#vAccountNumber").val("");
		    	        }
		    	    }
		    	});
		    	 //x.type = "text";
		    	 return false;
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
		    
		    if($("#remarks").val()=='Process' && $('#paymentMode').val() !='1'){
		    	if($("#mAccountNumber").val()==''){
		    		alert("m account number/v account number should not be empty");
		    		return false;
		    	}
		    } 
		    
		    
		    if($('#paymentMode').val()=='5' || $('#paymentMode').val()=='F'){
				if($("#mAccountNumber").val().length != 12 && $("#remarks").val() == 'Process'){
					alert("Account No. must be 12 digit.");
					document.getElementById("mAccountNumber").value = "";
					document.getElementById("vAccountNumber").value = "";
					return false;
				}
				return true;
			}
		    
		    
			if($('#paymentMode').val()=='9') {
				if($("#mmicrCode").val() == ''){
					alert("M-MICR Code Should Not Be Empty!!!");
					return false;
				}
				return true;
			}
			
			if($('#paymentMode').val()=='B' || $('#paymentMode').val()=='G'){
				if($("#mifscCode").val() == '' && $("#remarks").val() == 'Process'){
					alert("IFSC Code Should Not Be Empty!!!");
					return false;
				}
				return true;
			}
		
		return true;
	});	
	
/*----------------------------------------------- //Discrepancy save button validation -------------------------------------*/	
	
});