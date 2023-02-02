$(document).ready(function(){
	//alert("welcome");
	
	document.getElementById('abc').style.display = "none";
	
	var d = new Date();
	var twoDigitd= ((d.getDate().length+1) === 1)? (d.getDate()+1) : '0' + (d.getDate());
	var twoDigitMonth = ((d.getMonth().length+1) === 1)? (d.getMonth()+1) : '0' + (d.getMonth()+1);
	var strDate = d.getFullYear() + "-" + twoDigitMonth + "-" + twoDigitd;
	//alert("strDate......... "+strDate);
	
	
	/*------------------------ space not aalowed -----------------------------*/
	$("#newpassword").on('keypress', function(e){
		if(e.which == 32){
			$.confirm({
	    	    title: 'Error!',
	    	    content: "Spaces are not allowed...!!!",
	    	    buttons: {
	    	        OK: function () {
	    	        	return true;
	    	        }
	    	    }
	    	});
			return false; 
		}
			
	});
	
	$("#confirmpassword").on('keypress', function(e){
		if(e.which == 32){
			$.confirm({
	    	    title: 'Error!',
	    	    content: "Spaces are not allowed...!!!",
	    	    buttons: {
	    	        OK: function () {
	    	        	return true;
	    	        }
	    	    }
	    	});
			return false; 
		}
			
	});
	
	
	/*------------------------ Show Password expired ---------------------------------------------*/
	
		$("#userName").blur(function()
			{
			var uname = $("#userName").val();
			//alert(uname);
		
				$.ajax({
		        	type: "GET",
		        	url: "/spsv1/passwordvalidation/getlogindate",
		            data: {
		            	"uname":uname
		            }, 
		            success:function(data)
		            {
		            	//alert("getlogindate.."+data);
		            	if(data <= strDate && data != "") {
		            		//alert("true");
		            		$.confirm({
		        	    	    title: 'Error',
		        	    	    content: "Your Password has been expired.",
		        	    	    buttons: {
		        	    	        Change_Password: function () {
		        	    	        	document.getElementById("uname").value = uname;
		        	    	        	document.getElementById('abc').style.display = "block";
		        	    	        	document.getElementById("oldpassword").value = "";
	            	    	        	document.getElementById("newpassword").value = "";
	            	    	        	document.getElementById("confirmpassword").value = "";
		        	    	        	return true;
		        	    	        }
		        	    	    }
		        	    	});
		            	}
		            	else {
		            		//alert("in else.....");
		            		$.ajax({
		       		        	type: "GET",
		       		        	url: "/spsv1/passwordvalidation/checkpcremaindays",
		       		            data: {
		       		            	"uname":uname
		       		            },
		       		            success:function(data)
		       		            {
		       		            	//alert("checkpcremaindays......"+data);
		       		            	
		       		            	if(data <= 5 && data > 0) {
		       		            		$.confirm({
			       		    	    	    title: '',
			       		    	    	    content: "Your password will be expire in " + data + " days.",
			       		    	    	    buttons: {
			       		    	    	        CHANGE: function () {
			       		    	    	        	document.getElementById("uname").value = uname;
			       	        	    	        	document.getElementById('abc').style.display = "block";
			       	        	    	        	document.getElementById("oldpassword").value = "";
			                	    	        	document.getElementById("newpassword").value = "";
			                	    	        	document.getElementById("confirmpassword").value = "";
			       		    	    	        	return true;
			       		    	    	        },
				       		            		SKIP: function () 
				       		            		{
						    	    	        	return true;
						    	    	        }
			       		    	    	    }
			       		    	    	});
		       		            	}
		       		    			return false; 
		       		            }
		       	       
		       				});
		            	}
		    			return false; 
		            }
		   
				});
			
		});
	


	
	/*------------------ if password is blank then show error -----------------------------------------*/
	$("#password").click(function()
			{
		//alert("jhdsjfhdf");
		
			var uname = $("#userName").val();
			$.ajax({
	        	type: "GET",
	        	url: "/spsv1/passwordvalidation/getlogindate",
	            data: {
	            	"uname":uname
	            }, 
	           
	            success:function(data)
	            {
	            	//alert(data);
	            	
	            	if(data <= strDate && data != "") {
	            		//alert("true");
	            		$.confirm({
	        	    	    title: 'Error',
	        	    	    content: "Your Password has been expired.",
	        	    	    buttons: {
	        	    	        Change_Password: function () {
	        	    	        	document.getElementById("uname").value = uname;
	        	    	        	document.getElementById('abc').style.display = "block";
	        	    	        	document.getElementById("oldpassword").value = "";
            	    	        	document.getElementById("newpassword").value = "";
            	    	        	document.getElementById("confirmpassword").value = "";
	        	    	        	return true;
	        	    	        }
	        	    	    }
	        	    	});
	            	}
	            	else {
	            		//alert("in else.....");
	            		$.ajax({
	       		        	type: "GET",
	       		        	url: "/spsv1/passwordvalidation/checkpcremaindays",
	       		            data: {
	       		            	"uname":uname
	       		            },
	       		            success:function(data)
	       		            {
	       		            	//alert(data);
	       		            	
	       		            	if(data <= 5 && data > 0) {
	       		            		$.confirm({
		       		    	    	    title: '',
		       		    	    	    content: "Your password will be expire in " + data + " days.",
		       		    	    	    buttons: {
		       		    	    	        CHANGE: function () {
		       		    	    	        	document.getElementById("uname").value = uname;
		       	        	    	        	document.getElementById('abc').style.display = "block";
		       	        	    	        	document.getElementById("oldpassword").value = "";
		                	    	        	document.getElementById("newpassword").value = "";
		                	    	        	document.getElementById("confirmpassword").value = "";
		       		    	    	        	return true;
		       		    	    	        },
			       		            		SKIP: function () 
			       		            		{
					    	    	        	return true;
					    	    	        }
		       		    	    	    }
		       		    	    	});
	       		            	}
	       		    			return false; 
	       		            }
	       	       
	       				});
	            	}
	    			return false; 
	            }
			});
	});

	/*----------------------------- update password -------------------------------*/
	$(".editdata").click(function(){
		//alert("hiiiiiiii");
		var uname = $("#uname").val();
		var oldpassword = $("#oldpassword").val();
		var newpassword = $("#newpassword").val();
		var confirmpassword = $("#confirmpassword").val();
		var passwordvalid = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*])[a-zA-Z0-9!@#$%&*]+$/;
		
		//alert("uname.. "+uname + "  oldpassword.... "+oldpassword + "  newpassword.. "+newpassword+ "  confirmpassword.. "+confirmpassword);
		
		if(oldpassword=="" ||newpassword=="" || confirmpassword=="")
		{
			$.confirm({
	    	    title: 'Error!',
	    	    content: "Fields Can't Be Empty!!!",
	    	    buttons: {
	    	        OK: function () {
	    	        	return true;
	    	        }
	    	    }
	    	});
			return false; 
		 }
    	else 
    		{
    		$.ajax({
            	type: "GET",
            	url: "/spsv1/passwordvalidation/checkoldpassword",
                data: {
                	"uname":uname
                }, 
               
                success:function(data)
                {
                	//alert(data);
                	
                	if(data != oldpassword) {
                		//alert("true");
                		
                		$.confirm({
            	    	    title: 'Error',
            	    	    content: "Old password is wrong.",
            	    	    buttons: {
            	    	        OK: function () {
            	    	        	document.getElementById("oldpassword").value = "";
            	    	        	document.getElementById("newpassword").value = "";
            	    	        	document.getElementById("confirmpassword").value = "";
            	    	        	return true;
            	    	        }
            	    	    }
            	    	});
                	}
                	else if(newpassword.length < 8 || confirmpassword.length < 8)
	        			{
	                		$.confirm({
	            	    	    title: 'Error',
	            	    	    content: "Please Enter 8 Digit Password.....!!!!!",
	            	    	    buttons: {
	            	    	        OK: function () {
	            	    	        	document.getElementById("newpassword").value = "";
	            	    	        	document.getElementById("confirmpassword").value = "";
	            	    	        	return true;
	            	    	        }
	            	    	    }
	            	    	});
	        			}
	            		else if (!newpassword.match(passwordvalid))
		       		    {
	                		$.confirm({
	            	    	    title: 'Error',
	            	    	    content: "Password must contain at least 1 capital letter, small letter, 1 number and 1 special character......!!!!!",
	            	    	    buttons: {
	            	    	        OK: function () {
	            	    	        	document.getElementById("newpassword").value = "";
	            	    	        	document.getElementById("confirmpassword").value = "";
	            	    	        	return true;
	            	    	        }
	            	    	    }
	            	    	});
		       		    }
	            		else if(newpassword != confirmpassword){
		                		$.confirm({
  		            	    	    title: 'Error',
  		            	    	    content: "New Password and Confirm Password Not Match.",
  		            	    	    buttons: {
  		            	    	        OK: function () {
  		            	    	        	//$("#uname").val() == uname;
  		            	    	        	//document.getElementById("oldpassword").value = "";
  		            	    	        	document.getElementById("newpassword").value = "";
  		            	    	        	document.getElementById("confirmpassword").value = "";
  		            	    	        	return true;
  		            	    	        }
  		            	    	    }
  		            	    	});
  		                	}
                	else if(data == oldpassword) {
                		 $.ajax({
          		        	type: "GET",
          		        	url: "/spsv1/passwordvalidation/passalreadyexist",
          		            data: {
          		            	"uname":uname,"newpassword":newpassword
          		            },
          		            success:function(data)
          		            {
          		            	//alert(data);
          		            	
          		            	if(data == 'Exist') {
          		            		$.confirm({
              		    	    	    title: 'Error',
              		    	    	    content: 'Password already used.....!!!',
              		    	    	    buttons: {
              		    	    	        OK: function () {
              		    	    	        	document.getElementById("newpassword").value = "";
                        	    	        	document.getElementById("confirmpassword").value = "";
              		    	    	        	return true;
              		    	    	        }
              		    	    	    }
              		    	    	});
          		            	}
          		            	else if(data == 'Not Exist') {
          		            		$.ajax({
          		         		        	type: "GET",
          		         		        	url: "/spsv1/passwordvalidation/updatepassword",
          		         		            data: {
          		         		            	"uname":uname,"oldpassword":oldpassword,"newpassword":newpassword,"confirmpassword":confirmpassword
          		         		            },
          		         		            success:function(data)
          		         		            {
          		         		            	//alert(data);
          		         		            	$.confirm({
          		         		    	    	    title: 'Success',
          		         		    	    	    content: data,
          		         		    	    	    buttons: {
          		         		    	    	        OK: function () {
          		         		    	    	        	document.getElementById('abc').style.display = "none";
          		         		    	    	        	location.reload();
          		         		    	    	        	return true;
          		         		    	    	        }
          		         		    	    	    }
          		         		    	    	});
          		         		    			return false; 
          		         		            }
          		         	       
          		         				});
          		                     }
          		            	
          		    			return false; 
          		            }
          				});
                	}
                	
        			return false; 
                }
    		});
		   }
		return false;
	});
	
	
	
});


function div_hide(){
	document.getElementById('abc').style.display = "none";
	document.getElementById('abc1').style.display = "none";
	location.reload();
}

