$(document).ready(function(){
	$("#loading").hide();
	$("#upld").click(function(){
		$("#loading").show();
	});
});






/*function move() {

 var elem = document.getElementById("myBar"); 
	  
 var width = 0;
	  var id = setInterval(frame, 10);
	  function frame() {
	    if (width >= 100) {
	    	//alert("upload successfully");
	    	 
	    	$("#myBar").hide();
	        clearInterval(id);
	    } 
	    else {
	      width++; 
	      elem.style.width = width + '%'; 
	    }
	    
	  } 
 }*/