<c:set var = "username" scope = "session" value = "${ username }"/>
<div class="header">
	
	<div class="row ">
		<div class="col-sm-4 col-md-4">
			<img src="../images/logo.jpg" />
		</div>
		<div class="col-sm-4 col-md-4 projectheading" id="projecttitle">
			<h2>Single Payment System</h2>
		</div>
		<div class="col-sm-4 col-md-4 userdetails" id="userdata">
			<p>
			<i class="fa fa-user-circle" aria-hi
			dden="true"></i> Welcome ${username }
			</p><br></br>
			<p>
			<i class="fa fa-calendar" aria-hidden="true"></i> <span id="currentdate"></span>
			</p>
			<%-- <jsp:include page="../user/Logout.jsp" /> --%>
		</div>
	</div>
	
</div>

<div class="mainbody">
	<div id="backcolor1" class="menuback menubackcolor">
	
		</div>
</div>
	<!-- 	-----------------------------CNC Details------------------------------------->
		
		<center><h3 class="processname">${processname }</h3></center>
