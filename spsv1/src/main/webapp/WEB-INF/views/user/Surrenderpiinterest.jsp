<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>spsv1</title>

<spring:url value="/css/bootstrap.min.css" var="bootstrapcssUrl" />
<link rel="stylesheet" href="${ bootstrapcssUrl }">

<spring:url value="../js/jquery.min.js" var="jqueryjsUrl" />
<script src="${ jqueryjsUrl }"></script>

<spring:url value="../js/validation.js" var="validationjs" />
<script src="${ validationjs }"></script>

<spring:url value="../js/bootstrap.min.js" var="bootstrapjsUrl" />
<script src="${ bootstrapjsUrl }"></script>
<spring:url value="../js/dateValidation.js" var="validationjs1" />
<script src="${ validationjs1 }"></script>
<link rel="stylesheet" href="../css/style.css">

<spring:url value="/css/font-awesome.min.css" var="fontawesome" />
<link rel="stylesheet" href="${ fontawesome }">

<spring:url value="../js/upload.js" var="validationjs1" />
<script src="${ validationjs1 }"></script>
<link rel="stylesheet" href="/spsv1/css/main.css">
<script src="/spsv1/js/script.js"></script>
<spring:url value="/js/todolistvalidation.js" var="todolistvalidationjs" />
<script src="${ todolistvalidationjs }"></script>
</head>
<body id="mainbody">
	<c:set var="user" scope="session" value="${ user }" />
	<c:if test="${user.role =='TL' }">
		<jsp:include page="tlheader.jsp"></jsp:include>
	</c:if>
	<c:if test="${user.role !='TL' }">
		<jsp:include page="userheader.jsp"></jsp:include>
	</c:if>

	<div class="allocationmaindiv">
		<div class="panel-group">
			<div class="panel panel-danger">
				<center>
					<div class="panel-heading">SURRENDER PI-INTEREST</div>
				</center>
				<div class="panel-body">
					<div id="loading">
						<center>
							<p>
								<img src="../images/loading.gif" /> uploading....
							</p>
						</center>
					</div>
					
					<div class="container mfimaindiv">
						<div class="row mfimainrow">
							<div class="col-sm-12 col-md-12 leftsidediv">

					<div>
						<center>
							<form id="upload_form" method="Post"
								action="/spsv1/Surrenderpi/Penal">
								
									<button
										value="uplpenal" id="uplpenal" name="uplpenal" class="btn btn-success">PENAL INTEREST FILE
										</button> <img id="preview" />
							



							</form>
						</center>
					</div>

								<%-- <center>
									<form id="upload_form" method="Post"
										action="/spsv1/Surrenderpi/remark">


										<!-- <div>
											<button class="btn btn-primary" id="uplremark" type="submit"
												value="uplremark" name="Surrenderpiinterest">REMARK
												FILE UPLOAD</button>
										</div>
										 -->
										<div>

											<button type="submit" class="btn btn-primary uplbtn"
												id="uplremark" name="Surrenderpiinterest" value="uplremark">Remark
												File</button>
										</div>
									</form>
								</center> --%>


								<div>
									<center>

										<form id="upload_form" method="Post"
											action="/spsv1/Surrenderpi/electronictot">
											<!-- 											<button class="btn btn-primary" id="uplelectronictot" type="submit" value="uplelectronictot" 
												name="uplelectronictot">ELECTRONIC TOT FILE</button> -->
												
												<button
										value="uplelectronictot" id="uplelectronictot" name="uplelectronictot" class="btn btn-success">Electronic TOT File
										</button> <img id="preview" />
							
												



										</form>
									</center>
								</div>
								<div>
									<center>
										<form id="upload_form" method="Post"
											action="/spsv1/Surrenderpi/chequetot">
										
													<button
										value="uplchequetot" id="uplchequetot" name="uplchequetot" class="btn btn-success">CHEQUE
												TOT FILE
										</button> <img id="preview" />
												
												



										</form>
									</center>
								</div>
								<div>
									<center>
										<form id="upload_form" method="Post"
											action="/spsv1/Surrenderpi/SRdata">
									
												
												
												<button
										value="uplsrdata" id="uplsrdata" name="uplsrdata" class="btn btn-success">SR DATA FILE
										</button> <img id="preview" />
							



										</form>
									</center>
								</div>
								
								<div>
									<center>
										<form id="upload_form" method="Post"
											action="/spsv1/Surrenderpi/vendor">
									
												
												
												<button
										value="uplvendor" id="uplvendor" name="uplvendor" class="btn btn-success">VENDOR
										</button> <img id="preview" />
							



										</form>
									</center>
								</div>
								


							</div>
						</div>
					</div>
				</div>
			</div>


		</div>

		<h2 class="successmsg">${uploadmsg }</h2>
		<h2 class="successmsg">${uploadmsg1 }</h2>

	</div>
	<%-- <div class="col-sm-4 col-md-4">
						<center>
							<form action="/spsv1/Hold/holdfilequery" method="post">
								<button class="button button2" type="submit"
									value="exportRecondate" name="exc"
									style="line-height: inherit;">Excute</button>
							</form>
							<br> <br>
							<div>
								<a href="/spsv1/Hold/HoldFileExport"><button value="delete"
										id="deletefile" class="btn btn-success">Export Hold
										File</button></a> <img id="preview" />
							</div>
							<br> <br>
							<div>
								<a href="/spsv1/Hold/GlenysFileExport"><button
										value="delete" id="deletefile" class="btn btn-success">Export
										Glenys</button></a> <img id="preview" />
							</div>
							<h2 class="successmsg">${ExcuteAllFiles }</h2>
						</center>
					</div>
					<div class="col-sm-4 col-md-4">
						<center></center>
					</div>
				</div>
			</div>
		</div> --%>
		<div>
						
								<form action="/spsv1/Surrenderpi/exe" method="post">
								<button
										value="excute" id="excute" name="excute" class="btn btn-success">EXECUTE 										</button> <img id="preview" />
							
							</form>
							<br><br>
							<form action="/spsv1/Surrenderpi/exportsurrender" method="get">
								<button
										value="exc" id="exc" name="exc" class="btn btn-success">DOWNLOAD REPORT
										</button> <img id="preview" />
							
							</form>
							<h2 class="successmsg">${ExcuteAllFiles }</h2>
					
	<h2 class="successmsg">${uploadmsg }</h2>
	<h2 class="successmsg">${uploaddelete }</h2>




</body>
</html>