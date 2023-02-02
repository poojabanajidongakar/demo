<div class="header">
	<div class="row ">
		<div class="col-sm-4 col-md-4">
			<img src="/spsv1/images/logo.jpg" />
		</div>
		<div class="col-sm-4 col-md-4" id="projecttitle">
			<h2>Single Payment System</h2>
		</div>
		<div class="col-sm-4 col-md-4 userdetails">
			<p>
			<i class="fa fa-user-circle" aria-hidden="true"></i> Welcome ${user.username }
			</p><br></br>
			<p>
			<i class="fa fa-calendar" aria-hidden="true"></i><span id="currentdate"></span>
			</p>
			<jsp:include page="../user/Logout.jsp" />
		</div>
	</div>
	
</div>

<div class="mainbody">
	<div id="backcolor" class="menu">
	
			<ul>
				<li><a href="/spsv1/cheque/show">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="">Payment Mode <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/spsv1/cheque/showsubtype?paymode=Direct Credit">Direct Credit</a></li> 
						<!-- <li><a href="/sps/showsubtypeteam">SU/PW</a></li> -->
						<li><a href="/spsv1/cheque/showsubtype?paymode=RTGS">RTGS</a></li>
						<li><a href="/spsv1/cheque/showsubtype?paymode=NEFT">NEFT</a></li>
						<li><a href="/spsv1/cheque/showsubtype?paymode=ECS">ECS</a></li>
						<li><a href="/spsv1/cheque/showsubtype?paymode=G">NEFT-NRE</a></li>
						<li><a href="/spsv1/cheque/showsubtype?paymode=F">DC-NRE</a></li>
					</ul>
				</li>
			<!-- 	<li><a href="/spsv1/cheque/showdiscrepancy">Descrepency Cases</a></li> -->
			<li><a href="/spsv1/report/show">Status</a></li>
						<li><a href="/spsv1/report/searchuser">Search</a></li>
				<!-- <li><a>Reports</a></li>
				<li><a>Admin</a></li>
				<li><a>Masters</a></li> -->
				<li><a href="/spsv1/cheque/uploadfile">Upload File</a></li>
				<li class="dropdown"><a href="/spsv1/cheque/exportExcel">Export To Excel</a></li>
				<li><a href="/spsv1/forgetpassword/changepass">Change Password</a></li>
				<li><a href="/spsv1/forgetpassword/pass">Forget Password</a></li>
			</ul>
	</div>	

	<!-- 	-----------------------------CNC Details----------------------------------- -->
		
		
