
$(document).ready(function(){
	document.getElementById('abc').style.display = "none";
	document.getElementById('abc1').style.display = "none";
	
	$("#submit").click(function(){
		//alert("hiiiiiiii");
		var subtype = $("#subtype").val();
		var remark = $("#remark").val();
		
		//alert("subtype.... "+subtype + "  remark.. "+remark);
		
		if(subtype=="" ||remark=="")
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
		        	url: "/spsv1/report/addcancelremark",
		            data: {
		            	"subtype":subtype,"remark":remark
		            }, 
		           
		            success:function(data)
		            {
		            	$.confirm({
		    	    	    title: 'Success',
		    	    	    content: data,
		    	    	    buttons: {
		    	    	        OK: function () {
		    	    	        	$("#subtype").val("");
		    	    	        	$("#remark").val("");
		    	    	        	return true;
		    	    	        }
		    	    	    }
		    	    	});
		    			return false; 
		            }
	       
				});
		   }
		return false;
	});
	
	
	$(".edit").click(function(){
		//alert("welcome.... ");
		var getid = this.id;
		//alert(getid);
		var splitid = getid.split(";");
		var subtype = splitid[0];
		var remarks = splitid[1];
		var id1 = splitid[2];
		
		//alert("subtype.... "+subtype+"   remarks..."+remarks+"    id1"+id1)
		
		document.getElementById("subtype1").value = subtype;
		document.getElementById("remark1").value = remarks;
		document.getElementById("id1").value = id1;
		
		document.getElementById('abc1').style.display = "block";
		
		return false;
	});
	
	
	$(".editdata").click(function(){
		//alert("hiiiiiiii");
		var subtype = $("#subtype1").val();
		var remark = $("#remark1").val();
		var id1 = $("#id1").val();
		
		//alert("subtype.... "+subtype + "  remark.. "+remark);
		
		if(subtype=="" ||remark=="")
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
		        	url: "/spsv1/report/updatecancelremarkdata",
		            data: {
		            	"subtype":subtype,"remark":remark,"id1":id1
		            }, 
		           
		            success:function(data)
		            {
		            	$.confirm({
		    	    	    title: 'Success',
		    	    	    content: data,
		    	    	    buttons: {
		    	    	        OK: function () {
		    	    	        	document.getElementById('abc1').style.display = "none";
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
	});
	
	
	$(".delete").click(function(){
		//alert("welcome....");
		var id = this.id;
		//alert("delete id...... "+id);
		if (confirm('Are you sure you want to delete this?'))
	    {
			//alert("ssssssssssssssssss");
			$.ajax({
	        	type: "GET",
	        	url: "/spsv1/report/deletecancelremarkdata",
	            data: {
	            	"id1":id
	            }, 
	            success:function(data)
	            {
	            	//alert(data);
	            	$.confirm({
	    	    	    title: 'Success',
	    	    	    content: data,
	    	    	    buttons: {
	    	    	        OK: function () {
	    	    	        	location.reload();
	    	    	        	return true;
	    	    	        }
	    	    	    }
	    	    	});
	    			return false; 
	            }
       
			});
	             //event.preventDefault();
		       return true;
	    }
    	
	});
	
	
});

function div_show() {
	document.getElementById('abc').style.display = "block";
}

function div_hide(){
	document.getElementById('abc').style.display = "none";
	document.getElementById('abc1').style.display = "none";
	location.reload();
}