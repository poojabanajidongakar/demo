<div class="header">

	<div class="row ">
		<div class="col-sm-4 col-md-4">
			<img src="../images/logo.jpg" />
		</div>
		<div class="col-sm-4 col-md-4" id="projecttitle">
			<h2>Single Payment System</h2>
		</div>
		<div class="col-sm-4 col-md-4 userdetails">
			<p>
				<i class="fa fa-user-circle" aria-hidden="true"></i> Welcome
				${user.username }
			</p>
			<br></br>
			<p>
				<i class="fa fa-calendar" aria-hidden="true"></i><span
					id="currentdate"></span>
			</p>
			<jsp:include page="../user/Logout.jsp" />
		</div>


	</div>

</div>

<div class="mainbody">
	<div id="backcolor" class="menu">

		<ul>
			<li><a href="/spsv1/cheque/show">Home</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="">Sampling<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/spsv1/cheque/show">To Do List</a></li>
					<li><a href="/spsv1/cheque/exportExcel">Export To Excel</a></li>
					<li><a href="/spsv1/Hold/holdpayment">Hold File</a></li>
				</ul></li>

			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="">Payment Mode <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/spsv1/cheque/showsubtype?paymode=Direct Credit">Direct
							Credit</a></li>
					<!-- <li><a href="/sps/showsubtypeteam">SU/PW</a></li> -->
					<li><a href="/spsv1/cheque/showsubtype?paymode=RTGS">RTGS</a></li>
					<li><a href="/spsv1/cheque/showsubtype?paymode=NEFT">NEFT</a></li>
					<li><a href="/spsv1/cheque/showsubtype?paymode=ECS">ECS</a></li>
					<li><a href="/spsv1/cheque/showsubtype?paymode=G">NEFT-NRE</a></li>
					<li><a href="/spsv1/cheque/showsubtype?paymode=F">DC-NRE</a></li>
				</ul></li>

			<!-- <li class="dropdown" style="width:11%"><a class="dropdown-toggle" data-toggle="dropdown" href="">Payment Mode <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="dropdown dropdown-submenu"> ----in a href /spsv1/cheque/showsubtype?paymode=Direct Credit--
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">Direct Credit </a>
	                        <ul class="dropdown-menu submenu">
	                         <li><a href="/spsv1/cheque/showamtgrtfive?paymode=Direct Credit&amt=gfive">amt >=10</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=Direct Credit&amt=gfive">amt >= 5lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=Direct Credit&amt=betofi">bet 2-5 lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=Direct Credit&amt=lessTwo">amt <= 2lac</a></li>
	                        
	                        </ul>
	                    </li>
						
						<li class="dropdown dropdown-submenu"> ----in a href /spsv1/cheque/showsubtype?paymode=Direct Credit--
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">RTGS </a>
	                        <ul class="dropdown-menu submenu">
	                         <li><a href="/spsv1/cheque/showamtgrtfive?paymode=Direct Credit&amt=gfive">amt >=10</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=RTGS&amt=gfive">amt >= 5lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=RTGS&amt=betofi">bet 2-5 lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=RTGS&amt=lessTwo">amt <= 2lac</a></li>
	                        
	                        </ul>
	                    </li>
	                    <li class="dropdown dropdown-submenu"> ----in a href /spsv1/cheque/showsubtype?paymode=Direct Credit--
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">NEFT </a>
	                        <ul class="dropdown-menu submenu">
	                         <li><a href="/spsv1/cheque/showamtgrtfive?paymode=Direct Credit&amt=gfive">amt >=10</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=NEFT&amt=gfive">amt >= 5lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=NEFT&amt=betofi">bet 2-5 lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=NEFT&amt=lessTwo">amt <= 2lac</a></li>
	                        
	                        </ul>
	                    </li>
	                    <li class="dropdown dropdown-submenu"> ----in a href /spsv1/cheque/showsubtype?paymode=Direct Credit--
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">ECS </a>
	                        <ul class="dropdown-menu submenu">
	                         <li><a href="/spsv1/cheque/showamtgrtfive?paymode=Direct Credit&amt=gfive">amt >=10</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=ECS&amt=gfive">amt >= 5lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=ECS&amt=betofi">bet 2-5 lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=ECS&amt=lessTwo">amt <= 2lac</a></li>
	                        
	                        </ul>
	                    </li>
	                    <li class="dropdown dropdown-submenu"> ----in a href /spsv1/cheque/showsubtype?paymode=Direct Credit--
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">NEFT-NRE </a>
	                        <ul class="dropdown-menu submenu">
	                         <li><a href="/spsv1/cheque/showamtgrtfive?paymode=Direct Credit&amt=gfive">amt >=10</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=G&amt=gfive">amt >= 5lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=G&amt=betofi">bet 2-5 lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=G&amt=lessTwo">amt <= 2lac</a></li>
	                        
	                        </ul>
	                    </li>
	                    <li class="dropdown dropdown-submenu"> ----in a href /spsv1/cheque/showsubtype?paymode=Direct Credit--
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">DC-NRE </a>
	                        <ul class="dropdown-menu submenu">
	                         <li><a href="/spsv1/cheque/showamtgrtfive?paymode=Direct Credit&amt=gfive">amt >=10</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=F&amt=gfive">amt >= 5lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=F&amt=betofi">bet 2-5 lac</a></li>
	                          <li><a href="/spsv1/cheque/showamtgrtfive?paymode=F&amt=lessTwo">amt <= 2lac</a></li>
	                        
	                        </ul>
	                    </li>
						
						
					</ul>
				</li> -->

			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="">One Rs type <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/spsv1/cheque/OneRstype?OneRsType=Need To Check">Need
							To Check</a></li>
					<li><a href="/spsv1/cheque/OneRstype?OneRsType=Partial Ok">Partial
							Ok</a></li>

				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="">Pre-Sampling<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<!-- <li><a href="/spsv1/Precheque/uploadPrefile">Upload File</a></li> -->
					<!-- <li><a href="/spsv1/Precheque/show">To Do List</a></li> -->
					<li class="dropdown dropdown-submenu">
						<!-- ----in a href /spsv1/cheque/showsubtype?paymode=Direct Credit-- -->
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">To
							Do List </a>
						<ul class="dropdown-menu submenu">
							<li><a href="/spsv1/Precheque/show?type=maker">Maker</a></li>
							<li><a href="/spsv1/Precheque/show?type=checker">Checker</a></li>
						</ul>
					</li>
					<li><a href="/spsv1/Precheque/exportExcel">Export To Excel</a></li>
					<!-- <li><a href="/spsv1/Prereport/searchuser">Search</a></li>
						<li><a href="/spsv1/Prereport/show">Status</a></li>
						<li><a href="/spsv1/report/showmiscancelremark">Modify Cancel Remark</a></li> -->
				</ul></li>

		</ul>



	</div>
</div>
<!-- 	-----------------------------CNC Details----------------------------------- -->

<center>
	<h3 class="processname">${processname }</h3>
</center>
